import groovyx.gpars.GParsPool
import org.jsoup.Jsoup
import org.team619.rutor.config.ApiBuilder
import org.team619.rutor.converter.RutorConverter
import org.team619.rutor.core.Logger

import java.nio.file.Files
import java.nio.file.Paths

import static spark.Spark.*

class Server {

    static void main(String[] args) {
        def converter
        initExceptionHandler({
            e -> System.out.println("Uh-oh")
        })
        get("/torrent/:id/*", { request, response ->
            response.type("text/html; charset=UTF-8")
            return loadStatic(request.params(":id"), request.splat()[0])
        })
        get("/", { request, response ->
            return loadStatic("0", "plain")
        })
        get("/new", { request, response ->
            response.type("text/html; charset=UTF-8")
            return loadStatic("1", "grouped")
        })
        get("/plain", { request, response ->
            return converter.convert(loadStatic("0", "plain"))
        })
        get("/grouped", { request, response ->
            return converter.convert(loadStatic("1", "grouped"))
        })
        get("/detail", { request, response ->
            return converter.convert(loadStatic("271849", "mozilla-firefox-quantum-60.0-final-2018-rs"))
        })
        get("/generate", { request, response ->
            try {
                GParsPool.withPool {
                    onGenerate()
                }
            } catch (Exception e) {
                e.printStackTrace()
                throw e
            }

            return "ok"
        })
        converter = createConverter()
        println "Server.main#prepare to serve at ${port()}"
    }

    private static void onGenerate() {
        println("Load plain and group")
        def (folder, plain) = downloadMainPages()
        println("Downloaded main page")

        Jsoup.parse(plain).select("#index > table > tbody > tr")
                .drop(1)
                .collectParallel({ element ->
            element.select("a[href*='/torrent/']").attr("href").split(/\//).drop(1)
        })
                .collectParallel({ link ->
            def localFolder = new File("${folder.getAbsolutePath()}/${link[0]}/${link[1]}")
            println("Create folder for file: ${localFolder.absolutePath}")
            localFolder.mkdirs()
            if (localFolder.exists()) {
                def file = new File("${folder.getAbsolutePath()}/${link[0]}/${link[1]}/${link[2]}")
                file.createNewFile()
                println("\tCreated file: ${file.absolutePath}")
            }
            link
        })
                .withIndex()
                .collectParallel({ link, index ->
            println("Download page($index): ${link[2]}")
            def body
            try {
                body = loadExternal("${link[0]}/${link[1]}/${link[2]}")
            } catch (Exception e) {
                System.err.println("\tError ${link[2]} => ${e.getMessage()}")
                body = ""
            }

            def output = [
                    value: link,
                    body : body
            ]
        })
                .findAll({
            it.body?.trim()
        })
                .collectParallel({ torrent ->
            try {
                def file = new File("${folder.getAbsolutePath()}/${torrent.value[0]}/${torrent.value[1]}/${torrent.value[2]}")
                file.text = torrent.body
            } catch (Exception e) {
                e.printStackTrace()
            }
        })
    }

    private static def downloadMainPages() {
        def folder = new File("temp")
        def folderGrouped = new File("temp/new")
        folder.deleteDir()
        folder.mkdir()
        folderGrouped.mkdir()
        println("Generated folders in: $folder.absolutePath")
        def plain = loadExternal("")
        def grouped = loadExternal("new")
        new File("temp/index.html").text = plain
        new File("temp/new/index.html").text = grouped
        [folder, plain]
    }

    private static def downloadMainPages2() {
        def plain = new File("temp/index.html").text
        def folder = new File("temp")
        [folder, plain]
    }

    private static String loadStatic(String id, String name) {
        def url = "${id}/${name}"
        def resource = ClassLoader.getSystemResource("public/$url")
        if (Objects.nonNull(resource)) {
            println("Found cached instance in classpath: $url")
            def path = Paths.get(resource.toURI())
            byte[] fileBytes = Files.readAllBytes(path)
            return new String(fileBytes)
        } else {
            println("Prepare to download page: $url")
            return loadExternal("torrent/$url")
        }
    }

    private static String loadExternal(def url) {
        def sites = [
                "http://new-rutor.org",
                "http://rutor.info",
        ]
        def path = sites[1] + "/$url"
        println("\tGo to $path")
        new URL(path).getText(
                connectTimeout: 5000,
                readTimeout: 10000,
                useCaches: true,
                allowUserInteraction: false,
                requestProperties: ['Connection': 'close']
        )
    }

    private static RutorConverter createConverter() {
        new ApiBuilder.Companion().from("http://localhost:${port()}")
                .withLogger(new Logger() {
            @Override
            void error(String message) {
                println("message; $message")
            }

            @Override
            void error(Exception e, String message) {
                println("message; $message")
                e.printStackTrace()
            }

            @Override
            void error(Exception e, String message, String... args) {
                println("message; ${String.format(message, args)}")
                e.printStackTrace()
            }
        })
                .build()
    }

}
