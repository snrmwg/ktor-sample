package sandbox

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

object RunClient {
  @JvmStatic
  fun main(args: Array<String>) {
    val client = HttpClient(Apache) {
      install(HttpTimeout) {
        connectTimeoutMillis = 500
        socketTimeoutMillis = 999
        requestTimeoutMillis = 10_500
      }
    }

    runBlocking {
      val start = System.currentTimeMillis()
      val res = client.get("http://localhost:8080/")
      println(res)
    }
  }
}