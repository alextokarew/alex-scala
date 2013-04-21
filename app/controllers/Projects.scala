package controllers

import play.api._
import play.api.mvc._
import models.Project

object Projects extends Controller {
  
  def index = Action {implicit request =>
    Ok(views.html.Projects.index(Project.all()))
  }

  def show(name: String, kind : String = "description") = Action {implicit request =>
    Project.find(name) match {
      case Some(project) => Ok(views.html.Projects.show(project, kind))
      case None => NotFound
    }
  }

  def history(name: String) = show(name,"history")
  
}