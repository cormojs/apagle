package jp.cormo.apagle.endpoint

import javax.activation.MimeType

import io.finch._
import jp.cormo.apagle.Db
// import jp.cormo.apagle.model.db.Tables._
import jp.cormo.apagle.model.json.activitypub.Actor
import slick.jdbc.PostgresProfile.api._

object UsersEndpoint extends Endpoint[Actor] {
  def apply(): Endpoint[Actor] =
    get("users" :: string :: header("Accept")) { proc _ }

  def proc(name: String, types: String): Output[Actor] = {
    val mimes = types.split(",").map(new MimeType(_))
    println(types)
//    if (mimes.exists(isTypeOf(_, "json"))) {
//      Db().run(Accounts.filter(_.username === name).result).map(_.foreach { a =>
//        Ok(new Actor(a))
//      })
//    } else
      BadRequest(new IllegalArgumentException)
  }

  def isTypeOf(mime: MimeType, typ: String): Boolean = {
    val sub = mime.getSubType
    sub == "*" || sub == typ || sub.endsWith("+" + typ)
  }

  def apply(input: Input) = apply().apply(input)
}