package controllers

import play.api._
import play.api.mvc._

import java.util.Date
import models.Post
import play.api.data._
import play.api.data.Forms._

object Posts extends Controller with Secured {
  
  val postForm = Form(
    mapping(
      "id" -> ignored(0L),
      "title" -> nonEmptyText,
      "content" -> nonEmptyText,
      "created_at" -> ignored[Date](null)
    )(Post.apply)(Post.unapply)
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

  def edit(id: String) = IsAuthenticated {_ => implicit request =>
    Post.find(id) match {
      case Some(post) => Ok(views.html.Posts.postForm(postForm.fill(post), post.id))
      case None => NotFound
    }
  }

  def update(id: String) = IsAuthenticated {_ => implicit request =>
    postForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.Posts.postForm(formWithErrors)),
      post => {
        post.save(id.toLong)
        Redirect(routes.Posts.index)
      }
    )
  }
}