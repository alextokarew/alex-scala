import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "alex"
  val appVersion      = "1.0-SNAPSHOT"

  //Textile processor
  val markwrap = "org.clapper" %% "markwrap" % "1.0.1"

  //Postgres
  val postgres = "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    markwrap,
    postgres
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
