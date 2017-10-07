package jp.cormo.apagle.model

import com.fasterxml.jackson.annotation.JsonProperty
import jp.cormo.apagle.model.extractor.{AccountAtUrl, Acct}

class Webfinger {
  var subject: String = _
  var aliases: Array[String] = _
  var links: Array[Link] = _
}

object Webfinger {
  def apply(name: String, host: String): Webfinger = {
    // FIXME
    val userId: String = "1"
    val magic: String = "RSA.u-H537crIzXgKcscU_TjF7XeEwWg8LYFeEB_lpeFPnurGwXHdbRuRxH_ODPI6jMf2Yf2RZFU5azjbe5eHfJhg9wu9Qx6o5fBxyR71Llqzxi36raq7BwKKpCgLPep77FVVpwRiGcnWWpMx2tGUhc5FMzAFv73yefeToM_fdotB2w6GXHo7bXhYON5XYadTM3skywKQN9aemL5_AiG6GVjvvWWfwz4K7B03u-Go_QeEEYGLNXBZTo439_WISkfTWSUacW11O-FN1Drjf39Y_vgpPETc2zN19nvleMgym7OpcKgJnL7Y6nFBJHtUpJrkRpxGbGSHXZEospBOks15Ls3dQ==.AQAB"

    val w = new Webfinger()
    w.subject = Acct.apply(name, host)
    w.aliases = Array(AccountAtUrl(name, host), s"https://$host/users/$name")
    w.links = Array(
      ProfileLink(
        rel = "http://webfinger.net/rel/profile-page",
        typ = "text/html",
        href = s"https://$host/@$name"
      ),
      UpdateFrom(
        rel = "http://schemas.google.com/g/2010/updates-from",
        typ = "application/atom+xml",
        href = s"https://$host/users/$name.atom"
      ),
      Self(
        rel = "self",
        typ = "application/activity+json",
        href = s"https://$host/users/$name"
      ),
      Salmon(
        rel = "salmon",
        href = s"https://$host/api/salmon/$userId"
      ),
      MagicPublicKey(
        rel = "magic-public-key",
        href = s"data:application/magic-public-key,$magic"
      ),
      Template(
        rel = "http://ostatus.org/shema/1.0/subscribe",
        template = s"https://$host/authorize_follow?acc={uri}"
      )
    )
    w
  }
}

case class ProfileLink(rel: String, @JsonProperty("type") typ: String, href: String)
  extends Link
case class UpdateFrom(rel: String, @JsonProperty("type") typ: String, href: String)
  extends Link
case class Self(rel: String, @JsonProperty("type") typ: String, href: String)
  extends Link
case class Salmon(rel: String, href: String)
  extends Link
case class MagicPublicKey(rel: String, href: String) extends Link
case class Template(rel: String, template: String) extends Link

class Link