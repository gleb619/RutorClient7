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
        staticFileLocation("/public")

        def converter
        initExceptionHandler({
            e -> System.out.println("Uh-oh")
        })
        get("/hello", { request, response ->
            return "blah blah blah"
        })
        get("/torrent/:id/*", { request, response ->
            response.type("text/html; charset=UTF-8")
            return loadStatic(request.params(":id"), request.splat()[0])
        })
        get("/data", { request, response ->
            return converter.convert(loadStatic("0", "plain"))
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
        def plain = loadExternal("")
        def grouped = loadExternal("new")
        def folder = new File("test")
        def folderGrouped = new File("test/new")
        folder.delete()
        folder.mkdir()
        folderGrouped.delete()
        folderGrouped.mkdir()
        new File("test/index.html").text = plain
        new File("test/new/index.html").text = grouped
        println("Generate folders")

        Jsoup.parse(plain).select("#index > table > tbody > tr").drop(1)
                .collectParallel({ element ->
            [value: element.select("a[href*='/torrent/']").attr("href").split(/\//).drop(1)]
        })
                .collectParallel({
            torrentPath ->
                println("torrent: ${torrentPath.value[1]}/${torrentPath.value[2]}")
                def localFolder = new File("${folder.getName()}/${torrentPath.value[0]}/${torrentPath.value[1]}")
                localFolder.mkdirs()
                def file = new File("${folder.getName()}/${localFolder.getName()}/${torrentPath.value[2]}")
                file.text =
                        loadExternal("${torrentPath.value[0]}/${torrentPath.value[1]}/${torrentPath.value[2]}")
        })
    }

    private static String loadStatic(String id, String name) {
        def url = "torrent/${id}/${name}.html"
        def resource = ClassLoader.getSystemResource("public/$url")
        if (Objects.nonNull(resource)) {
            println("Found cached instance in classpath: $url")
            def path = Paths.get(resource.toURI())
            byte[] fileBytes = Files.readAllBytes(path)
            return new String(fileBytes)
        } else {
            println("Prepare to download page: $url")
            return loadExternal(url)
        }
    }

    private static String loadExternal(def url) {
        new URL("http://rutor.info/$url")
                .getText(
                connectTimeout: 5000,
                readTimeout: 10000,
                useCaches: true,
                allowUserInteraction: false,
                requestProperties: ['Connection': 'close']
        )
    }

    private static RutorConverter createConverter() {
        ApiBuilder.from("http://localhost:${port()}")
                .withLogger(new Logger() {
            @Override
            void error(String message) {
                println("11111")
            }

            @Override
            void error(Exception e, String message) {
                println("2222")
            }

            @Override
            void error(Exception e, String message, String... args) {
                println("3333")
            }
        })
                .build()
    }

}
