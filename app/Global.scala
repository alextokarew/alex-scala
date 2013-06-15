import anorm.SQL
import play.api._
import play.api.db.DB
import play.api.Play.current

object Global extends GlobalSettings {
  val initialData = List(
    "delete from Posts",
    "delete from Users",
    "insert into Posts values (1,'First post','*First* post content','2013-03-20 14:15:16')",
    "insert into Posts values (2,'Second post','Second _post_ content','2013-03-22 17:18:19')",
    "insert into Users values ('sanchous-i','5d23a82bed6c309bece1a9ae9a063879');"
  )

  override def onStart(app: Application) {
    if (app.mode == Mode.Dev) {
      Logger.info("Application has been started in development mode. Loading sample data")
      initialData.foreach(statement => DB.withConnection {implicit c => SQL(statement).execute()})
      Logger.info("Sample data has been loaded successfully")
    }
  }
}
