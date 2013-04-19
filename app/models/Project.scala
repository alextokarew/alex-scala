package models

import play.api.db.DB
import anorm._
import anorm.SqlParser._
import play.api.Play.current

case class Project(id: Long, name: String, summary: String, description : String, history: String)

object Project {
  val project = {
    get[Long]("id") ~ get[String]("name") ~ get[String]("summary") map {
      case id~name~summary => Project(id, name, summary, null, null);
    }
  }

  def all() : List[Project] = DB.withConnection {implicit c =>
    SQL("select * from projects").as(project *)
  }
}
