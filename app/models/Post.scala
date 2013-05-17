package models


import anorm._
import anorm.SqlParser._

import play.api.db._
import play.api.Play.current
import java.util.Date

case class Post(id: Long, title: String, content: String, created_at: Date) {
  def save(id: Long = 0) : Boolean = DB.withConnection {implicit c =>
    id match {
      case 0 => SQL("insert into posts (title, content, created_at) values ({title}, {content}, now())")
        .on("title" -> title, "content" -> content)
        .execute()
      case _ => SQL("update posts set title={title}, content={content} where id={id}")
        .on("title" -> title, "content" -> content, "id" -> id)
        .execute()
    }
  }
}

object Post {

  val post = {
    get[Long]("id") ~ get[String]("title") ~ get[String]("content") ~ get[Date]("created_at") map {
      case id~title~content~created_at => Post(id, title, content, created_at);
    }
  }

  def all() : List[Post] = DB.withConnection {implicit c =>
    SQL("select * from posts order by created_at desc").as(post *)
  }

  def find(id: String) = DB.withConnection { implicit c =>
    SQL("select * from posts where id = {id}")
      .on("id" -> id)
      .as(post singleOpt)
  }
}
