package examples.sardine

import com.sun.net.httpserver._
import java.net.InetSocketAddress
import org.apache.http.impl.client.HttpClientBuilder
import com.github.sardine.Sardine
import com.github.sardine.impl.SardineImpl

object Main extends App {
  // Set up a dummy server.

  val serverHandler: HttpHandler = (exchange: HttpExchange) => {
    // Ignore the requests
    val response = "This is the response"
    // 0 means that no `Content-Length` is provided, and the response is chunked.
    exchange.sendResponseHeaders(200, 0)
    val os = exchange.getResponseBody
    os.write(response.getBytes)
    os.close
  }

  val server = HttpServer.create(new InetSocketAddress(8080), 0)
  server.createContext("/", serverHandler)
  server.start()

  try {
    // Set up a Sardine client
    val sardine: Sardine = new SardineImpl(
      HttpClientBuilder.create
    )

    // Run a simple request
    val is = sardine.get("http://localhost:8080")
    val response = new String(is.readAllBytes)
    is.close

    println(response)
  } finally {
    server.stop(1)
  }
}
