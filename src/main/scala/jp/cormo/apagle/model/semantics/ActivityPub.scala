package jp.cormo.apagle.model.semantics

import org.apache.jena.rdf.model.{Model, ModelFactory, Property}

object ActivityPub {
  val uri: String = "https://www.w3.org/ns/activitypub"
//  val uri: String = "ap:"

  private val m: Model = ModelFactory.createDefaultModel()

  val INBOX: Property = m.createProperty(uri + "#inbox")
  val OUTBOX: Property = m.createProperty(uri + "#outbox")
  val FOLLOWING: Property = m.createProperty(uri + "#following")
  val FOLLOWERS: Property = m.createProperty(uri + "#followers")
  val STREAMS: Property = m.createProperty(uri + "#streams")
  val PREFERRED_USERNAME: Property = m.createProperty(uri + "#preferredUsername")
  val ENDPOINTS: Property = m.createProperty(uri + "#endpoints")
  val UPLOAD_MEDIA: Property = m.createProperty(uri + "#uploadMedia")
  val PROXY_URL: Property = m.createProperty(uri + "#proxyUrl")
  val OAUTH_CLIENT_AUTHORIZE: Property = m.createProperty(uri + "#oauthClientAuthorize")
  val PROVIDE_CLIENT_KEY: Property = m.createProperty(uri + "#providerClientKey")
  val AUTHORIZE_CLIENT_KEY: Property = m.createProperty(uri + "#authorizeClientKey")
  val SHARED_INBOX: Property = m.createProperty(uri + "#sharedInbox")
}
