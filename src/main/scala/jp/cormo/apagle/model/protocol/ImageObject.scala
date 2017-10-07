package jp.cormo.apagle.model.protocol

import com.fasterxml.jackson.annotation.JsonProperty

class ImageObject(@JsonProperty val url: String) {
  @JsonProperty("type") val typ = "image"
}
