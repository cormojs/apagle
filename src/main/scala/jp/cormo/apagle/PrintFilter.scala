package jp.cormo.apagle

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.util.Future

class PrintFilter extends SimpleFilter[Request, Response] {
  override def apply(request: Request, service: Service[Request, Response]): Future[Response] = {
    println(request.toString(), request.params, request.headerMap)
    println(request.contentString)
    service(request)
  }
}
