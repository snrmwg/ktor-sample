package sandbox.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay

fun Application.configureRouting() {

  routing {
    get("/") {
      call.response.cacheControl(CacheControl.NoCache(null))
      call.respondTextWriter(contentType = ContentType.Text.EventStream) {
        try {
          for (i in 1..10) {
            delay(1_000)
            write("$i\n")
            println("sent $i")
            flush()
          }
        }catch (e: Throwable) {
          println(e.javaClass.simpleName)
          this.close()
        }
      }
    }
  }

}
