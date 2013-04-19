package controllers

import play.api._
import play.api.mvc._
import models.Project

object Projects extends Controller {
  
  def index = Action {
    Ok(views.html.Projects.index(Project.all()))
  }
  
}