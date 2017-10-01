package jp.cormo.apagle

import com.twitter.util.tunable.{JsonTunableMapper, TunableMap}

object Config {

  val tunableMap: TunableMap = JsonTunableMapper()
    .loadJsonTunables("apagle", "com/twitter/tunables/apagle/instances.json")

  def get[T](id: String)(implicit clazz: Manifest[T]): Option[T] =
    tunableMap(TunableMap.Key[T](id)).apply()
  def hostname: String = get("Hostname").orNull
  def serverCertPath: String = get("ServerCertPath").orNull
  def serverKeyPath: String = get("ServerKeyPath").orNull
}