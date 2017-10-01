package jp.cormo.apagle.endpoint

import javax.activation.MimeType

import io.finch._
import jp.cormo.apagle.model.{Account, Actor}

object ActorEndpoint {
  def apply(): Endpoint[Actor] = {
    get("users" :: string :: header("Accept")) { (name: String, types: String) =>
      val mimes = types.split(",").map(t => new MimeType(t))
      if (mimes.exists(m => isTypeOf(m, "json")))
        Ok(new Actor(Account.find(name)))
          .withHeader("Content-Type" -> "application/activity+json")
      else
        BadRequest(new Exception)
    }
  }

  def isTypeOf(mime: MimeType, typ: String): Boolean = {
    val sub = mime.getSubType
    sub == "*" || sub == typ || sub.endsWith("+" + typ)
  }
}