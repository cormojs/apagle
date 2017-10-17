package jp.cormo.apagle.model.json.security

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonProperty}

case class PublicKey(@JsonIgnore private val acc: Any) {
//  @JsonProperty val id: String = s"${acc.uri}#main-key"
//  @JsonProperty val owner: String = acc.uri
//  @JsonProperty val publicKeyPem: String = acc.publicKey
}
