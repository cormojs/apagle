package jp.cormo.apagle.model

import java.net.URI

import com.fasterxml.jackson.annotation.JsonProperty

class Account {
  var atUtl: URI = _
  var displayName: String = _
  var screenName: String = _
  var followersUrl: URI = _
  var followingIndexUrl: URI = _
  var url: URI = _
  @JsonProperty("inbox_url") var inboxUrl: URI = _
  @JsonProperty("outbox_url") var outboxUrl: URI = _
}

object Account {
  def find(screenName: String): Account = {
    val acc = new Account()
    acc.screenName = screenName
    acc.displayName = "Eugen"
    acc.url = new URI(s"https://192.168.0.3:8443/users/$screenName")
    acc.atUtl = new URI(s"https://192.168.0.3:8443/@$screenName")
    acc
  }
}