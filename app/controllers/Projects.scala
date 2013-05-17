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
      "id" -> ignored(0L),
      "name" -> nonEmptyText,
      "title" -> nonEmptyText,
      "summary" -> nonEmptyText,
      "description" -> nonEmptyText,
      "history" -> nonEmptyText
    )(Project.apply)(Project.unapply)
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

  def edit(name: String) = IsAuthenticated {_ => implicit request =>
    Project.find(name) match {
      case Some(project) => Ok(views.html.Projects.projectForm(projectForm.fill(project), project.id))
      case None => NotFound
    }
  }

  def update(id: String) = IsAuthenticated {_ => implicit request =>
    projectForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.Projects.projectForm(formWithErrors)),
      project => {
        project.save(id.toLong)
        Redirect(routes.Projects.index)
      }
    )
  }
}