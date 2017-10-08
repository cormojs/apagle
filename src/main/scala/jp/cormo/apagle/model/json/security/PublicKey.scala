package jp.cormo.apagle.model.json.security

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonProperty}
import jp.cormo.apagle.model.db.Account

case class PublicKey(@JsonIgnore private val acc: Account) {
  @JsonProperty val id: String = s"${acc.url.toString}#main-key"
  @JsonProperty val owner: String = acc.url.toString
  @JsonProperty val publicKeyPem: String = acc.publicKeyPem
}
