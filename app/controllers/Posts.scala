package controllers

import play.api._
import play.api.mvc._

import anorm._
import models.Post
import play.api.data._
import play.api.data.Forms._

object Posts extends Controller with Secured {
  
  val postForm = Form(
    mapping(
      "title" -> nonEmptyText,
      "content" -> nonEmptyText
    )(Post.apply(0L, _, _, null))((p: Post) => Some(p.title, p.content))
  )

  def index = Action {implicit request =>
    Ok(views.html.Posts.index(Post.all()))
  }

  def newPost = IsAuthenticated {_ => implicit request =>
    Ok(views.html.Posts.postForm(postForm))
  }

  def create = IsAuthenticated {_ => implicit request =>
    postForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.Posts.postForm(formWithErrors)),
      post => {
        post.save()
        Redirect(routes.Posts.index)
      }
    )

  }
  
}