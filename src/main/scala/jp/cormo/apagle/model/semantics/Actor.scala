package jp.cormo.apagle.model.semantics

import jp.cormo.apagle.Config
import jp.cormo.apagle.model.Account
import org.apache.jena.rdf.model._
import org.apache.jena.vocabulary.RDF

object Actor {
  import jp.cormo.apagle.model.semantics.{ActivityPub => Ap, ActivityStreams2 => As}
  def build(a: Account): Model = {
    val m = ModelFactory.createDefaultModel()
    m.createResource(a.url.toString, As.PERSON)
      .addProperty(Ap.FOLLOWERS, a.followersUrl.toString)
      .addProperty(Ap.FOLLOWING, a.followingIndexUrl.toString)
      .addProperty(Ap.INBOX, a.inboxUrl.toString)
      .addProperty(Ap.OUTBOX, a.outboxUrl.toString)
      .addProperty(Ap.PREFERRED_USERNAME, a.screenName)
      .addProperty(As.NAME, a.displayName)
      .addProperty(As.SUMMARY, a.summary)
      .addProperty(As.URL, a.atUrl.toString)
      .addProperty(As.MANUALLY_APPROVES_FOLLOWERS, a.manuallyApprovesFollowers.toString)
      .addProperty(As.PUBLIC_KEY, m.createResource(s"${a.url.toString}#main-key")
        .addProperty(As.OWNER, a.url.toString)
        .addProperty(As.PUBLIC_KEY_PEM, a.publicKey.publicKeyPem))
      .addProperty(Ap.ENDPOINTS, m.createResource()
        .addProperty(Ap.SHARED_INBOX, s"https://${Config.hostname}/inbox"))
      .addProperty(As.ICON, m.createResource()
        .addProperty(RDF.`type`, As.IMAGE)
        .addProperty(As.URL, a.icon.url))
      .addProperty(RDF.`type`, As.PERSON)
    m
  }
}
