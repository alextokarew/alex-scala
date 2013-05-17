package models

import play.api.db.DB
import anorm._
import anorm.SqlParser._
import play.api.Play.current

case class Project(id: Long, name: String, title: String, summary: String, description : String, history: String) {
  def save(id: Long = 0) : Boolean = DB.withConnection {implicit c =>
    id match {
      case 0 => SQL("insert into projects (name, title, summary, description, history) values ({name}, {title}, {summary}, {description}, {history})")
        .on("name" -> name, "title" -> title, "summary" -> summary, "description" -> description, "history" -> history)
        .execute()
      case _ => SQL("update projects set name={name}, title={title}, summary={summary}, description={description}, history={history} where id = {id}")
        .on("id" -> id, "name" -> name, "title" -> title, "summary" -> summary, "description" -> description, "history" -> history)
        .execute()
    }
  }
}

object Project {
  val projectBrief = {
    get[Long]("id") ~ get[String]("name") ~ get[String]("title") ~ get[String]("summary") map {
      case id~name~title~summary => Project(id, name, title, summary, null, null)
    }
  }

  val project = {
    get[Long]("id") ~
      get[String]("name") ~
      get[String]("title") ~
      get[String]("summary") ~
      get[String]("description") ~
      get[String]("history") map {
      case id~name~title~summary~description~history => Project(id, name, title, summary, description, history)
    }
  }

  def all() : List[Project] = DB.withConnection {implicit c =>
    SQL("select id, name, title, summary from projects").as(projectBrief *)
  }

  def find(name: String) = DB.withConnection { implicit c =>
    SQL("select * from projects where name = {name}")
    .on("name" -> name)
    .as(project singleOpt)
  }
}
