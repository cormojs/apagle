package jp.cormo.apagle.model

import java.net.URI

import com.fasterxml.jackson.annotation.JsonProperty
import jp.cormo.apagle.Config
import jp.cormo.apagle.model.protocol.{ImageObject, KeyObject}
import jp.cormo.apagle.model.protocol.endpoint.{EndpointObject, SharedInboxObject}

class Account {
  var icon: ImageObject = _

  var endpoints: Map[String, EndpointObject] = _

  var publicKey: KeyObject = _

  var manuallyApprovesFollowers: Boolean = _

  var summary: String = _
  var atUrl: URI = _
  var displayName: String = _
  var screenName: String = _
  var followersUrl: URI = _
  var followingIndexUrl: URI = _
  var url: URI = _
  @JsonProperty("inbox_url") var inboxUrl: URI = _
  @JsonProperty("outbox_url") var outboxUrl: URI = _
}

object Account {
  def createMock(screenName: String): Account = {
    val acc = new Account()
    acc.screenName = screenName
    acc.displayName = "Eugen"
    acc.url = new URI(s"https://${Config.hostname}/users/$screenName")
    acc.atUrl = new URI(s"https://${Config.hostname}/@$screenName")
    acc.icon = new ImageObject("https://mstdn.maud.io/system/accounts/avatars/000/007/832/original/4a9845004c73b0fc.png?1492429850")
    acc.endpoints = Map("sharedInbox" -> new SharedInboxObject(s"https://${Config.hostname}/inbox"))
    acc.publicKey = KeyObject(
      s"https://${Config.hostname}/users/$screenName#main-key",
      s"https://${Config.hostname}/users/$screenName",
      "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwj6Cfix6eTVhauFrINfK\nsvVJNNSKBlfAKsfdyKk1surMQFU2DFic0O/u8mgF9Ny3l7PbdeAYuw4UhfPhrMnV\nYzE099HHhuSjppfywwVIb9QMF5NVMafdtpL6XQXtLT2wGyZjejBX0WGsLFqYF2nF\npb4xR00LJ5XjboPI0Qv8wsH4FbqEhqceFCw8aOxZDGbbi9CKmufBE7UOJ+eBEFvI\nC0y7/GC90BUqHAUhhdD3drq7cDelyTlC51pawtOEph7nNPV9wAAKgrxvYTQnF773\n4+ogSyMHXwo58+FBS3cbv3VvxsGcKpsV+RDS+LuM0P8Bd0XNtW38aYpMi1aGoOU9\niwIDAQAB\n-----END PUBLIC KEY-----\n"
    )
    acc.summary = "test summary"
    acc.followersUrl = new URI(s"https://${Config.hostname}/users/$screenName/followers")
    acc.followingIndexUrl = new URI(s"https://${Config.hostname}/users/$screenName/following")
    acc.inboxUrl = new URI(s"https://${Config.hostname}/users/$screenName/inbox")
    acc.outboxUrl = new URI(s"https://${Config.hostname}/users/$screenName/inbox")
    acc.manuallyApprovesFollowers = false
    acc
  }
}