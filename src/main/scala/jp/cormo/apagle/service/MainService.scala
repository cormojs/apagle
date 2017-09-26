package jp.cormo.apagle.service

import com.twitter.finagle.{Service, http}
import com.twitter.util.Future

class MainService extends Service[http.Request, http.Response] {
  def apply(req: http.Request): Future[http.Response] = {
    println(req.toString())
    println(req.headerMap.toString())
    println(req.getContentString())

    val res = http.Response(req.version, http.Status.Ok)
    res.contentString = req.params.getOrElse("hub.challenge", "")
    Future.value(res)
  }
}