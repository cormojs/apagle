package jp.cormo.apagle.endpoint

import io.finch._
import jp.cormo.apagle.model.Webfinger
import jp.cormo.apagle.model.extractor.{AccountAtUrl, Acct}

object WebfingerEndpoint extends Endpoint[Webfinger] {
  def apply(): Endpoint[Webfinger] = get(".well-known" :: "webfinger" :: param("resource" )) { proc _ }

  private[this] def proc(s: String): Output[Webfinger] = {
    val p: (String, String) => Output[Webfinger] =  { (n, h) =>
      Ok(Webfinger(n, h)).withHeader("Content-Type" -> "application/jrd+json")
    }

    s match {
      case AccountAtUrl(name, host) => p(name, host)
      case Acct(name, host) => p(name, host)
      case _ => BadRequest(new IllegalArgumentException)
    }
  }

  override def apply(input: Input) = apply().apply(input)
}
