package controllers

import play.api._
import play.api.mvc._
import models.User

object Application extends Controller {
  def contacts = TODO

  def login = Action {implicit request =>

    (request.queryString.get("name"),request.queryString.get("password")) match {
      case (Some(Seq(name)),Some(Seq(password))) if (User.authenticate(name, password)) =>
          Redirect(routes.Posts.index()).withSession ("user" -> name)
      case _ => Redirect(routes.Posts.index()).withNewSession
    }
  }
}