package jp.cormo.apagle

import slick.codegen.{AbstractSourceCodeGenerator, OutputHelpers, SourceCodeGenerator}
import slick.jdbc.PostgresProfile

import scala.concurrent.{Await, duration}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object DbCodeGen extends App {
  val slickDriver = "slick.jdbc.PostgresProfile"
  val jdbcDriver  = "org.postgresql.Driver"
  val outDir = "src/main/scala"
  val pkg = "jp.cormo.apagle.model.db"
  val future = Db().run(PostgresProfile.createModel(Some(PostgresProfile.defaultTables))).map(m => {
    val codegen = new AbstractSourceCodeGenerator(m) with OutputHelpers {
      type Table = TableDef
      def Table = new TableDef(_)
      class TableDef(model: slick.model.Table) extends super.TableDef(model){
        // Using defs instead of (caching) lazy vals here to provide consitent interface to the user.
        // Performance should really not be critical in the code generator. Models shouldn't be huge.
        // Also lazy vals don't inherit docs from defs
        override def mappingEnabled: Boolean = true
        type EntityType     =     EntityTypeDef
        def  EntityType     = new EntityType{}
        type PlainSqlMapper =     PlainSqlMapperDef
        def  PlainSqlMapper = new PlainSqlMapper{}
        type TableClass     =     TableClassDef
        def  TableClass     = new TableClass{
          override def star: String = {
            val struct = compoundValue(columns.map(c=>if(c.asOption)s"Rep.Some(${c.name})" else s"${c.name}"))
            val rhs1 = s"($struct).mappedWith(Generic[${TableClass.elementType}])"
            val rhs2 = s"$struct <> (${TableClass.elementType}.tupled, ${TableClass.elementType}.unapply)"
            val rhs = if (hlistEnabled) rhs1 else rhs2
            s"def * = ${rhs}"
          }
        }
        type TableValue     =     TableValueDef
        def  TableValue     = new TableValue{}
        type Column         =     ColumnDef
        def  Column         = new Column(_)
        type PrimaryKey     =     PrimaryKeyDef
        def  PrimaryKey     = new PrimaryKey(_)
        type ForeignKey     =     ForeignKeyDef
        def  ForeignKey     = new ForeignKey(_)
        type Index          =     IndexDef
        def  Index          = new Index(_)
      }
    }
    println("test")
    codegen.writeToFile(slickDriver, outDir, pkg, "Tables", "Tables.scala")
    println("complete")
  })

  Await.ready(future, 1.days)
  println(future)
}
