package jp.cormo.apagle

import slick.jdbc.JdbcBackend._

object Db {
  val db: DatabaseDef =
    Database.forURL(
      Config.postgresUrl, Config.postgresUser, Config.postgresPass,
      null, "org.postgresql.Driver"
    )
  def apply(): DatabaseDef = db
}
