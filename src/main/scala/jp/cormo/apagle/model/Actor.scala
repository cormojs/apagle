package jp.cormo.apagle.model

import java.net.URI

import com.fasterxml.jackson.annotation._

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NON_PRIVATE)
class Actor(private val user: Account) {
  @JsonProperty("@context") val context = "https://www.w3.org/ns/activitystreams"
  @JsonProperty("type") val typ: String = "Person"
  @JsonProperty def preferredUsername: String = user.screenName
  @JsonProperty def name: String = user.displayName
  @JsonProperty def id: URI = user.url
  @JsonProperty def url: URI = user.atUtl
  @JsonProperty def following: URI = user.followingIndexUrl
  @JsonProperty def followers: URI = user.followersUrl
  @JsonProperty def inbox: URI = user.inboxUrl
  @JsonProperty def outbox: URI = user.outboxUrl
}
