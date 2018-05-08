import spark.Request

import java.nio.file.Files
import java.nio.file.Paths

import static spark.Spark.*

class Server {

    static void main(String[] args) {
        staticFileLocation("/public")

        get("/hello", { request, response ->
            return "blah blah blah"
        })
        get("/torrent/:id/*", { request, response ->
            response.type("text/html; charset=UTF-8")
            return loadStatic(request)
        })
        println "Server.main#prepare to serve at ${port()}"
    }

    private static String loadStatic(Request request) {
        def url = "torrent/${request.params(":id")}/${request.splat()[0]}.html"
        def resource = ClassLoader.getSystemResource("public/$url")
        if (Objects.nonNull(resource)) {
            println("Found cached instance in classpath: $url")
            def path = Paths.get(resource.toURI())
            byte[] fileBytes = Files.readAllBytes(path)
            return new String(fileBytes)
        } else {
            println("Prepare to download page: $url")
            return new URL("http://rutor.info/$url")
                    .getText(
                    connectTimeout: 5000,
                    readTimeout: 10000,
                    useCaches: true,
                    allowUserInteraction: false,
                    requestProperties: ['Connection': 'close']
            )
        }
    }

}
