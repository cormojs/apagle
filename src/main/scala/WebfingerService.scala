import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.Future

class WebfingerService extends Service[Request, Response] {
  override def apply(request: Request): Future[Response] = {
    val res = Response(request.version, Status.Ok)
    res.contentType = "lrdd+json"
    Future.value(res)
  }
}
