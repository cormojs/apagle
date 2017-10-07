package jp.cormo.apagle.service

import com.fasterxml.jackson.databind._
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.twitter.finagle.Service
import com.twitter.finagle.http.path.ParamMatcher
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.Future
import jp.cormo.apagle.model.extractor.{AccountAtUrl, Acct}
import jp.cormo.apagle.model.Webfinger


class WebfingerService extends Service[Request, Response] {
  override def apply(request: Request): Future[Response] = {
    request.params match {
      case Resource(Acct(name, host)) => process(request, name, host)
      case Resource(AccountAtUrl(name, host)) => process(request, name, host)
      case _ =>
        println(request.params)
        Future.value(Response(request.version, Status.BadRequest))
    }
  }

  private def process(request: Request, name: String, host: String): Future[Response] = {
    println(name, host)
    val mapper = new ObjectMapper with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)


    val webfinger = Webfinger(name, host)


    val response = Response(request.version, Status.Ok)
    response.contentType = "application/jrd+json"
    response.contentString = mapper.writeValueAsString(webfinger)
    Future.value(response)
  }

  object Resource extends ParamMatcher("resource")



}
