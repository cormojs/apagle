package jp.cormo.apagle.endpoint

import javax.activation.MimeType

import io.finch._
import jp.cormo.apagle.model.db.Account
import jp.cormo.apagle.model.json.activitypub.Actor

object UsersEndpoint extends Endpoint[Actor] {
  def apply(): Endpoint[Actor] =
    get("users" :: string :: header("Accept")) { proc _ }

  def proc(name: String, types: String): Output[Actor] = {
    val mimes = types.split(",").map(new MimeType(_))
    println(types)
    if (mimes.exists(isTypeOf(_, "json")))
      Ok(new Actor(Account.createMock(name)))
        .withHeader("Content-Type" -> "application/activity+json")
    else
      BadRequest(new IllegalArgumentException)
  }

  def isTypeOf(mime: MimeType, typ: String): Boolean = {
    val sub = mime.getSubType
    sub == "*" || sub == typ || sub.endsWith("+" + typ)
  }

  def apply(input: Input) = apply().apply(input)
}