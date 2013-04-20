package controllers

import play.api._
import play.api.mvc._
import models.Project

object Projects extends Controller {
  
  def index = Action {
    Ok(views.html.Projects.index(Project.all()))
  }

  def show(name: String, kind : String = "description") = Action {request =>
    val currentUrl = request.uri
    Project.find(name) match {
      case Some(project) => Ok(views.html.Projects.show(project, kind, currentUrl))
      case None => NotFound
    }
  }

  def history(name: String) = show(name,"history")
  
}