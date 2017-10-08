package jp.cormo.apagle.model.json.activitypub

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonProperty}
import jp.cormo.apagle.model.db.Account
import jp.cormo.apagle.model.json._

@JsonIgnore
class Actor(@JsonIgnore private val acc: Account) extends activitystreams.Object("Person") {
  def this() = this(new Account)
  val id: String = acc.url.toString
  val name: String = acc.displayName
  @JsonProperty val inbox: String = acc.inboxUrl.toString
  @JsonProperty val outbox: String = acc.outboxUrl.toString
  @JsonProperty val following: String = acc.followingIndexUrl.toString
  @JsonProperty val followers: String = acc.followersUrl.toString
  @JsonProperty val preferredUsername: String = acc.screenName
  @JsonProperty val endpoints: Map[String, String] = acc.endpoints
  @JsonProperty val publicKey: security.PublicKey = security.PublicKey(acc)
  @JsonProperty val icon: activitystreams.Image = new activitystreams.Image(acc.iconUrl.toString)
  @JsonProperty val summary: String = acc.summary
  @JsonProperty val manuallyApprovesFollowers: Boolean = acc.manuallyApprovesFollowers
}
