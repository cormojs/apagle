package jp.cormo.apagle.model.json.activitypub

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonProperty}
import jp.cormo.apagle.model.db.Tables._
import jp.cormo.apagle.model.json._

import scala.collection.mutable

@JsonIgnore
class Actor(@JsonIgnore private val acc: AccountsRow) extends activitystreams.Object("Person") {
  val id: String = acc.uri
  val name: String = acc.displayName
  @JsonProperty val inbox: String = acc.inboxUrl
  @JsonProperty val outbox: String = acc.outboxUrl.toString
  @JsonProperty val following: String = acc.followersUrl
  @JsonProperty val followers: String = acc.followersUrl
  @JsonProperty val preferredUsername: String = acc.username
  @JsonProperty val endpoints: mutable.Map[String, String] = {
    val m: mutable.Map[String, String] = new mutable.HashMap[String, String]
    m.put("sharedInbox", acc.sharedInboxUrl)
    m
  }
  @JsonProperty val publicKey: security.PublicKey = security.PublicKey(acc)
  @JsonProperty val icon: activitystreams.Image = new activitystreams.Image(acc.avatarRemoteUrl.getOrElse(""))
  @JsonProperty val summary: String = acc.note
  @JsonProperty val manuallyApprovesFollowers: Boolean = acc.locked
}
