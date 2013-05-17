package controllers

import play.api._
import play.api.mvc._
import models.Project
import play.api.data.Form
import play.api.data.Forms._
import scala.Some

object Projects extends Controller with Secured {

  val projectForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "title" -> nonEmptyText,
      "summary" -> nonEmptyText,
      "description" -> nonEmptyText,
      "history" -> nonEmptyText
    )(Project.apply(0L, _, _, _, _, _))((p: Project) => Some(p.name, p.title, p.summary, p.description, p.history))
  )

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

  def newProject = IsAuthenticated {_ => implicit request =>
    Ok(views.html.Projects.projectForm(projectForm))
  }

  def create = IsAuthenticated {_ => implicit request =>
    projectForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.Projects.projectForm(formWithErrors)),
      project => {
        project.save()
        Redirect(routes.Projects.index)
      }
    )
  }
  
}