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

trait Secured {
  /**
   * Retrieve the connected user email.
   */
  private def username(request: RequestHeader) = request.session.get("user")

  /**
   * Redirect to login if the user in not authorized.
   */
  private def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Posts.index)

  /**
   * Action for authenticated users.
   */
  def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(username, onUnauthorized) { user =>
    Action(request => f(user)(request))
  }
}