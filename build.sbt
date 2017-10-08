name := "apagle"
version := "0.1.1"
scalaVersion := "2.12.3"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
libraryDependencies += "com.twitter" %% "finagle-http" % "7.1.0"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.1"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.1"

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.1"
libraryDependencies += "com.github.finagle" %% "finch-core" % "0.16.0-M2"
libraryDependencies += "com.github.finagle" %% "finch-jackson" % "0.16.0-M2"
