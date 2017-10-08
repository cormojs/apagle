package jp.cormo.apagle.model.json

import com.fasterxml.jackson.annotation.JsonProperty

class ImageObject(@JsonProperty val url: String) {
  @JsonProperty("type") val typ = "image"
}
