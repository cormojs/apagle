package jp.cormo.apagle.model.db
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  import shapeless.{HList, ::, HNil, Generic}
  import slickless._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(AccountDomainBlocks.schema, Accounts.schema, ArInternalMetadata.schema, Blocks.schema, ConversationMutes.schema, Conversations.schema, CustomEmojis.schema, DomainBlocks.schema, Favourites.schema, FollowRequests.schema, Follows.schema, Imports.schema, MediaAttachments.schema, Mentions.schema, Mutes.schema, Notifications.schema, OauthAccessGrants.schema, OauthAccessTokens.schema, OauthApplications.schema, PreviewCards.schema, PreviewCardsStatuses.schema, Reports.schema, SchemaMigrations.schema, SessionActivations.schema, Settings.schema, SiteUploads.schema, Statuses.schema, StatusesTags.schema, StatusPins.schema, StreamEntries.schema, Subscriptions.schema, Tags.schema, Users.schema, WebPushSubscriptions.schema, WebSettings.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table AccountDomainBlocks
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8), Default(None)
   *  @param domain Database column domain SqlType(varchar), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class AccountDomainBlocksRow(id: Long, accountId: Option[Long] = None, domain: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching AccountDomainBlocksRow objects using plain SQL queries */
  implicit def GetResultAccountDomainBlocksRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[AccountDomainBlocksRow] = GR{
    prs => import prs._
    AccountDomainBlocksRow.tupled((<<[Long], <<?[Long], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table account_domain_blocks. Objects of this class serve as prototypes for rows in queries. */
  class AccountDomainBlocks(_tableTag: Tag) extends profile.api.Table[AccountDomainBlocksRow](_tableTag, "account_domain_blocks") {
    def * = (id, accountId, domain, createdAt, updatedAt) <> (AccountDomainBlocksRow.tupled, AccountDomainBlocksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), accountId, domain, Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> AccountDomainBlocksRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8), Default(None) */
    val accountId: Rep[Option[Long]] = column[Option[Long]]("account_id", O.Default(None))
    /** Database column domain SqlType(varchar), Default(None) */
    val domain: Rep[Option[String]] = column[Option[String]]("domain", O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Accounts (database name fk_rails_8da10d9564) */
    lazy val accountsFk = foreignKey("fk_rails_8da10d9564", accountId, Accounts)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,domain) (database name index_account_domain_blocks_on_account_id_and_domain) */
    val index1 = index("index_account_domain_blocks_on_account_id_and_domain", (accountId, domain), unique=true)
  }
  /** Collection-like TableQuery object for table AccountDomainBlocks */
  lazy val AccountDomainBlocks = new TableQuery(tag => new AccountDomainBlocks(tag))

  /** Entity class storing rows of table Accounts
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Default()
   *  @param domain Database column domain SqlType(varchar), Default(None)
   *  @param secret Database column secret SqlType(varchar), Default()
   *  @param privateKey Database column private_key SqlType(text), Default(None)
   *  @param publicKey Database column public_key SqlType(text), Default()
   *  @param remoteUrl Database column remote_url SqlType(varchar), Default()
   *  @param salmonUrl Database column salmon_url SqlType(varchar), Default()
   *  @param hubUrl Database column hub_url SqlType(varchar), Default()
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param note Database column note SqlType(text), Default()
   *  @param displayName Database column display_name SqlType(varchar), Default()
   *  @param uri Database column uri SqlType(varchar), Default()
   *  @param url Database column url SqlType(varchar), Default(None)
   *  @param avatarFileName Database column avatar_file_name SqlType(varchar), Default(None)
   *  @param avatarContentType Database column avatar_content_type SqlType(varchar), Default(None)
   *  @param avatarFileSize Database column avatar_file_size SqlType(int4), Default(None)
   *  @param avatarUpdatedAt Database column avatar_updated_at SqlType(timestamp), Default(None)
   *  @param headerFileName Database column header_file_name SqlType(varchar), Default(None)
   *  @param headerContentType Database column header_content_type SqlType(varchar), Default(None)
   *  @param headerFileSize Database column header_file_size SqlType(int4), Default(None)
   *  @param headerUpdatedAt Database column header_updated_at SqlType(timestamp), Default(None)
   *  @param avatarRemoteUrl Database column avatar_remote_url SqlType(varchar), Default(None)
   *  @param subscriptionExpiresAt Database column subscription_expires_at SqlType(timestamp), Default(None)
   *  @param silenced Database column silenced SqlType(bool), Default(false)
   *  @param suspended Database column suspended SqlType(bool), Default(false)
   *  @param locked Database column locked SqlType(bool), Default(false)
   *  @param headerRemoteUrl Database column header_remote_url SqlType(varchar), Default()
   *  @param statusesCount Database column statuses_count SqlType(int4), Default(0)
   *  @param followersCount Database column followers_count SqlType(int4), Default(0)
   *  @param followingCount Database column following_count SqlType(int4), Default(0)
   *  @param lastWebfingeredAt Database column last_webfingered_at SqlType(timestamp), Default(None)
   *  @param inboxUrl Database column inbox_url SqlType(varchar), Default()
   *  @param outboxUrl Database column outbox_url SqlType(varchar), Default()
   *  @param sharedInboxUrl Database column shared_inbox_url SqlType(varchar), Default()
   *  @param followersUrl Database column followers_url SqlType(varchar), Default()
   *  @param protocol Database column protocol SqlType(int4), Default(0) */
  case class AccountsRow(id: Long, username: String = "", domain: Option[String] = None, secret: String = "", privateKey: Option[String] = None, publicKey: String = "", remoteUrl: String = "", salmonUrl: String = "", hubUrl: String = "", createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, note: String = "", displayName: String = "", uri: String = "", url: Option[String] = None, avatarFileName: Option[String] = None, avatarContentType: Option[String] = None, avatarFileSize: Option[Int] = None, avatarUpdatedAt: Option[java.sql.Timestamp] = None, headerFileName: Option[String] = None, headerContentType: Option[String] = None, headerFileSize: Option[Int] = None, headerUpdatedAt: Option[java.sql.Timestamp] = None, avatarRemoteUrl: Option[String] = None, subscriptionExpiresAt: Option[java.sql.Timestamp] = None, silenced: Boolean = false, suspended: Boolean = false, locked: Boolean = false, headerRemoteUrl: String = "", statusesCount: Int = 0, followersCount: Int = 0, followingCount: Int = 0, lastWebfingeredAt: Option[java.sql.Timestamp] = None, inboxUrl: String = "", outboxUrl: String = "", sharedInboxUrl: String = "", followersUrl: String = "", protocol: Int = 0)
  /** GetResult implicit for fetching AccountsRow objects using plain SQL queries */
  implicit def GetResultAccountsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Option[Int]], e5: GR[Option[java.sql.Timestamp]], e6: GR[Boolean], e7: GR[Int]): GR[AccountsRow] = GR{
    prs => import prs._
    Generic[AccountsRow].from(<<[Long] :: <<[String] :: <<?[String] :: <<[String] :: <<?[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[java.sql.Timestamp] :: <<[java.sql.Timestamp] :: <<[String] :: <<[String] :: <<[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[Int] :: <<?[java.sql.Timestamp] :: <<?[String] :: <<?[String] :: <<?[Int] :: <<?[java.sql.Timestamp] :: <<?[String] :: <<?[java.sql.Timestamp] :: <<[Boolean] :: <<[Boolean] :: <<[Boolean] :: <<[String] :: <<[Int] :: <<[Int] :: <<[Int] :: <<?[java.sql.Timestamp] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[Int] :: HNil)
  }
  /** Table description of table accounts. Objects of this class serve as prototypes for rows in queries. */
  class Accounts(_tableTag: Tag) extends profile.api.Table[AccountsRow](_tableTag, "accounts") {
    def * = (id :: username :: domain :: secret :: privateKey :: publicKey :: remoteUrl :: salmonUrl :: hubUrl :: createdAt :: updatedAt :: note :: displayName :: uri :: url :: avatarFileName :: avatarContentType :: avatarFileSize :: avatarUpdatedAt :: headerFileName :: headerContentType :: headerFileSize :: headerUpdatedAt :: avatarRemoteUrl :: subscriptionExpiresAt :: silenced :: suspended :: locked :: headerRemoteUrl :: statusesCount :: followersCount :: followingCount :: lastWebfingeredAt :: inboxUrl :: outboxUrl :: sharedInboxUrl :: followersUrl :: protocol :: HNil).mappedWith(Generic[AccountsRow])

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Default() */
    val username: Rep[String] = column[String]("username", O.Default(""))
    /** Database column domain SqlType(varchar), Default(None) */
    val domain: Rep[Option[String]] = column[Option[String]]("domain", O.Default(None))
    /** Database column secret SqlType(varchar), Default() */
    val secret: Rep[String] = column[String]("secret", O.Default(""))
    /** Database column private_key SqlType(text), Default(None) */
    val privateKey: Rep[Option[String]] = column[Option[String]]("private_key", O.Default(None))
    /** Database column public_key SqlType(text), Default() */
    val publicKey: Rep[String] = column[String]("public_key", O.Default(""))
    /** Database column remote_url SqlType(varchar), Default() */
    val remoteUrl: Rep[String] = column[String]("remote_url", O.Default(""))
    /** Database column salmon_url SqlType(varchar), Default() */
    val salmonUrl: Rep[String] = column[String]("salmon_url", O.Default(""))
    /** Database column hub_url SqlType(varchar), Default() */
    val hubUrl: Rep[String] = column[String]("hub_url", O.Default(""))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column note SqlType(text), Default() */
    val note: Rep[String] = column[String]("note", O.Default(""))
    /** Database column display_name SqlType(varchar), Default() */
    val displayName: Rep[String] = column[String]("display_name", O.Default(""))
    /** Database column uri SqlType(varchar), Default() */
    val uri: Rep[String] = column[String]("uri", O.Default(""))
    /** Database column url SqlType(varchar), Default(None) */
    val url: Rep[Option[String]] = column[Option[String]]("url", O.Default(None))
    /** Database column avatar_file_name SqlType(varchar), Default(None) */
    val avatarFileName: Rep[Option[String]] = column[Option[String]]("avatar_file_name", O.Default(None))
    /** Database column avatar_content_type SqlType(varchar), Default(None) */
    val avatarContentType: Rep[Option[String]] = column[Option[String]]("avatar_content_type", O.Default(None))
    /** Database column avatar_file_size SqlType(int4), Default(None) */
    val avatarFileSize: Rep[Option[Int]] = column[Option[Int]]("avatar_file_size", O.Default(None))
    /** Database column avatar_updated_at SqlType(timestamp), Default(None) */
    val avatarUpdatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("avatar_updated_at", O.Default(None))
    /** Database column header_file_name SqlType(varchar), Default(None) */
    val headerFileName: Rep[Option[String]] = column[Option[String]]("header_file_name", O.Default(None))
    /** Database column header_content_type SqlType(varchar), Default(None) */
    val headerContentType: Rep[Option[String]] = column[Option[String]]("header_content_type", O.Default(None))
    /** Database column header_file_size SqlType(int4), Default(None) */
    val headerFileSize: Rep[Option[Int]] = column[Option[Int]]("header_file_size", O.Default(None))
    /** Database column header_updated_at SqlType(timestamp), Default(None) */
    val headerUpdatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("header_updated_at", O.Default(None))
    /** Database column avatar_remote_url SqlType(varchar), Default(None) */
    val avatarRemoteUrl: Rep[Option[String]] = column[Option[String]]("avatar_remote_url", O.Default(None))
    /** Database column subscription_expires_at SqlType(timestamp), Default(None) */
    val subscriptionExpiresAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("subscription_expires_at", O.Default(None))
    /** Database column silenced SqlType(bool), Default(false) */
    val silenced: Rep[Boolean] = column[Boolean]("silenced", O.Default(false))
    /** Database column suspended SqlType(bool), Default(false) */
    val suspended: Rep[Boolean] = column[Boolean]("suspended", O.Default(false))
    /** Database column locked SqlType(bool), Default(false) */
    val locked: Rep[Boolean] = column[Boolean]("locked", O.Default(false))
    /** Database column header_remote_url SqlType(varchar), Default() */
    val headerRemoteUrl: Rep[String] = column[String]("header_remote_url", O.Default(""))
    /** Database column statuses_count SqlType(int4), Default(0) */
    val statusesCount: Rep[Int] = column[Int]("statuses_count", O.Default(0))
    /** Database column followers_count SqlType(int4), Default(0) */
    val followersCount: Rep[Int] = column[Int]("followers_count", O.Default(0))
    /** Database column following_count SqlType(int4), Default(0) */
    val followingCount: Rep[Int] = column[Int]("following_count", O.Default(0))
    /** Database column last_webfingered_at SqlType(timestamp), Default(None) */
    val lastWebfingeredAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_webfingered_at", O.Default(None))
    /** Database column inbox_url SqlType(varchar), Default() */
    val inboxUrl: Rep[String] = column[String]("inbox_url", O.Default(""))
    /** Database column outbox_url SqlType(varchar), Default() */
    val outboxUrl: Rep[String] = column[String]("outbox_url", O.Default(""))
    /** Database column shared_inbox_url SqlType(varchar), Default() */
    val sharedInboxUrl: Rep[String] = column[String]("shared_inbox_url", O.Default(""))
    /** Database column followers_url SqlType(varchar), Default() */
    val followersUrl: Rep[String] = column[String]("followers_url", O.Default(""))
    /** Database column protocol SqlType(int4), Default(0) */
    val protocol: Rep[Int] = column[Int]("protocol", O.Default(0))

    /** Index over (uri) (database name index_accounts_on_uri) */
    val index1 = index("index_accounts_on_uri", uri :: HNil)
    /** Index over (url) (database name index_accounts_on_url) */
    val index2 = index("index_accounts_on_url", url :: HNil)
    /** Uniqueness Index over (username,domain) (database name index_accounts_on_username_and_domain) */
    val index3 = index("index_accounts_on_username_and_domain", username :: domain :: HNil, unique=true)
  }
  /** Collection-like TableQuery object for table Accounts */
  lazy val Accounts = new TableQuery(tag => new Accounts(tag))

  /** Entity class storing rows of table ArInternalMetadata
   *  @param key Database column key SqlType(varchar), PrimaryKey
   *  @param value Database column value SqlType(varchar), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class ArInternalMetadataRow(key: String, value: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching ArInternalMetadataRow objects using plain SQL queries */
  implicit def GetResultArInternalMetadataRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[ArInternalMetadataRow] = GR{
    prs => import prs._
    ArInternalMetadataRow.tupled((<<[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table ar_internal_metadata. Objects of this class serve as prototypes for rows in queries. */
  class ArInternalMetadata(_tableTag: Tag) extends profile.api.Table[ArInternalMetadataRow](_tableTag, "ar_internal_metadata") {
    def * = (key, value, createdAt, updatedAt) <> (ArInternalMetadataRow.tupled, ArInternalMetadataRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(key), value, Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> ArInternalMetadataRow.tupled((_1.get, _2, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column key SqlType(varchar), PrimaryKey */
    val key: Rep[String] = column[String]("key", O.PrimaryKey)
    /** Database column value SqlType(varchar), Default(None) */
    val value: Rep[Option[String]] = column[Option[String]]("value", O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table ArInternalMetadata */
  lazy val ArInternalMetadata = new TableQuery(tag => new ArInternalMetadata(tag))

  /** Entity class storing rows of table Blocks
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8)
   *  @param targetAccountId Database column target_account_id SqlType(int8)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class BlocksRow(id: Long, accountId: Long, targetAccountId: Long, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching BlocksRow objects using plain SQL queries */
  implicit def GetResultBlocksRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp]): GR[BlocksRow] = GR{
    prs => import prs._
    BlocksRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table blocks. Objects of this class serve as prototypes for rows in queries. */
  class Blocks(_tableTag: Tag) extends profile.api.Table[BlocksRow](_tableTag, "blocks") {
    def * = (id, accountId, targetAccountId, createdAt, updatedAt) <> (BlocksRow.tupled, BlocksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(accountId), Rep.Some(targetAccountId), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> BlocksRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column target_account_id SqlType(int8) */
    val targetAccountId: Rep[Long] = column[Long]("target_account_id")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Accounts (database name fk_rails_009e331529) */
    lazy val accountsFk1 = foreignKey("fk_rails_009e331529", targetAccountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Accounts (database name fk_rails_93fa0ef60e) */
    lazy val accountsFk2 = foreignKey("fk_rails_93fa0ef60e", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,targetAccountId) (database name index_blocks_on_account_id_and_target_account_id) */
    val index1 = index("index_blocks_on_account_id_and_target_account_id", (accountId, targetAccountId), unique=true)
  }
  /** Collection-like TableQuery object for table Blocks */
  lazy val Blocks = new TableQuery(tag => new Blocks(tag))

  /** Entity class storing rows of table ConversationMutes
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8)
   *  @param conversationId Database column conversation_id SqlType(int8) */
  case class ConversationMutesRow(id: Long, accountId: Long, conversationId: Long)
  /** GetResult implicit for fetching ConversationMutesRow objects using plain SQL queries */
  implicit def GetResultConversationMutesRow(implicit e0: GR[Long]): GR[ConversationMutesRow] = GR{
    prs => import prs._
    ConversationMutesRow.tupled((<<[Long], <<[Long], <<[Long]))
  }
  /** Table description of table conversation_mutes. Objects of this class serve as prototypes for rows in queries. */
  class ConversationMutes(_tableTag: Tag) extends profile.api.Table[ConversationMutesRow](_tableTag, "conversation_mutes") {
    def * = (id, accountId, conversationId) <> (ConversationMutesRow.tupled, ConversationMutesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(accountId), Rep.Some(conversationId)).shaped.<>({r=>import r._; _1.map(_=> ConversationMutesRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column conversation_id SqlType(int8) */
    val conversationId: Rep[Long] = column[Long]("conversation_id")

    /** Foreign key referencing Accounts (database name fk_rails_69510c1b77) */
    lazy val accountsFk = foreignKey("fk_rails_69510c1b77", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Conversations (database name fk_rails_5ab139311f) */
    lazy val conversationsFk = foreignKey("fk_rails_5ab139311f", conversationId, Conversations)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,conversationId) (database name index_conversation_mutes_on_account_id_and_conversation_id) */
    val index1 = index("index_conversation_mutes_on_account_id_and_conversation_id", (accountId, conversationId), unique=true)
  }
  /** Collection-like TableQuery object for table ConversationMutes */
  lazy val ConversationMutes = new TableQuery(tag => new ConversationMutes(tag))

  /** Entity class storing rows of table Conversations
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param uri Database column uri SqlType(varchar), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class ConversationsRow(id: Long, uri: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching ConversationsRow objects using plain SQL queries */
  implicit def GetResultConversationsRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[ConversationsRow] = GR{
    prs => import prs._
    ConversationsRow.tupled((<<[Long], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table conversations. Objects of this class serve as prototypes for rows in queries. */
  class Conversations(_tableTag: Tag) extends profile.api.Table[ConversationsRow](_tableTag, "conversations") {
    def * = (id, uri, createdAt, updatedAt) <> (ConversationsRow.tupled, ConversationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), uri, Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> ConversationsRow.tupled((_1.get, _2, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column uri SqlType(varchar), Default(None) */
    val uri: Rep[Option[String]] = column[Option[String]]("uri", O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Uniqueness Index over (uri) (database name index_conversations_on_uri) */
    val index1 = index("index_conversations_on_uri", uri, unique=true)
  }
  /** Collection-like TableQuery object for table Conversations */
  lazy val Conversations = new TableQuery(tag => new Conversations(tag))

  /** Entity class storing rows of table CustomEmojis
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param shortcode Database column shortcode SqlType(varchar), Default()
   *  @param domain Database column domain SqlType(varchar), Default(None)
   *  @param imageFileName Database column image_file_name SqlType(varchar), Default(None)
   *  @param imageContentType Database column image_content_type SqlType(varchar), Default(None)
   *  @param imageFileSize Database column image_file_size SqlType(int4), Default(None)
   *  @param imageUpdatedAt Database column image_updated_at SqlType(timestamp), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class CustomEmojisRow(id: Long, shortcode: String = "", domain: Option[String] = None, imageFileName: Option[String] = None, imageContentType: Option[String] = None, imageFileSize: Option[Int] = None, imageUpdatedAt: Option[java.sql.Timestamp] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching CustomEmojisRow objects using plain SQL queries */
  implicit def GetResultCustomEmojisRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Int]], e4: GR[Option[java.sql.Timestamp]], e5: GR[java.sql.Timestamp]): GR[CustomEmojisRow] = GR{
    prs => import prs._
    CustomEmojisRow.tupled((<<[Long], <<[String], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table custom_emojis. Objects of this class serve as prototypes for rows in queries. */
  class CustomEmojis(_tableTag: Tag) extends profile.api.Table[CustomEmojisRow](_tableTag, "custom_emojis") {
    def * = (id, shortcode, domain, imageFileName, imageContentType, imageFileSize, imageUpdatedAt, createdAt, updatedAt) <> (CustomEmojisRow.tupled, CustomEmojisRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(shortcode), domain, imageFileName, imageContentType, imageFileSize, imageUpdatedAt, Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> CustomEmojisRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column shortcode SqlType(varchar), Default() */
    val shortcode: Rep[String] = column[String]("shortcode", O.Default(""))
    /** Database column domain SqlType(varchar), Default(None) */
    val domain: Rep[Option[String]] = column[Option[String]]("domain", O.Default(None))
    /** Database column image_file_name SqlType(varchar), Default(None) */
    val imageFileName: Rep[Option[String]] = column[Option[String]]("image_file_name", O.Default(None))
    /** Database column image_content_type SqlType(varchar), Default(None) */
    val imageContentType: Rep[Option[String]] = column[Option[String]]("image_content_type", O.Default(None))
    /** Database column image_file_size SqlType(int4), Default(None) */
    val imageFileSize: Rep[Option[Int]] = column[Option[Int]]("image_file_size", O.Default(None))
    /** Database column image_updated_at SqlType(timestamp), Default(None) */
    val imageUpdatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("image_updated_at", O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Uniqueness Index over (shortcode,domain) (database name index_custom_emojis_on_shortcode_and_domain) */
    val index1 = index("index_custom_emojis_on_shortcode_and_domain", (shortcode, domain), unique=true)
  }
  /** Collection-like TableQuery object for table CustomEmojis */
  lazy val CustomEmojis = new TableQuery(tag => new CustomEmojis(tag))

  /** Entity class storing rows of table DomainBlocks
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param domain Database column domain SqlType(varchar), Default()
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param severity Database column severity SqlType(int4), Default(Some(0))
   *  @param rejectMedia Database column reject_media SqlType(bool), Default(false) */
  case class DomainBlocksRow(id: Long, domain: String = "", createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, severity: Option[Int] = Some(0), rejectMedia: Boolean = false)
  /** GetResult implicit for fetching DomainBlocksRow objects using plain SQL queries */
  implicit def GetResultDomainBlocksRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[Int]], e4: GR[Boolean]): GR[DomainBlocksRow] = GR{
    prs => import prs._
    DomainBlocksRow.tupled((<<[Long], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int], <<[Boolean]))
  }
  /** Table description of table domain_blocks. Objects of this class serve as prototypes for rows in queries. */
  class DomainBlocks(_tableTag: Tag) extends profile.api.Table[DomainBlocksRow](_tableTag, "domain_blocks") {
    def * = (id, domain, createdAt, updatedAt, severity, rejectMedia) <> (DomainBlocksRow.tupled, DomainBlocksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(domain), Rep.Some(createdAt), Rep.Some(updatedAt), severity, Rep.Some(rejectMedia)).shaped.<>({r=>import r._; _1.map(_=> DomainBlocksRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column domain SqlType(varchar), Default() */
    val domain: Rep[String] = column[String]("domain", O.Default(""))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column severity SqlType(int4), Default(Some(0)) */
    val severity: Rep[Option[Int]] = column[Option[Int]]("severity", O.Default(Some(0)))
    /** Database column reject_media SqlType(bool), Default(false) */
    val rejectMedia: Rep[Boolean] = column[Boolean]("reject_media", O.Default(false))

    /** Uniqueness Index over (domain) (database name index_domain_blocks_on_domain) */
    val index1 = index("index_domain_blocks_on_domain", domain, unique=true)
  }
  /** Collection-like TableQuery object for table DomainBlocks */
  lazy val DomainBlocks = new TableQuery(tag => new DomainBlocks(tag))

  /** Entity class storing rows of table Favourites
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8)
   *  @param statusId Database column status_id SqlType(int8)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class FavouritesRow(id: Long, accountId: Long, statusId: Long, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching FavouritesRow objects using plain SQL queries */
  implicit def GetResultFavouritesRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp]): GR[FavouritesRow] = GR{
    prs => import prs._
    FavouritesRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table favourites. Objects of this class serve as prototypes for rows in queries. */
  class Favourites(_tableTag: Tag) extends profile.api.Table[FavouritesRow](_tableTag, "favourites") {
    def * = (id, accountId, statusId, createdAt, updatedAt) <> (FavouritesRow.tupled, FavouritesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(accountId), Rep.Some(statusId), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> FavouritesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column status_id SqlType(int8) */
    val statusId: Rep[Long] = column[Long]("status_id")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Accounts (database name fk_rails_0763833708) */
    lazy val accountsFk = foreignKey("fk_rails_0763833708", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Statuses (database name fk_rails_1d7900c97d) */
    lazy val statusesFk = foreignKey("fk_rails_1d7900c97d", statusId, Statuses)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Index over (accountId,id) (database name index_favourites_on_account_id_and_id) */
    val index1 = index("index_favourites_on_account_id_and_id", (accountId, id))
    /** Uniqueness Index over (accountId,statusId) (database name index_favourites_on_account_id_and_status_id) */
    val index2 = index("index_favourites_on_account_id_and_status_id", (accountId, statusId), unique=true)
  }
  /** Collection-like TableQuery object for table Favourites */
  lazy val Favourites = new TableQuery(tag => new Favourites(tag))

  /** Entity class storing rows of table FollowRequests
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8)
   *  @param targetAccountId Database column target_account_id SqlType(int8)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class FollowRequestsRow(id: Long, accountId: Long, targetAccountId: Long, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching FollowRequestsRow objects using plain SQL queries */
  implicit def GetResultFollowRequestsRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp]): GR[FollowRequestsRow] = GR{
    prs => import prs._
    FollowRequestsRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table follow_requests. Objects of this class serve as prototypes for rows in queries. */
  class FollowRequests(_tableTag: Tag) extends profile.api.Table[FollowRequestsRow](_tableTag, "follow_requests") {
    def * = (id, accountId, targetAccountId, createdAt, updatedAt) <> (FollowRequestsRow.tupled, FollowRequestsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(accountId), Rep.Some(targetAccountId), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> FollowRequestsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column target_account_id SqlType(int8) */
    val targetAccountId: Rep[Long] = column[Long]("target_account_id")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Accounts (database name fk_rails_0af6523f3c) */
    lazy val accountsFk1 = foreignKey("fk_rails_0af6523f3c", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Accounts (database name fk_rails_972b9f1173) */
    lazy val accountsFk2 = foreignKey("fk_rails_972b9f1173", targetAccountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,targetAccountId) (database name index_follow_requests_on_account_id_and_target_account_id) */
    val index1 = index("index_follow_requests_on_account_id_and_target_account_id", (accountId, targetAccountId), unique=true)
  }
  /** Collection-like TableQuery object for table FollowRequests */
  lazy val FollowRequests = new TableQuery(tag => new FollowRequests(tag))

  /** Entity class storing rows of table Follows
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8)
   *  @param targetAccountId Database column target_account_id SqlType(int8)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class FollowsRow(id: Long, accountId: Long, targetAccountId: Long, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching FollowsRow objects using plain SQL queries */
  implicit def GetResultFollowsRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp]): GR[FollowsRow] = GR{
    prs => import prs._
    FollowsRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table follows. Objects of this class serve as prototypes for rows in queries. */
  class Follows(_tableTag: Tag) extends profile.api.Table[FollowsRow](_tableTag, "follows") {
    def * = (id, accountId, targetAccountId, createdAt, updatedAt) <> (FollowsRow.tupled, FollowsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(accountId), Rep.Some(targetAccountId), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> FollowsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column target_account_id SqlType(int8) */
    val targetAccountId: Rep[Long] = column[Long]("target_account_id")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Accounts (database name fk_rails_ac4936d17b) */
    lazy val accountsFk1 = foreignKey("fk_rails_ac4936d17b", targetAccountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Accounts (database name fk_rails_f59577ad9d) */
    lazy val accountsFk2 = foreignKey("fk_rails_f59577ad9d", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,targetAccountId) (database name index_follows_on_account_id_and_target_account_id) */
    val index1 = index("index_follows_on_account_id_and_target_account_id", (accountId, targetAccountId), unique=true)
  }
  /** Collection-like TableQuery object for table Follows */
  lazy val Follows = new TableQuery(tag => new Follows(tag))

  /** Entity class storing rows of table Imports
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8)
   *  @param `type` Database column type SqlType(int4)
   *  @param approved Database column approved SqlType(bool), Default(false)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param dataFileName Database column data_file_name SqlType(varchar), Default(None)
   *  @param dataContentType Database column data_content_type SqlType(varchar), Default(None)
   *  @param dataFileSize Database column data_file_size SqlType(int4), Default(None)
   *  @param dataUpdatedAt Database column data_updated_at SqlType(timestamp), Default(None) */
  case class ImportsRow(id: Long, accountId: Long, `type`: Int, approved: Boolean = false, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, dataFileName: Option[String] = None, dataContentType: Option[String] = None, dataFileSize: Option[Int] = None, dataUpdatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching ImportsRow objects using plain SQL queries */
  implicit def GetResultImportsRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Boolean], e3: GR[java.sql.Timestamp], e4: GR[Option[String]], e5: GR[Option[Int]], e6: GR[Option[java.sql.Timestamp]]): GR[ImportsRow] = GR{
    prs => import prs._
    ImportsRow.tupled((<<[Long], <<[Long], <<[Int], <<[Boolean], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp]))
  }
  /** Table description of table imports. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class Imports(_tableTag: Tag) extends profile.api.Table[ImportsRow](_tableTag, "imports") {
    def * = (id, accountId, `type`, approved, createdAt, updatedAt, dataFileName, dataContentType, dataFileSize, dataUpdatedAt) <> (ImportsRow.tupled, ImportsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(accountId), Rep.Some(`type`), Rep.Some(approved), Rep.Some(createdAt), Rep.Some(updatedAt), dataFileName, dataContentType, dataFileSize, dataUpdatedAt).shaped.<>({r=>import r._; _1.map(_=> ImportsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column type SqlType(int4)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[Int] = column[Int]("type")
    /** Database column approved SqlType(bool), Default(false) */
    val approved: Rep[Boolean] = column[Boolean]("approved", O.Default(false))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column data_file_name SqlType(varchar), Default(None) */
    val dataFileName: Rep[Option[String]] = column[Option[String]]("data_file_name", O.Default(None))
    /** Database column data_content_type SqlType(varchar), Default(None) */
    val dataContentType: Rep[Option[String]] = column[Option[String]]("data_content_type", O.Default(None))
    /** Database column data_file_size SqlType(int4), Default(None) */
    val dataFileSize: Rep[Option[Int]] = column[Option[Int]]("data_file_size", O.Default(None))
    /** Database column data_updated_at SqlType(timestamp), Default(None) */
    val dataUpdatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("data_updated_at", O.Default(None))

    /** Foreign key referencing Accounts (database name fk_rails_138d41de18) */
    lazy val accountsFk = foreignKey("fk_rails_138d41de18", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Imports */
  lazy val Imports = new TableQuery(tag => new Imports(tag))

  /** Entity class storing rows of table MediaAttachments
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param statusId Database column status_id SqlType(int8), Default(None)
   *  @param fileFileName Database column file_file_name SqlType(varchar), Default(None)
   *  @param fileContentType Database column file_content_type SqlType(varchar), Default(None)
   *  @param fileFileSize Database column file_file_size SqlType(int4), Default(None)
   *  @param fileUpdatedAt Database column file_updated_at SqlType(timestamp), Default(None)
   *  @param remoteUrl Database column remote_url SqlType(varchar), Default()
   *  @param accountId Database column account_id SqlType(int8), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param shortcode Database column shortcode SqlType(varchar), Default(None)
   *  @param `type` Database column type SqlType(int4), Default(0)
   *  @param fileMeta Database column file_meta SqlType(json), Length(2147483647,false), Default(None) */
  case class MediaAttachmentsRow(id: Long, statusId: Option[Long] = None, fileFileName: Option[String] = None, fileContentType: Option[String] = None, fileFileSize: Option[Int] = None, fileUpdatedAt: Option[java.sql.Timestamp] = None, remoteUrl: String = "", accountId: Option[Long] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, shortcode: Option[String] = None, `type`: Int = 0, fileMeta: Option[String] = None)
  /** GetResult implicit for fetching MediaAttachmentsRow objects using plain SQL queries */
  implicit def GetResultMediaAttachmentsRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Option[String]], e3: GR[Option[Int]], e4: GR[Option[java.sql.Timestamp]], e5: GR[String], e6: GR[java.sql.Timestamp], e7: GR[Int]): GR[MediaAttachmentsRow] = GR{
    prs => import prs._
    MediaAttachmentsRow.tupled((<<[Long], <<?[Long], <<?[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp], <<[String], <<?[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[String], <<[Int], <<?[String]))
  }
  /** Table description of table media_attachments. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class MediaAttachments(_tableTag: Tag) extends profile.api.Table[MediaAttachmentsRow](_tableTag, "media_attachments") {
    def * = (id, statusId, fileFileName, fileContentType, fileFileSize, fileUpdatedAt, remoteUrl, accountId, createdAt, updatedAt, shortcode, `type`, fileMeta) <> (MediaAttachmentsRow.tupled, MediaAttachmentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), statusId, fileFileName, fileContentType, fileFileSize, fileUpdatedAt, Rep.Some(remoteUrl), accountId, Rep.Some(createdAt), Rep.Some(updatedAt), shortcode, Rep.Some(`type`), fileMeta).shaped.<>({r=>import r._; _1.map(_=> MediaAttachmentsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7.get, _8, _9.get, _10.get, _11, _12.get, _13)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column status_id SqlType(int8), Default(None) */
    val statusId: Rep[Option[Long]] = column[Option[Long]]("status_id", O.Default(None))
    /** Database column file_file_name SqlType(varchar), Default(None) */
    val fileFileName: Rep[Option[String]] = column[Option[String]]("file_file_name", O.Default(None))
    /** Database column file_content_type SqlType(varchar), Default(None) */
    val fileContentType: Rep[Option[String]] = column[Option[String]]("file_content_type", O.Default(None))
    /** Database column file_file_size SqlType(int4), Default(None) */
    val fileFileSize: Rep[Option[Int]] = column[Option[Int]]("file_file_size", O.Default(None))
    /** Database column file_updated_at SqlType(timestamp), Default(None) */
    val fileUpdatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("file_updated_at", O.Default(None))
    /** Database column remote_url SqlType(varchar), Default() */
    val remoteUrl: Rep[String] = column[String]("remote_url", O.Default(""))
    /** Database column account_id SqlType(int8), Default(None) */
    val accountId: Rep[Option[Long]] = column[Option[Long]]("account_id", O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column shortcode SqlType(varchar), Default(None) */
    val shortcode: Rep[Option[String]] = column[Option[String]]("shortcode", O.Default(None))
    /** Database column type SqlType(int4), Default(0)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[Int] = column[Int]("type", O.Default(0))
    /** Database column file_meta SqlType(json), Length(2147483647,false), Default(None) */
    val fileMeta: Rep[Option[String]] = column[Option[String]]("file_meta", O.Length(2147483647,varying=false), O.Default(None))

    /** Foreign key referencing Accounts (database name fk_rails_2d19cca08b) */
    lazy val accountsFk = foreignKey("fk_rails_2d19cca08b", accountId, Accounts)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.SetNull)
    /** Foreign key referencing Statuses (database name fk_rails_3ec0cfdd70) */
    lazy val statusesFk = foreignKey("fk_rails_3ec0cfdd70", statusId, Statuses)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.SetNull)

    /** Uniqueness Index over (shortcode) (database name index_media_attachments_on_shortcode) */
    val index1 = index("index_media_attachments_on_shortcode", shortcode, unique=true)
  }
  /** Collection-like TableQuery object for table MediaAttachments */
  lazy val MediaAttachments = new TableQuery(tag => new MediaAttachments(tag))

  /** Entity class storing rows of table Mentions
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8), Default(None)
   *  @param statusId Database column status_id SqlType(int8), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class MentionsRow(id: Long, accountId: Option[Long] = None, statusId: Option[Long] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching MentionsRow objects using plain SQL queries */
  implicit def GetResultMentionsRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[java.sql.Timestamp]): GR[MentionsRow] = GR{
    prs => import prs._
    MentionsRow.tupled((<<[Long], <<?[Long], <<?[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table mentions. Objects of this class serve as prototypes for rows in queries. */
  class Mentions(_tableTag: Tag) extends profile.api.Table[MentionsRow](_tableTag, "mentions") {
    def * = (id, accountId, statusId, createdAt, updatedAt) <> (MentionsRow.tupled, MentionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), accountId, statusId, Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> MentionsRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8), Default(None) */
    val accountId: Rep[Option[Long]] = column[Option[Long]]("account_id", O.Default(None))
    /** Database column status_id SqlType(int8), Default(None) */
    val statusId: Rep[Option[Long]] = column[Option[Long]]("status_id", O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Accounts (database name fk_rails_3130ff6a8f) */
    lazy val accountsFk = foreignKey("fk_rails_3130ff6a8f", accountId, Accounts)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Statuses (database name fk_rails_59edbe2887) */
    lazy val statusesFk = foreignKey("fk_rails_59edbe2887", statusId, Statuses)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,statusId) (database name index_mentions_on_account_id_and_status_id) */
    val index1 = index("index_mentions_on_account_id_and_status_id", (accountId, statusId), unique=true)
  }
  /** Collection-like TableQuery object for table Mentions */
  lazy val Mentions = new TableQuery(tag => new Mentions(tag))

  /** Entity class storing rows of table Mutes
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8)
   *  @param targetAccountId Database column target_account_id SqlType(int8)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class MutesRow(id: Long, accountId: Long, targetAccountId: Long, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching MutesRow objects using plain SQL queries */
  implicit def GetResultMutesRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp]): GR[MutesRow] = GR{
    prs => import prs._
    MutesRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table mutes. Objects of this class serve as prototypes for rows in queries. */
  class Mutes(_tableTag: Tag) extends profile.api.Table[MutesRow](_tableTag, "mutes") {
    def * = (id, accountId, targetAccountId, createdAt, updatedAt) <> (MutesRow.tupled, MutesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(accountId), Rep.Some(targetAccountId), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> MutesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column target_account_id SqlType(int8) */
    val targetAccountId: Rep[Long] = column[Long]("target_account_id")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Accounts (database name fk_rails_802b245a68) */
    lazy val accountsFk1 = foreignKey("fk_rails_802b245a68", targetAccountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Accounts (database name fk_rails_cb49248c07) */
    lazy val accountsFk2 = foreignKey("fk_rails_cb49248c07", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,targetAccountId) (database name index_mutes_on_account_id_and_target_account_id) */
    val index1 = index("index_mutes_on_account_id_and_target_account_id", (accountId, targetAccountId), unique=true)
  }
  /** Collection-like TableQuery object for table Mutes */
  lazy val Mutes = new TableQuery(tag => new Mutes(tag))

  /** Entity class storing rows of table Notifications
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8), Default(None)
   *  @param activityId Database column activity_id SqlType(int8), Default(None)
   *  @param activityType Database column activity_type SqlType(varchar), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param fromAccountId Database column from_account_id SqlType(int8), Default(None) */
  case class NotificationsRow(id: Long, accountId: Option[Long] = None, activityId: Option[Long] = None, activityType: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, fromAccountId: Option[Long] = None)
  /** GetResult implicit for fetching NotificationsRow objects using plain SQL queries */
  implicit def GetResultNotificationsRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[NotificationsRow] = GR{
    prs => import prs._
    NotificationsRow.tupled((<<[Long], <<?[Long], <<?[Long], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Long]))
  }
  /** Table description of table notifications. Objects of this class serve as prototypes for rows in queries. */
  class Notifications(_tableTag: Tag) extends profile.api.Table[NotificationsRow](_tableTag, "notifications") {
    def * = (id, accountId, activityId, activityType, createdAt, updatedAt, fromAccountId) <> (NotificationsRow.tupled, NotificationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), accountId, activityId, activityType, Rep.Some(createdAt), Rep.Some(updatedAt), fromAccountId).shaped.<>({r=>import r._; _1.map(_=> NotificationsRow.tupled((_1.get, _2, _3, _4, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8), Default(None) */
    val accountId: Rep[Option[Long]] = column[Option[Long]]("account_id", O.Default(None))
    /** Database column activity_id SqlType(int8), Default(None) */
    val activityId: Rep[Option[Long]] = column[Option[Long]]("activity_id", O.Default(None))
    /** Database column activity_type SqlType(varchar), Default(None) */
    val activityType: Rep[Option[String]] = column[Option[String]]("activity_type", O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column from_account_id SqlType(int8), Default(None) */
    val fromAccountId: Rep[Option[Long]] = column[Option[Long]]("from_account_id", O.Default(None))

    /** Foreign key referencing Accounts (database name fk_rails_1c0a19e3ee) */
    lazy val accountsFk1 = foreignKey("fk_rails_1c0a19e3ee", accountId, Accounts)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Accounts (database name fk_rails_a9df2835a1) */
    lazy val accountsFk2 = foreignKey("fk_rails_a9df2835a1", fromAccountId, Accounts)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,activityId,activityType) (database name account_activity) */
    val index1 = index("account_activity", (accountId, activityId, activityType), unique=true)
    /** Index over (activityId,activityType) (database name index_notifications_on_activity_id_and_activity_type) */
    val index2 = index("index_notifications_on_activity_id_and_activity_type", (activityId, activityType))
    /** Index over (id,accountId,activityType) (database name index_notifications_on_id_and_account_id_and_activity_type) */
    val index3 = index("index_notifications_on_id_and_account_id_and_activity_type", (id, accountId, activityType))
  }
  /** Collection-like TableQuery object for table Notifications */
  lazy val Notifications = new TableQuery(tag => new Notifications(tag))

  /** Entity class storing rows of table OauthAccessGrants
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param resourceOwnerId Database column resource_owner_id SqlType(int8)
   *  @param applicationId Database column application_id SqlType(int8)
   *  @param token Database column token SqlType(varchar)
   *  @param expiresIn Database column expires_in SqlType(int4)
   *  @param redirectUri Database column redirect_uri SqlType(text)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param revokedAt Database column revoked_at SqlType(timestamp), Default(None)
   *  @param scopes Database column scopes SqlType(varchar), Default(None) */
  case class OauthAccessGrantsRow(id: Long, resourceOwnerId: Long, applicationId: Long, token: String, expiresIn: Int, redirectUri: String, createdAt: java.sql.Timestamp, revokedAt: Option[java.sql.Timestamp] = None, scopes: Option[String] = None)
  /** GetResult implicit for fetching OauthAccessGrantsRow objects using plain SQL queries */
  implicit def GetResultOauthAccessGrantsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]], e5: GR[Option[String]]): GR[OauthAccessGrantsRow] = GR{
    prs => import prs._
    OauthAccessGrantsRow.tupled((<<[Long], <<[Long], <<[Long], <<[String], <<[Int], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table oauth_access_grants. Objects of this class serve as prototypes for rows in queries. */
  class OauthAccessGrants(_tableTag: Tag) extends profile.api.Table[OauthAccessGrantsRow](_tableTag, "oauth_access_grants") {
    def * = (id, resourceOwnerId, applicationId, token, expiresIn, redirectUri, createdAt, revokedAt, scopes) <> (OauthAccessGrantsRow.tupled, OauthAccessGrantsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(resourceOwnerId), Rep.Some(applicationId), Rep.Some(token), Rep.Some(expiresIn), Rep.Some(redirectUri), Rep.Some(createdAt), revokedAt, scopes).shaped.<>({r=>import r._; _1.map(_=> OauthAccessGrantsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column resource_owner_id SqlType(int8) */
    val resourceOwnerId: Rep[Long] = column[Long]("resource_owner_id")
    /** Database column application_id SqlType(int8) */
    val applicationId: Rep[Long] = column[Long]("application_id")
    /** Database column token SqlType(varchar) */
    val token: Rep[String] = column[String]("token")
    /** Database column expires_in SqlType(int4) */
    val expiresIn: Rep[Int] = column[Int]("expires_in")
    /** Database column redirect_uri SqlType(text) */
    val redirectUri: Rep[String] = column[String]("redirect_uri")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column revoked_at SqlType(timestamp), Default(None) */
    val revokedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("revoked_at", O.Default(None))
    /** Database column scopes SqlType(varchar), Default(None) */
    val scopes: Rep[Option[String]] = column[Option[String]]("scopes", O.Default(None))

    /** Foreign key referencing OauthApplications (database name fk_rails_b4b53e07b8) */
    lazy val oauthApplicationsFk = foreignKey("fk_rails_b4b53e07b8", applicationId, OauthApplications)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Users (database name fk_rails_330c32d8d9) */
    lazy val usersFk = foreignKey("fk_rails_330c32d8d9", resourceOwnerId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (token) (database name index_oauth_access_grants_on_token) */
    val index1 = index("index_oauth_access_grants_on_token", token, unique=true)
  }
  /** Collection-like TableQuery object for table OauthAccessGrants */
  lazy val OauthAccessGrants = new TableQuery(tag => new OauthAccessGrants(tag))

  /** Entity class storing rows of table OauthAccessTokens
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param resourceOwnerId Database column resource_owner_id SqlType(int8), Default(None)
   *  @param applicationId Database column application_id SqlType(int8), Default(None)
   *  @param token Database column token SqlType(varchar)
   *  @param refreshToken Database column refresh_token SqlType(varchar), Default(None)
   *  @param expiresIn Database column expires_in SqlType(int4), Default(None)
   *  @param revokedAt Database column revoked_at SqlType(timestamp), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param scopes Database column scopes SqlType(varchar), Default(None) */
  case class OauthAccessTokensRow(id: Long, resourceOwnerId: Option[Long] = None, applicationId: Option[Long] = None, token: String, refreshToken: Option[String] = None, expiresIn: Option[Int] = None, revokedAt: Option[java.sql.Timestamp] = None, createdAt: java.sql.Timestamp, scopes: Option[String] = None)
  /** GetResult implicit for fetching OauthAccessTokensRow objects using plain SQL queries */
  implicit def GetResultOauthAccessTokensRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[String], e3: GR[Option[String]], e4: GR[Option[Int]], e5: GR[Option[java.sql.Timestamp]], e6: GR[java.sql.Timestamp]): GR[OauthAccessTokensRow] = GR{
    prs => import prs._
    OauthAccessTokensRow.tupled((<<[Long], <<?[Long], <<?[Long], <<[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table oauth_access_tokens. Objects of this class serve as prototypes for rows in queries. */
  class OauthAccessTokens(_tableTag: Tag) extends profile.api.Table[OauthAccessTokensRow](_tableTag, "oauth_access_tokens") {
    def * = (id, resourceOwnerId, applicationId, token, refreshToken, expiresIn, revokedAt, createdAt, scopes) <> (OauthAccessTokensRow.tupled, OauthAccessTokensRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), resourceOwnerId, applicationId, Rep.Some(token), refreshToken, expiresIn, revokedAt, Rep.Some(createdAt), scopes).shaped.<>({r=>import r._; _1.map(_=> OauthAccessTokensRow.tupled((_1.get, _2, _3, _4.get, _5, _6, _7, _8.get, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column resource_owner_id SqlType(int8), Default(None) */
    val resourceOwnerId: Rep[Option[Long]] = column[Option[Long]]("resource_owner_id", O.Default(None))
    /** Database column application_id SqlType(int8), Default(None) */
    val applicationId: Rep[Option[Long]] = column[Option[Long]]("application_id", O.Default(None))
    /** Database column token SqlType(varchar) */
    val token: Rep[String] = column[String]("token")
    /** Database column refresh_token SqlType(varchar), Default(None) */
    val refreshToken: Rep[Option[String]] = column[Option[String]]("refresh_token", O.Default(None))
    /** Database column expires_in SqlType(int4), Default(None) */
    val expiresIn: Rep[Option[Int]] = column[Option[Int]]("expires_in", O.Default(None))
    /** Database column revoked_at SqlType(timestamp), Default(None) */
    val revokedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("revoked_at", O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column scopes SqlType(varchar), Default(None) */
    val scopes: Rep[Option[String]] = column[Option[String]]("scopes", O.Default(None))

    /** Foreign key referencing OauthApplications (database name fk_rails_732cb83ab7) */
    lazy val oauthApplicationsFk = foreignKey("fk_rails_732cb83ab7", applicationId, OauthApplications)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Users (database name fk_rails_ee63f25419) */
    lazy val usersFk = foreignKey("fk_rails_ee63f25419", resourceOwnerId, Users)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (refreshToken) (database name index_oauth_access_tokens_on_refresh_token) */
    val index1 = index("index_oauth_access_tokens_on_refresh_token", refreshToken, unique=true)
    /** Uniqueness Index over (token) (database name index_oauth_access_tokens_on_token) */
    val index2 = index("index_oauth_access_tokens_on_token", token, unique=true)
  }
  /** Collection-like TableQuery object for table OauthAccessTokens */
  lazy val OauthAccessTokens = new TableQuery(tag => new OauthAccessTokens(tag))

  /** Entity class storing rows of table OauthApplications
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar)
   *  @param uid Database column uid SqlType(varchar)
   *  @param secret Database column secret SqlType(varchar)
   *  @param redirectUri Database column redirect_uri SqlType(text)
   *  @param scopes Database column scopes SqlType(varchar), Default()
   *  @param createdAt Database column created_at SqlType(timestamp), Default(None)
   *  @param updatedAt Database column updated_at SqlType(timestamp), Default(None)
   *  @param superapp Database column superapp SqlType(bool), Default(false)
   *  @param website Database column website SqlType(varchar), Default(None)
   *  @param ownerId Database column owner_id SqlType(int8), Default(None)
   *  @param ownerType Database column owner_type SqlType(varchar), Default(None) */
  case class OauthApplicationsRow(id: Long, name: String, uid: String, secret: String, redirectUri: String, scopes: String = "", createdAt: Option[java.sql.Timestamp] = None, updatedAt: Option[java.sql.Timestamp] = None, superapp: Boolean = false, website: Option[String] = None, ownerId: Option[Long] = None, ownerType: Option[String] = None)
  /** GetResult implicit for fetching OauthApplicationsRow objects using plain SQL queries */
  implicit def GetResultOauthApplicationsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[java.sql.Timestamp]], e3: GR[Boolean], e4: GR[Option[String]], e5: GR[Option[Long]]): GR[OauthApplicationsRow] = GR{
    prs => import prs._
    OauthApplicationsRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<[Boolean], <<?[String], <<?[Long], <<?[String]))
  }
  /** Table description of table oauth_applications. Objects of this class serve as prototypes for rows in queries. */
  class OauthApplications(_tableTag: Tag) extends profile.api.Table[OauthApplicationsRow](_tableTag, "oauth_applications") {
    def * = (id, name, uid, secret, redirectUri, scopes, createdAt, updatedAt, superapp, website, ownerId, ownerType) <> (OauthApplicationsRow.tupled, OauthApplicationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(uid), Rep.Some(secret), Rep.Some(redirectUri), Rep.Some(scopes), createdAt, updatedAt, Rep.Some(superapp), website, ownerId, ownerType).shaped.<>({r=>import r._; _1.map(_=> OauthApplicationsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8, _9.get, _10, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar) */
    val name: Rep[String] = column[String]("name")
    /** Database column uid SqlType(varchar) */
    val uid: Rep[String] = column[String]("uid")
    /** Database column secret SqlType(varchar) */
    val secret: Rep[String] = column[String]("secret")
    /** Database column redirect_uri SqlType(text) */
    val redirectUri: Rep[String] = column[String]("redirect_uri")
    /** Database column scopes SqlType(varchar), Default() */
    val scopes: Rep[String] = column[String]("scopes", O.Default(""))
    /** Database column created_at SqlType(timestamp), Default(None) */
    val createdAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    /** Database column updated_at SqlType(timestamp), Default(None) */
    val updatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated_at", O.Default(None))
    /** Database column superapp SqlType(bool), Default(false) */
    val superapp: Rep[Boolean] = column[Boolean]("superapp", O.Default(false))
    /** Database column website SqlType(varchar), Default(None) */
    val website: Rep[Option[String]] = column[Option[String]]("website", O.Default(None))
    /** Database column owner_id SqlType(int8), Default(None) */
    val ownerId: Rep[Option[Long]] = column[Option[Long]]("owner_id", O.Default(None))
    /** Database column owner_type SqlType(varchar), Default(None) */
    val ownerType: Rep[Option[String]] = column[Option[String]]("owner_type", O.Default(None))

    /** Foreign key referencing Users (database name fk_rails_cc886e315a) */
    lazy val usersFk = foreignKey("fk_rails_cc886e315a", ownerId, Users)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Index over (ownerId,ownerType) (database name index_oauth_applications_on_owner_id_and_owner_type) */
    val index1 = index("index_oauth_applications_on_owner_id_and_owner_type", (ownerId, ownerType))
    /** Uniqueness Index over (uid) (database name index_oauth_applications_on_uid) */
    val index2 = index("index_oauth_applications_on_uid", uid, unique=true)
  }
  /** Collection-like TableQuery object for table OauthApplications */
  lazy val OauthApplications = new TableQuery(tag => new OauthApplications(tag))

  /** Entity class storing rows of table PreviewCards
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param url Database column url SqlType(varchar), Default()
   *  @param title Database column title SqlType(varchar), Default()
   *  @param description Database column description SqlType(varchar), Default()
   *  @param imageFileName Database column image_file_name SqlType(varchar), Default(None)
   *  @param imageContentType Database column image_content_type SqlType(varchar), Default(None)
   *  @param imageFileSize Database column image_file_size SqlType(int4), Default(None)
   *  @param imageUpdatedAt Database column image_updated_at SqlType(timestamp), Default(None)
   *  @param `type` Database column type SqlType(int4), Default(0)
   *  @param html Database column html SqlType(text), Default()
   *  @param authorName Database column author_name SqlType(varchar), Default()
   *  @param authorUrl Database column author_url SqlType(varchar), Default()
   *  @param providerName Database column provider_name SqlType(varchar), Default()
   *  @param providerUrl Database column provider_url SqlType(varchar), Default()
   *  @param width Database column width SqlType(int4), Default(0)
   *  @param height Database column height SqlType(int4), Default(0)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class PreviewCardsRow(id: Long, url: String = "", title: String = "", description: String = "", imageFileName: Option[String] = None, imageContentType: Option[String] = None, imageFileSize: Option[Int] = None, imageUpdatedAt: Option[java.sql.Timestamp] = None, `type`: Int = 0, html: String = "", authorName: String = "", authorUrl: String = "", providerName: String = "", providerUrl: String = "", width: Int = 0, height: Int = 0, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching PreviewCardsRow objects using plain SQL queries */
  implicit def GetResultPreviewCardsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Int]], e4: GR[Option[java.sql.Timestamp]], e5: GR[Int], e6: GR[java.sql.Timestamp]): GR[PreviewCardsRow] = GR{
    prs => import prs._
    PreviewCardsRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<?[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp], <<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table preview_cards. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class PreviewCards(_tableTag: Tag) extends profile.api.Table[PreviewCardsRow](_tableTag, "preview_cards") {
    def * = (id, url, title, description, imageFileName, imageContentType, imageFileSize, imageUpdatedAt, `type`, html, authorName, authorUrl, providerName, providerUrl, width, height, createdAt, updatedAt) <> (PreviewCardsRow.tupled, PreviewCardsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(url), Rep.Some(title), Rep.Some(description), imageFileName, imageContentType, imageFileSize, imageUpdatedAt, Rep.Some(`type`), Rep.Some(html), Rep.Some(authorName), Rep.Some(authorUrl), Rep.Some(providerName), Rep.Some(providerUrl), Rep.Some(width), Rep.Some(height), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> PreviewCardsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6, _7, _8, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get, _15.get, _16.get, _17.get, _18.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column url SqlType(varchar), Default() */
    val url: Rep[String] = column[String]("url", O.Default(""))
    /** Database column title SqlType(varchar), Default() */
    val title: Rep[String] = column[String]("title", O.Default(""))
    /** Database column description SqlType(varchar), Default() */
    val description: Rep[String] = column[String]("description", O.Default(""))
    /** Database column image_file_name SqlType(varchar), Default(None) */
    val imageFileName: Rep[Option[String]] = column[Option[String]]("image_file_name", O.Default(None))
    /** Database column image_content_type SqlType(varchar), Default(None) */
    val imageContentType: Rep[Option[String]] = column[Option[String]]("image_content_type", O.Default(None))
    /** Database column image_file_size SqlType(int4), Default(None) */
    val imageFileSize: Rep[Option[Int]] = column[Option[Int]]("image_file_size", O.Default(None))
    /** Database column image_updated_at SqlType(timestamp), Default(None) */
    val imageUpdatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("image_updated_at", O.Default(None))
    /** Database column type SqlType(int4), Default(0)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[Int] = column[Int]("type", O.Default(0))
    /** Database column html SqlType(text), Default() */
    val html: Rep[String] = column[String]("html", O.Default(""))
    /** Database column author_name SqlType(varchar), Default() */
    val authorName: Rep[String] = column[String]("author_name", O.Default(""))
    /** Database column author_url SqlType(varchar), Default() */
    val authorUrl: Rep[String] = column[String]("author_url", O.Default(""))
    /** Database column provider_name SqlType(varchar), Default() */
    val providerName: Rep[String] = column[String]("provider_name", O.Default(""))
    /** Database column provider_url SqlType(varchar), Default() */
    val providerUrl: Rep[String] = column[String]("provider_url", O.Default(""))
    /** Database column width SqlType(int4), Default(0) */
    val width: Rep[Int] = column[Int]("width", O.Default(0))
    /** Database column height SqlType(int4), Default(0) */
    val height: Rep[Int] = column[Int]("height", O.Default(0))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Uniqueness Index over (url) (database name index_preview_cards_on_url) */
    val index1 = index("index_preview_cards_on_url", url, unique=true)
  }
  /** Collection-like TableQuery object for table PreviewCards */
  lazy val PreviewCards = new TableQuery(tag => new PreviewCards(tag))

  /** Entity class storing rows of table PreviewCardsStatuses
   *  @param previewCardId Database column preview_card_id SqlType(int8)
   *  @param statusId Database column status_id SqlType(int8) */
  case class PreviewCardsStatusesRow(previewCardId: Long, statusId: Long)
  /** GetResult implicit for fetching PreviewCardsStatusesRow objects using plain SQL queries */
  implicit def GetResultPreviewCardsStatusesRow(implicit e0: GR[Long]): GR[PreviewCardsStatusesRow] = GR{
    prs => import prs._
    PreviewCardsStatusesRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table preview_cards_statuses. Objects of this class serve as prototypes for rows in queries. */
  class PreviewCardsStatuses(_tableTag: Tag) extends profile.api.Table[PreviewCardsStatusesRow](_tableTag, "preview_cards_statuses") {
    def * = (previewCardId, statusId) <> (PreviewCardsStatusesRow.tupled, PreviewCardsStatusesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(previewCardId), Rep.Some(statusId)).shaped.<>({r=>import r._; _1.map(_=> PreviewCardsStatusesRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column preview_card_id SqlType(int8) */
    val previewCardId: Rep[Long] = column[Long]("preview_card_id")
    /** Database column status_id SqlType(int8) */
    val statusId: Rep[Long] = column[Long]("status_id")

    /** Index over (statusId,previewCardId) (database name index_preview_cards_statuses_on_status_id_and_preview_card_id) */
    val index1 = index("index_preview_cards_statuses_on_status_id_and_preview_card_id", (statusId, previewCardId))
  }
  /** Collection-like TableQuery object for table PreviewCardsStatuses */
  lazy val PreviewCardsStatuses = new TableQuery(tag => new PreviewCardsStatuses(tag))

  /** Entity class storing rows of table Reports
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8)
   *  @param targetAccountId Database column target_account_id SqlType(int8)
   *  @param statusIds Database column status_ids SqlType(_int8), Length(19,false)
   *  @param comment Database column comment SqlType(text), Default()
   *  @param actionTaken Database column action_taken SqlType(bool), Default(false)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param actionTakenByAccountId Database column action_taken_by_account_id SqlType(int8), Default(None) */
  case class ReportsRow(id: Long, accountId: Long, targetAccountId: Long, statusIds: String, comment: String = "", actionTaken: Boolean = false, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, actionTakenByAccountId: Option[Long] = None)
  /** GetResult implicit for fetching ReportsRow objects using plain SQL queries */
  implicit def GetResultReportsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Boolean], e3: GR[java.sql.Timestamp], e4: GR[Option[Long]]): GR[ReportsRow] = GR{
    prs => import prs._
    ReportsRow.tupled((<<[Long], <<[Long], <<[Long], <<[String], <<[String], <<[Boolean], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Long]))
  }
  /** Table description of table reports. Objects of this class serve as prototypes for rows in queries. */
  class Reports(_tableTag: Tag) extends profile.api.Table[ReportsRow](_tableTag, "reports") {
    def * = (id, accountId, targetAccountId, statusIds, comment, actionTaken, createdAt, updatedAt, actionTakenByAccountId) <> (ReportsRow.tupled, ReportsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(accountId), Rep.Some(targetAccountId), Rep.Some(statusIds), Rep.Some(comment), Rep.Some(actionTaken), Rep.Some(createdAt), Rep.Some(updatedAt), actionTakenByAccountId).shaped.<>({r=>import r._; _1.map(_=> ReportsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column target_account_id SqlType(int8) */
    val targetAccountId: Rep[Long] = column[Long]("target_account_id")
    /** Database column status_ids SqlType(_int8), Length(19,false) */
    val statusIds: Rep[String] = column[String]("status_ids", O.Length(19,varying=false))
    /** Database column comment SqlType(text), Default() */
    val comment: Rep[String] = column[String]("comment", O.Default(""))
    /** Database column action_taken SqlType(bool), Default(false) */
    val actionTaken: Rep[Boolean] = column[Boolean]("action_taken", O.Default(false))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column action_taken_by_account_id SqlType(int8), Default(None) */
    val actionTakenByAccountId: Rep[Option[Long]] = column[Option[Long]]("action_taken_by_account_id", O.Default(None))

    /** Foreign key referencing Accounts (database name fk_rails_126d07513e) */
    lazy val accountsFk1 = foreignKey("fk_rails_126d07513e", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Accounts (database name fk_rails_66528197fd) */
    lazy val accountsFk2 = foreignKey("fk_rails_66528197fd", targetAccountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Accounts (database name fk_rails_e8e172880e) */
    lazy val accountsFk3 = foreignKey("fk_rails_e8e172880e", actionTakenByAccountId, Accounts)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.SetNull)
  }
  /** Collection-like TableQuery object for table Reports */
  lazy val Reports = new TableQuery(tag => new Reports(tag))

  /** Entity class storing rows of table SchemaMigrations
   *  @param version Database column version SqlType(varchar), PrimaryKey */
  case class SchemaMigrationsRow(version: String)
  /** GetResult implicit for fetching SchemaMigrationsRow objects using plain SQL queries */
  implicit def GetResultSchemaMigrationsRow(implicit e0: GR[String]): GR[SchemaMigrationsRow] = GR{
    prs => import prs._
    SchemaMigrationsRow(<<[String])
  }
  /** Table description of table schema_migrations. Objects of this class serve as prototypes for rows in queries. */
  class SchemaMigrations(_tableTag: Tag) extends profile.api.Table[SchemaMigrationsRow](_tableTag, "schema_migrations") {
    def * = version <> (SchemaMigrationsRow.apply, SchemaMigrationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = Rep.Some(version).shaped.<>(r => r.map(_=> SchemaMigrationsRow(r.get)), (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column version SqlType(varchar), PrimaryKey */
    val version: Rep[String] = column[String]("version", O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table SchemaMigrations */
  lazy val SchemaMigrations = new TableQuery(tag => new SchemaMigrations(tag))

  /** Entity class storing rows of table SessionActivations
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(int8)
   *  @param sessionId Database column session_id SqlType(varchar)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param userAgent Database column user_agent SqlType(varchar), Default()
   *  @param ip Database column ip SqlType(inet), Length(2147483647,false), Default(None)
   *  @param accessTokenId Database column access_token_id SqlType(int8), Default(None)
   *  @param webPushSubscriptionId Database column web_push_subscription_id SqlType(int8), Default(None) */
  case class SessionActivationsRow(id: Long, userId: Long, sessionId: String, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, userAgent: String = "", ip: Option[String] = None, accessTokenId: Option[Long] = None, webPushSubscriptionId: Option[Long] = None)
  /** GetResult implicit for fetching SessionActivationsRow objects using plain SQL queries */
  implicit def GetResultSessionActivationsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]], e4: GR[Option[Long]]): GR[SessionActivationsRow] = GR{
    prs => import prs._
    SessionActivationsRow.tupled((<<[Long], <<[Long], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[String], <<?[String], <<?[Long], <<?[Long]))
  }
  /** Table description of table session_activations. Objects of this class serve as prototypes for rows in queries. */
  class SessionActivations(_tableTag: Tag) extends profile.api.Table[SessionActivationsRow](_tableTag, "session_activations") {
    def * = (id, userId, sessionId, createdAt, updatedAt, userAgent, ip, accessTokenId, webPushSubscriptionId) <> (SessionActivationsRow.tupled, SessionActivationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(sessionId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(userAgent), ip, accessTokenId, webPushSubscriptionId).shaped.<>({r=>import r._; _1.map(_=> SessionActivationsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(int8) */
    val userId: Rep[Long] = column[Long]("user_id")
    /** Database column session_id SqlType(varchar) */
    val sessionId: Rep[String] = column[String]("session_id")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column user_agent SqlType(varchar), Default() */
    val userAgent: Rep[String] = column[String]("user_agent", O.Default(""))
    /** Database column ip SqlType(inet), Length(2147483647,false), Default(None) */
    val ip: Rep[Option[String]] = column[Option[String]]("ip", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column access_token_id SqlType(int8), Default(None) */
    val accessTokenId: Rep[Option[Long]] = column[Option[Long]]("access_token_id", O.Default(None))
    /** Database column web_push_subscription_id SqlType(int8), Default(None) */
    val webPushSubscriptionId: Rep[Option[Long]] = column[Option[Long]]("web_push_subscription_id", O.Default(None))

    /** Foreign key referencing OauthAccessTokens (database name fk_rails_a0d14b4784) */
    lazy val oauthAccessTokensFk = foreignKey("fk_rails_a0d14b4784", accessTokenId, OauthAccessTokens)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Users (database name fk_rails_dd343c7f5b) */
    lazy val usersFk = foreignKey("fk_rails_dd343c7f5b", userId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (sessionId) (database name index_session_activations_on_session_id) */
    val index1 = index("index_session_activations_on_session_id", sessionId, unique=true)
  }
  /** Collection-like TableQuery object for table SessionActivations */
  lazy val SessionActivations = new TableQuery(tag => new SessionActivations(tag))

  /** Entity class storing rows of table Settings
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param `var` Database column var SqlType(varchar)
   *  @param value Database column value SqlType(text), Default(None)
   *  @param thingType Database column thing_type SqlType(varchar), Default(None)
   *  @param thingId Database column thing_id SqlType(int8), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp), Default(None)
   *  @param updatedAt Database column updated_at SqlType(timestamp), Default(None) */
  case class SettingsRow(id: Long, `var`: String, value: Option[String] = None, thingType: Option[String] = None, thingId: Option[Long] = None, createdAt: Option[java.sql.Timestamp] = None, updatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching SettingsRow objects using plain SQL queries */
  implicit def GetResultSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Long]], e4: GR[Option[java.sql.Timestamp]]): GR[SettingsRow] = GR{
    prs => import prs._
    SettingsRow.tupled((<<[Long], <<[String], <<?[String], <<?[String], <<?[Long], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table settings. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: var */
  class Settings(_tableTag: Tag) extends profile.api.Table[SettingsRow](_tableTag, "settings") {
    def * = (id, `var`, value, thingType, thingId, createdAt, updatedAt) <> (SettingsRow.tupled, SettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(`var`), value, thingType, thingId, createdAt, updatedAt).shaped.<>({r=>import r._; _1.map(_=> SettingsRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column var SqlType(varchar)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `var`: Rep[String] = column[String]("var")
    /** Database column value SqlType(text), Default(None) */
    val value: Rep[Option[String]] = column[Option[String]]("value", O.Default(None))
    /** Database column thing_type SqlType(varchar), Default(None) */
    val thingType: Rep[Option[String]] = column[Option[String]]("thing_type", O.Default(None))
    /** Database column thing_id SqlType(int8), Default(None) */
    val thingId: Rep[Option[Long]] = column[Option[Long]]("thing_id", O.Default(None))
    /** Database column created_at SqlType(timestamp), Default(None) */
    val createdAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    /** Database column updated_at SqlType(timestamp), Default(None) */
    val updatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated_at", O.Default(None))

    /** Uniqueness Index over (thingType,thingId,`var`) (database name index_settings_on_thing_type_and_thing_id_and_var) */
    val index1 = index("index_settings_on_thing_type_and_thing_id_and_var", (thingType, thingId, `var`), unique=true)
  }
  /** Collection-like TableQuery object for table Settings */
  lazy val Settings = new TableQuery(tag => new Settings(tag))

  /** Entity class storing rows of table SiteUploads
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param `var` Database column var SqlType(varchar), Default()
   *  @param fileFileName Database column file_file_name SqlType(varchar), Default(None)
   *  @param fileContentType Database column file_content_type SqlType(varchar), Default(None)
   *  @param fileFileSize Database column file_file_size SqlType(int4), Default(None)
   *  @param fileUpdatedAt Database column file_updated_at SqlType(timestamp), Default(None)
   *  @param meta Database column meta SqlType(json), Length(2147483647,false), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class SiteUploadsRow(id: Long, `var`: String = "", fileFileName: Option[String] = None, fileContentType: Option[String] = None, fileFileSize: Option[Int] = None, fileUpdatedAt: Option[java.sql.Timestamp] = None, meta: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching SiteUploadsRow objects using plain SQL queries */
  implicit def GetResultSiteUploadsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Int]], e4: GR[Option[java.sql.Timestamp]], e5: GR[java.sql.Timestamp]): GR[SiteUploadsRow] = GR{
    prs => import prs._
    SiteUploadsRow.tupled((<<[Long], <<[String], <<?[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table site_uploads. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: var */
  class SiteUploads(_tableTag: Tag) extends profile.api.Table[SiteUploadsRow](_tableTag, "site_uploads") {
    def * = (id, `var`, fileFileName, fileContentType, fileFileSize, fileUpdatedAt, meta, createdAt, updatedAt) <> (SiteUploadsRow.tupled, SiteUploadsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(`var`), fileFileName, fileContentType, fileFileSize, fileUpdatedAt, meta, Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> SiteUploadsRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column var SqlType(varchar), Default()
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `var`: Rep[String] = column[String]("var", O.Default(""))
    /** Database column file_file_name SqlType(varchar), Default(None) */
    val fileFileName: Rep[Option[String]] = column[Option[String]]("file_file_name", O.Default(None))
    /** Database column file_content_type SqlType(varchar), Default(None) */
    val fileContentType: Rep[Option[String]] = column[Option[String]]("file_content_type", O.Default(None))
    /** Database column file_file_size SqlType(int4), Default(None) */
    val fileFileSize: Rep[Option[Int]] = column[Option[Int]]("file_file_size", O.Default(None))
    /** Database column file_updated_at SqlType(timestamp), Default(None) */
    val fileUpdatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("file_updated_at", O.Default(None))
    /** Database column meta SqlType(json), Length(2147483647,false), Default(None) */
    val meta: Rep[Option[String]] = column[Option[String]]("meta", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Uniqueness Index over (`var`) (database name index_site_uploads_on_var) */
    val index1 = index("index_site_uploads_on_var", `var`, unique=true)
  }
  /** Collection-like TableQuery object for table SiteUploads */
  lazy val SiteUploads = new TableQuery(tag => new SiteUploads(tag))

  /** Entity class storing rows of table Statuses
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param uri Database column uri SqlType(varchar), Default(None)
   *  @param accountId Database column account_id SqlType(int8)
   *  @param text Database column text SqlType(text), Default()
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param inReplyToId Database column in_reply_to_id SqlType(int8), Default(None)
   *  @param reblogOfId Database column reblog_of_id SqlType(int8), Default(None)
   *  @param url Database column url SqlType(varchar), Default(None)
   *  @param sensitive Database column sensitive SqlType(bool), Default(false)
   *  @param visibility Database column visibility SqlType(int4), Default(0)
   *  @param inReplyToAccountId Database column in_reply_to_account_id SqlType(int8), Default(None)
   *  @param applicationId Database column application_id SqlType(int8), Default(None)
   *  @param spoilerText Database column spoiler_text SqlType(text), Default()
   *  @param reply Database column reply SqlType(bool), Default(false)
   *  @param favouritesCount Database column favourites_count SqlType(int4), Default(0)
   *  @param reblogsCount Database column reblogs_count SqlType(int4), Default(0)
   *  @param language Database column language SqlType(varchar), Default(None)
   *  @param conversationId Database column conversation_id SqlType(int8), Default(None)
   *  @param local Database column local SqlType(bool), Default(None) */
  case class StatusesRow(id: Long, uri: Option[String] = None, accountId: Long, text: String = "", createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, inReplyToId: Option[Long] = None, reblogOfId: Option[Long] = None, url: Option[String] = None, sensitive: Boolean = false, visibility: Int = 0, inReplyToAccountId: Option[Long] = None, applicationId: Option[Long] = None, spoilerText: String = "", reply: Boolean = false, favouritesCount: Int = 0, reblogsCount: Int = 0, language: Option[String] = None, conversationId: Option[Long] = None, local: Option[Boolean] = None)
  /** GetResult implicit for fetching StatusesRow objects using plain SQL queries */
  implicit def GetResultStatusesRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[String], e3: GR[java.sql.Timestamp], e4: GR[Option[Long]], e5: GR[Boolean], e6: GR[Int], e7: GR[Option[Boolean]]): GR[StatusesRow] = GR{
    prs => import prs._
    StatusesRow.tupled((<<[Long], <<?[String], <<[Long], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Long], <<?[Long], <<?[String], <<[Boolean], <<[Int], <<?[Long], <<?[Long], <<[String], <<[Boolean], <<[Int], <<[Int], <<?[String], <<?[Long], <<?[Boolean]))
  }
  /** Table description of table statuses. Objects of this class serve as prototypes for rows in queries. */
  class Statuses(_tableTag: Tag) extends profile.api.Table[StatusesRow](_tableTag, "statuses") {
    def * = (id, uri, accountId, text, createdAt, updatedAt, inReplyToId, reblogOfId, url, sensitive, visibility, inReplyToAccountId, applicationId, spoilerText, reply, favouritesCount, reblogsCount, language, conversationId, local) <> (StatusesRow.tupled, StatusesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), uri, Rep.Some(accountId), Rep.Some(text), Rep.Some(createdAt), Rep.Some(updatedAt), inReplyToId, reblogOfId, url, Rep.Some(sensitive), Rep.Some(visibility), inReplyToAccountId, applicationId, Rep.Some(spoilerText), Rep.Some(reply), Rep.Some(favouritesCount), Rep.Some(reblogsCount), language, conversationId, local).shaped.<>({r=>import r._; _1.map(_=> StatusesRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6.get, _7, _8, _9, _10.get, _11.get, _12, _13, _14.get, _15.get, _16.get, _17.get, _18, _19, _20)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column uri SqlType(varchar), Default(None) */
    val uri: Rep[Option[String]] = column[Option[String]]("uri", O.Default(None))
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column text SqlType(text), Default() */
    val text: Rep[String] = column[String]("text", O.Default(""))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column in_reply_to_id SqlType(int8), Default(None) */
    val inReplyToId: Rep[Option[Long]] = column[Option[Long]]("in_reply_to_id", O.Default(None))
    /** Database column reblog_of_id SqlType(int8), Default(None) */
    val reblogOfId: Rep[Option[Long]] = column[Option[Long]]("reblog_of_id", O.Default(None))
    /** Database column url SqlType(varchar), Default(None) */
    val url: Rep[Option[String]] = column[Option[String]]("url", O.Default(None))
    /** Database column sensitive SqlType(bool), Default(false) */
    val sensitive: Rep[Boolean] = column[Boolean]("sensitive", O.Default(false))
    /** Database column visibility SqlType(int4), Default(0) */
    val visibility: Rep[Int] = column[Int]("visibility", O.Default(0))
    /** Database column in_reply_to_account_id SqlType(int8), Default(None) */
    val inReplyToAccountId: Rep[Option[Long]] = column[Option[Long]]("in_reply_to_account_id", O.Default(None))
    /** Database column application_id SqlType(int8), Default(None) */
    val applicationId: Rep[Option[Long]] = column[Option[Long]]("application_id", O.Default(None))
    /** Database column spoiler_text SqlType(text), Default() */
    val spoilerText: Rep[String] = column[String]("spoiler_text", O.Default(""))
    /** Database column reply SqlType(bool), Default(false) */
    val reply: Rep[Boolean] = column[Boolean]("reply", O.Default(false))
    /** Database column favourites_count SqlType(int4), Default(0) */
    val favouritesCount: Rep[Int] = column[Int]("favourites_count", O.Default(0))
    /** Database column reblogs_count SqlType(int4), Default(0) */
    val reblogsCount: Rep[Int] = column[Int]("reblogs_count", O.Default(0))
    /** Database column language SqlType(varchar), Default(None) */
    val language: Rep[Option[String]] = column[Option[String]]("language", O.Default(None))
    /** Database column conversation_id SqlType(int8), Default(None) */
    val conversationId: Rep[Option[Long]] = column[Option[Long]]("conversation_id", O.Default(None))
    /** Database column local SqlType(bool), Default(None) */
    val local: Rep[Option[Boolean]] = column[Option[Boolean]]("local", O.Default(None))

    /** Foreign key referencing Accounts (database name fk_rails_1a7bc3aca9) */
    lazy val accountsFk1 = foreignKey("fk_rails_1a7bc3aca9", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Accounts (database name fk_rails_5904f5f441) */
    lazy val accountsFk2 = foreignKey("fk_rails_5904f5f441", inReplyToAccountId, Accounts)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.SetNull)
    /** Foreign key referencing Statuses (database name fk_rails_256483a9ab) */
    lazy val statusesFk3 = foreignKey("fk_rails_256483a9ab", reblogOfId, Statuses)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Statuses (database name fk_rails_94a6f70399) */
    lazy val statusesFk4 = foreignKey("fk_rails_94a6f70399", inReplyToId, Statuses)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.SetNull)

    /** Index over (accountId,id) (database name index_statuses_on_account_id_id) */
    val index1 = index("index_statuses_on_account_id_id", (accountId, id))
    /** Index over (conversationId) (database name index_statuses_on_conversation_id) */
    val index2 = index("index_statuses_on_conversation_id", conversationId)
    /** Uniqueness Index over (uri) (database name index_statuses_on_uri) */
    val index3 = index("index_statuses_on_uri", uri, unique=true)
  }
  /** Collection-like TableQuery object for table Statuses */
  lazy val Statuses = new TableQuery(tag => new Statuses(tag))

  /** Entity class storing rows of table StatusesTags
   *  @param statusId Database column status_id SqlType(int8)
   *  @param tagId Database column tag_id SqlType(int8) */
  case class StatusesTagsRow(statusId: Long, tagId: Long)
  /** GetResult implicit for fetching StatusesTagsRow objects using plain SQL queries */
  implicit def GetResultStatusesTagsRow(implicit e0: GR[Long]): GR[StatusesTagsRow] = GR{
    prs => import prs._
    StatusesTagsRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table statuses_tags. Objects of this class serve as prototypes for rows in queries. */
  class StatusesTags(_tableTag: Tag) extends profile.api.Table[StatusesTagsRow](_tableTag, "statuses_tags") {
    def * = (statusId, tagId) <> (StatusesTagsRow.tupled, StatusesTagsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(statusId), Rep.Some(tagId)).shaped.<>({r=>import r._; _1.map(_=> StatusesTagsRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column status_id SqlType(int8) */
    val statusId: Rep[Long] = column[Long]("status_id")
    /** Database column tag_id SqlType(int8) */
    val tagId: Rep[Long] = column[Long]("tag_id")

    /** Foreign key referencing Statuses (database name fk_rails_df0fe11427) */
    lazy val statusesFk = foreignKey("fk_rails_df0fe11427", statusId, Statuses)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Tags (database name fk_rails_623fc2c83d) */
    lazy val tagsFk = foreignKey("fk_rails_623fc2c83d", tagId, Tags)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (tagId,statusId) (database name index_statuses_tags_on_tag_id_and_status_id) */
    val index1 = index("index_statuses_tags_on_tag_id_and_status_id", (tagId, statusId), unique=true)
  }
  /** Collection-like TableQuery object for table StatusesTags */
  lazy val StatusesTags = new TableQuery(tag => new StatusesTags(tag))

  /** Entity class storing rows of table StatusPins
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8)
   *  @param statusId Database column status_id SqlType(int8)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class StatusPinsRow(id: Long, accountId: Long, statusId: Long, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching StatusPinsRow objects using plain SQL queries */
  implicit def GetResultStatusPinsRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp]): GR[StatusPinsRow] = GR{
    prs => import prs._
    StatusPinsRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table status_pins. Objects of this class serve as prototypes for rows in queries. */
  class StatusPins(_tableTag: Tag) extends profile.api.Table[StatusPinsRow](_tableTag, "status_pins") {
    def * = (id, accountId, statusId, createdAt, updatedAt) <> (StatusPinsRow.tupled, StatusPinsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(accountId), Rep.Some(statusId), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> StatusPinsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column status_id SqlType(int8) */
    val statusId: Rep[Long] = column[Long]("status_id")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Accounts (database name fk_rails_c3470a9de3) */
    lazy val accountsFk = foreignKey("fk_rails_c3470a9de3", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Statuses (database name fk_rails_65c05552f1) */
    lazy val statusesFk = foreignKey("fk_rails_65c05552f1", statusId, Statuses)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,statusId) (database name index_status_pins_on_account_id_and_status_id) */
    val index1 = index("index_status_pins_on_account_id_and_status_id", (accountId, statusId), unique=true)
  }
  /** Collection-like TableQuery object for table StatusPins */
  lazy val StatusPins = new TableQuery(tag => new StatusPins(tag))

  /** Entity class storing rows of table StreamEntries
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param accountId Database column account_id SqlType(int8), Default(None)
   *  @param activityId Database column activity_id SqlType(int8), Default(None)
   *  @param activityType Database column activity_type SqlType(varchar), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param hidden Database column hidden SqlType(bool), Default(false) */
  case class StreamEntriesRow(id: Long, accountId: Option[Long] = None, activityId: Option[Long] = None, activityType: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, hidden: Boolean = false)
  /** GetResult implicit for fetching StreamEntriesRow objects using plain SQL queries */
  implicit def GetResultStreamEntriesRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Boolean]): GR[StreamEntriesRow] = GR{
    prs => import prs._
    StreamEntriesRow.tupled((<<[Long], <<?[Long], <<?[Long], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table stream_entries. Objects of this class serve as prototypes for rows in queries. */
  class StreamEntries(_tableTag: Tag) extends profile.api.Table[StreamEntriesRow](_tableTag, "stream_entries") {
    def * = (id, accountId, activityId, activityType, createdAt, updatedAt, hidden) <> (StreamEntriesRow.tupled, StreamEntriesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), accountId, activityId, activityType, Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(hidden)).shaped.<>({r=>import r._; _1.map(_=> StreamEntriesRow.tupled((_1.get, _2, _3, _4, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column account_id SqlType(int8), Default(None) */
    val accountId: Rep[Option[Long]] = column[Option[Long]]("account_id", O.Default(None))
    /** Database column activity_id SqlType(int8), Default(None) */
    val activityId: Rep[Option[Long]] = column[Option[Long]]("activity_id", O.Default(None))
    /** Database column activity_type SqlType(varchar), Default(None) */
    val activityType: Rep[Option[String]] = column[Option[String]]("activity_type", O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column hidden SqlType(bool), Default(false) */
    val hidden: Rep[Boolean] = column[Boolean]("hidden", O.Default(false))

    /** Foreign key referencing Accounts (database name fk_rails_9f7d3aa7ef) */
    lazy val accountsFk = foreignKey("fk_rails_9f7d3aa7ef", accountId, Accounts)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Index over (activityId,activityType) (database name index_stream_entries_on_activity_id_and_activity_type) */
    val index1 = index("index_stream_entries_on_activity_id_and_activity_type", (activityId, activityType))
  }
  /** Collection-like TableQuery object for table StreamEntries */
  lazy val StreamEntries = new TableQuery(tag => new StreamEntries(tag))

  /** Entity class storing rows of table Subscriptions
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param callbackUrl Database column callback_url SqlType(varchar), Default()
   *  @param secret Database column secret SqlType(varchar), Default(None)
   *  @param expiresAt Database column expires_at SqlType(timestamp), Default(None)
   *  @param confirmed Database column confirmed SqlType(bool), Default(false)
   *  @param accountId Database column account_id SqlType(int8)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param lastSuccessfulDeliveryAt Database column last_successful_delivery_at SqlType(timestamp), Default(None)
   *  @param domain Database column domain SqlType(varchar), Default(None) */
  case class SubscriptionsRow(id: Long, callbackUrl: String = "", secret: Option[String] = None, expiresAt: Option[java.sql.Timestamp] = None, confirmed: Boolean = false, accountId: Long, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, lastSuccessfulDeliveryAt: Option[java.sql.Timestamp] = None, domain: Option[String] = None)
  /** GetResult implicit for fetching SubscriptionsRow objects using plain SQL queries */
  implicit def GetResultSubscriptionsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[java.sql.Timestamp]], e4: GR[Boolean], e5: GR[java.sql.Timestamp]): GR[SubscriptionsRow] = GR{
    prs => import prs._
    SubscriptionsRow.tupled((<<[Long], <<[String], <<?[String], <<?[java.sql.Timestamp], <<[Boolean], <<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table subscriptions. Objects of this class serve as prototypes for rows in queries. */
  class Subscriptions(_tableTag: Tag) extends profile.api.Table[SubscriptionsRow](_tableTag, "subscriptions") {
    def * = (id, callbackUrl, secret, expiresAt, confirmed, accountId, createdAt, updatedAt, lastSuccessfulDeliveryAt, domain) <> (SubscriptionsRow.tupled, SubscriptionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(callbackUrl), secret, expiresAt, Rep.Some(confirmed), Rep.Some(accountId), Rep.Some(createdAt), Rep.Some(updatedAt), lastSuccessfulDeliveryAt, domain).shaped.<>({r=>import r._; _1.map(_=> SubscriptionsRow.tupled((_1.get, _2.get, _3, _4, _5.get, _6.get, _7.get, _8.get, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column callback_url SqlType(varchar), Default() */
    val callbackUrl: Rep[String] = column[String]("callback_url", O.Default(""))
    /** Database column secret SqlType(varchar), Default(None) */
    val secret: Rep[Option[String]] = column[Option[String]]("secret", O.Default(None))
    /** Database column expires_at SqlType(timestamp), Default(None) */
    val expiresAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("expires_at", O.Default(None))
    /** Database column confirmed SqlType(bool), Default(false) */
    val confirmed: Rep[Boolean] = column[Boolean]("confirmed", O.Default(false))
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column last_successful_delivery_at SqlType(timestamp), Default(None) */
    val lastSuccessfulDeliveryAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_successful_delivery_at", O.Default(None))
    /** Database column domain SqlType(varchar), Default(None) */
    val domain: Rep[Option[String]] = column[Option[String]]("domain", O.Default(None))

    /** Foreign key referencing Accounts (database name fk_rails_eb0e3ffd90) */
    lazy val accountsFk = foreignKey("fk_rails_eb0e3ffd90", accountId, Accounts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (accountId,callbackUrl) (database name index_subscriptions_on_account_id_and_callback_url) */
    val index1 = index("index_subscriptions_on_account_id_and_callback_url", (accountId, callbackUrl), unique=true)
  }
  /** Collection-like TableQuery object for table Subscriptions */
  lazy val Subscriptions = new TableQuery(tag => new Subscriptions(tag))

  /** Entity class storing rows of table Tags
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Default()
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class TagsRow(id: Long, name: String = "", createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching TagsRow objects using plain SQL queries */
  implicit def GetResultTagsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[TagsRow] = GR{
    prs => import prs._
    TagsRow.tupled((<<[Long], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table tags. Objects of this class serve as prototypes for rows in queries. */
  class Tags(_tableTag: Tag) extends profile.api.Table[TagsRow](_tableTag, "tags") {
    def * = (id, name, createdAt, updatedAt) <> (TagsRow.tupled, TagsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> TagsRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Default() */
    val name: Rep[String] = column[String]("name", O.Default(""))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Uniqueness Index over (name) (database name index_tags_on_name) */
    val index1 = index("index_tags_on_name", name, unique=true)
  }
  /** Collection-like TableQuery object for table Tags */
  lazy val Tags = new TableQuery(tag => new Tags(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param email Database column email SqlType(varchar), Default()
   *  @param accountId Database column account_id SqlType(int8)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp)
   *  @param encryptedPassword Database column encrypted_password SqlType(varchar), Default()
   *  @param resetPasswordToken Database column reset_password_token SqlType(varchar), Default(None)
   *  @param resetPasswordSentAt Database column reset_password_sent_at SqlType(timestamp), Default(None)
   *  @param rememberCreatedAt Database column remember_created_at SqlType(timestamp), Default(None)
   *  @param signInCount Database column sign_in_count SqlType(int4), Default(0)
   *  @param currentSignInAt Database column current_sign_in_at SqlType(timestamp), Default(None)
   *  @param lastSignInAt Database column last_sign_in_at SqlType(timestamp), Default(None)
   *  @param currentSignInIp Database column current_sign_in_ip SqlType(inet), Length(2147483647,false), Default(None)
   *  @param lastSignInIp Database column last_sign_in_ip SqlType(inet), Length(2147483647,false), Default(None)
   *  @param admin Database column admin SqlType(bool), Default(false)
   *  @param confirmationToken Database column confirmation_token SqlType(varchar), Default(None)
   *  @param confirmedAt Database column confirmed_at SqlType(timestamp), Default(None)
   *  @param confirmationSentAt Database column confirmation_sent_at SqlType(timestamp), Default(None)
   *  @param unconfirmedEmail Database column unconfirmed_email SqlType(varchar), Default(None)
   *  @param locale Database column locale SqlType(varchar), Default(None)
   *  @param encryptedOtpSecret Database column encrypted_otp_secret SqlType(varchar), Default(None)
   *  @param encryptedOtpSecretIv Database column encrypted_otp_secret_iv SqlType(varchar), Default(None)
   *  @param encryptedOtpSecretSalt Database column encrypted_otp_secret_salt SqlType(varchar), Default(None)
   *  @param consumedTimestep Database column consumed_timestep SqlType(int4), Default(None)
   *  @param otpRequiredForLogin Database column otp_required_for_login SqlType(bool), Default(false)
   *  @param lastEmailedAt Database column last_emailed_at SqlType(timestamp), Default(None)
   *  @param otpBackupCodes Database column otp_backup_codes SqlType(_varchar), Length(2147483647,false), Default(None)
   *  @param filteredLanguages Database column filtered_languages SqlType(_varchar), Length(2147483647,false) */
  case class UsersRow(id: Long, email: String = "", accountId: Long, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, encryptedPassword: String = "", resetPasswordToken: Option[String] = None, resetPasswordSentAt: Option[java.sql.Timestamp] = None, rememberCreatedAt: Option[java.sql.Timestamp] = None, signInCount: Int = 0, currentSignInAt: Option[java.sql.Timestamp] = None, lastSignInAt: Option[java.sql.Timestamp] = None, currentSignInIp: Option[String] = None, lastSignInIp: Option[String] = None, admin: Boolean = false, confirmationToken: Option[String] = None, confirmedAt: Option[java.sql.Timestamp] = None, confirmationSentAt: Option[java.sql.Timestamp] = None, unconfirmedEmail: Option[String] = None, locale: Option[String] = None, encryptedOtpSecret: Option[String] = None, encryptedOtpSecretIv: Option[String] = None, encryptedOtpSecretSalt: Option[String] = None, consumedTimestep: Option[Int] = None, otpRequiredForLogin: Boolean = false, lastEmailedAt: Option[java.sql.Timestamp] = None, otpBackupCodes: Option[String] = None, filteredLanguages: String)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]], e4: GR[Option[java.sql.Timestamp]], e5: GR[Int], e6: GR[Boolean], e7: GR[Option[Int]]): GR[UsersRow] = GR{
    prs => import prs._
    Generic[UsersRow].from(<<[Long] :: <<[String] :: <<[Long] :: <<[java.sql.Timestamp] :: <<[java.sql.Timestamp] :: <<[String] :: <<?[String] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<[Int] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[String] :: <<?[String] :: <<[Boolean] :: <<?[String] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[Int] :: <<[Boolean] :: <<?[java.sql.Timestamp] :: <<?[String] :: <<[String] :: HNil)
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * = (id :: email :: accountId :: createdAt :: updatedAt :: encryptedPassword :: resetPasswordToken :: resetPasswordSentAt :: rememberCreatedAt :: signInCount :: currentSignInAt :: lastSignInAt :: currentSignInIp :: lastSignInIp :: admin :: confirmationToken :: confirmedAt :: confirmationSentAt :: unconfirmedEmail :: locale :: encryptedOtpSecret :: encryptedOtpSecretIv :: encryptedOtpSecretSalt :: consumedTimestep :: otpRequiredForLogin :: lastEmailedAt :: otpBackupCodes :: filteredLanguages :: HNil).mappedWith(Generic[UsersRow])

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column email SqlType(varchar), Default() */
    val email: Rep[String] = column[String]("email", O.Default(""))
    /** Database column account_id SqlType(int8) */
    val accountId: Rep[Long] = column[Long]("account_id")
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column encrypted_password SqlType(varchar), Default() */
    val encryptedPassword: Rep[String] = column[String]("encrypted_password", O.Default(""))
    /** Database column reset_password_token SqlType(varchar), Default(None) */
    val resetPasswordToken: Rep[Option[String]] = column[Option[String]]("reset_password_token", O.Default(None))
    /** Database column reset_password_sent_at SqlType(timestamp), Default(None) */
    val resetPasswordSentAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("reset_password_sent_at", O.Default(None))
    /** Database column remember_created_at SqlType(timestamp), Default(None) */
    val rememberCreatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("remember_created_at", O.Default(None))
    /** Database column sign_in_count SqlType(int4), Default(0) */
    val signInCount: Rep[Int] = column[Int]("sign_in_count", O.Default(0))
    /** Database column current_sign_in_at SqlType(timestamp), Default(None) */
    val currentSignInAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("current_sign_in_at", O.Default(None))
    /** Database column last_sign_in_at SqlType(timestamp), Default(None) */
    val lastSignInAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_sign_in_at", O.Default(None))
    /** Database column current_sign_in_ip SqlType(inet), Length(2147483647,false), Default(None) */
    val currentSignInIp: Rep[Option[String]] = column[Option[String]]("current_sign_in_ip", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column last_sign_in_ip SqlType(inet), Length(2147483647,false), Default(None) */
    val lastSignInIp: Rep[Option[String]] = column[Option[String]]("last_sign_in_ip", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column admin SqlType(bool), Default(false) */
    val admin: Rep[Boolean] = column[Boolean]("admin", O.Default(false))
    /** Database column confirmation_token SqlType(varchar), Default(None) */
    val confirmationToken: Rep[Option[String]] = column[Option[String]]("confirmation_token", O.Default(None))
    /** Database column confirmed_at SqlType(timestamp), Default(None) */
    val confirmedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("confirmed_at", O.Default(None))
    /** Database column confirmation_sent_at SqlType(timestamp), Default(None) */
    val confirmationSentAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("confirmation_sent_at", O.Default(None))
    /** Database column unconfirmed_email SqlType(varchar), Default(None) */
    val unconfirmedEmail: Rep[Option[String]] = column[Option[String]]("unconfirmed_email", O.Default(None))
    /** Database column locale SqlType(varchar), Default(None) */
    val locale: Rep[Option[String]] = column[Option[String]]("locale", O.Default(None))
    /** Database column encrypted_otp_secret SqlType(varchar), Default(None) */
    val encryptedOtpSecret: Rep[Option[String]] = column[Option[String]]("encrypted_otp_secret", O.Default(None))
    /** Database column encrypted_otp_secret_iv SqlType(varchar), Default(None) */
    val encryptedOtpSecretIv: Rep[Option[String]] = column[Option[String]]("encrypted_otp_secret_iv", O.Default(None))
    /** Database column encrypted_otp_secret_salt SqlType(varchar), Default(None) */
    val encryptedOtpSecretSalt: Rep[Option[String]] = column[Option[String]]("encrypted_otp_secret_salt", O.Default(None))
    /** Database column consumed_timestep SqlType(int4), Default(None) */
    val consumedTimestep: Rep[Option[Int]] = column[Option[Int]]("consumed_timestep", O.Default(None))
    /** Database column otp_required_for_login SqlType(bool), Default(false) */
    val otpRequiredForLogin: Rep[Boolean] = column[Boolean]("otp_required_for_login", O.Default(false))
    /** Database column last_emailed_at SqlType(timestamp), Default(None) */
    val lastEmailedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_emailed_at", O.Default(None))
    /** Database column otp_backup_codes SqlType(_varchar), Length(2147483647,false), Default(None) */
    val otpBackupCodes: Rep[Option[String]] = column[Option[String]]("otp_backup_codes", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column filtered_languages SqlType(_varchar), Length(2147483647,false) */
    val filteredLanguages: Rep[String] = column[String]("filtered_languages", O.Length(2147483647,varying=false))

    /** Foreign key referencing Accounts (database name fk_rails_61ac11da2b) */
    lazy val accountsFk = foreignKey("fk_rails_61ac11da2b", accountId :: HNil, Accounts)(r => r.id :: HNil, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (confirmationToken) (database name index_users_on_confirmation_token) */
    val index1 = index("index_users_on_confirmation_token", confirmationToken :: HNil, unique=true)
    /** Uniqueness Index over (email) (database name index_users_on_email) */
    val index2 = index("index_users_on_email", email :: HNil, unique=true)
    /** Index over (filteredLanguages) (database name index_users_on_filtered_languages) */
    val index3 = index("index_users_on_filtered_languages", filteredLanguages :: HNil)
    /** Uniqueness Index over (resetPasswordToken) (database name index_users_on_reset_password_token) */
    val index4 = index("index_users_on_reset_password_token", resetPasswordToken :: HNil, unique=true)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))

  /** Entity class storing rows of table WebPushSubscriptions
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param endpoint Database column endpoint SqlType(varchar)
   *  @param keyP256dh Database column key_p256dh SqlType(varchar)
   *  @param keyAuth Database column key_auth SqlType(varchar)
   *  @param data Database column data SqlType(json), Length(2147483647,false), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class WebPushSubscriptionsRow(id: Long, endpoint: String, keyP256dh: String, keyAuth: String, data: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching WebPushSubscriptionsRow objects using plain SQL queries */
  implicit def GetResultWebPushSubscriptionsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[WebPushSubscriptionsRow] = GR{
    prs => import prs._
    WebPushSubscriptionsRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table web_push_subscriptions. Objects of this class serve as prototypes for rows in queries. */
  class WebPushSubscriptions(_tableTag: Tag) extends profile.api.Table[WebPushSubscriptionsRow](_tableTag, "web_push_subscriptions") {
    def * = (id, endpoint, keyP256dh, keyAuth, data, createdAt, updatedAt) <> (WebPushSubscriptionsRow.tupled, WebPushSubscriptionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(endpoint), Rep.Some(keyP256dh), Rep.Some(keyAuth), data, Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> WebPushSubscriptionsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column endpoint SqlType(varchar) */
    val endpoint: Rep[String] = column[String]("endpoint")
    /** Database column key_p256dh SqlType(varchar) */
    val keyP256dh: Rep[String] = column[String]("key_p256dh")
    /** Database column key_auth SqlType(varchar) */
    val keyAuth: Rep[String] = column[String]("key_auth")
    /** Database column data SqlType(json), Length(2147483647,false), Default(None) */
    val data: Rep[Option[String]] = column[Option[String]]("data", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table WebPushSubscriptions */
  lazy val WebPushSubscriptions = new TableQuery(tag => new WebPushSubscriptions(tag))

  /** Entity class storing rows of table WebSettings
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(int8), Default(None)
   *  @param data Database column data SqlType(json), Length(2147483647,false), Default(None)
   *  @param createdAt Database column created_at SqlType(timestamp)
   *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class WebSettingsRow(id: Long, userId: Option[Long] = None, data: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching WebSettingsRow objects using plain SQL queries */
  implicit def GetResultWebSettingsRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[WebSettingsRow] = GR{
    prs => import prs._
    WebSettingsRow.tupled((<<[Long], <<?[Long], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table web_settings. Objects of this class serve as prototypes for rows in queries. */
  class WebSettings(_tableTag: Tag) extends profile.api.Table[WebSettingsRow](_tableTag, "web_settings") {
    def * = (id, userId, data, createdAt, updatedAt) <> (WebSettingsRow.tupled, WebSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), userId, data, Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> WebSettingsRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(int8), Default(None) */
    val userId: Rep[Option[Long]] = column[Option[Long]]("user_id", O.Default(None))
    /** Database column data SqlType(json), Length(2147483647,false), Default(None) */
    val data: Rep[Option[String]] = column[Option[String]]("data", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Users (database name fk_rails_550a4959e4) */
    lazy val usersFk = foreignKey("fk_rails_550a4959e4", userId, Users)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (userId) (database name index_web_settings_on_user_id) */
    val index1 = index("index_web_settings_on_user_id", userId, unique=true)
  }
  /** Collection-like TableQuery object for table WebSettings */
  lazy val WebSettings = new TableQuery(tag => new WebSettings(tag))
}
