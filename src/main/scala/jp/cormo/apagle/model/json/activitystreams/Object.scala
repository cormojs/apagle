package jp.cormo.apagle.model.json.activitystreams

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonProperty}

@JsonIgnore
abstract class Object(@JsonProperty val `type`: String) {
  @JsonProperty val `@context`: String = "https://www.w3.org/ns/activitystreams"
  @JsonProperty val id: String
  @JsonProperty val name: String
}
