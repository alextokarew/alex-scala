package controllers

import play.api._
import play.api.mvc._

import anorm._
import models.Post

object Posts extends Controller {
  
  def index = Action {
    Ok(views.html.Posts.index(Post.all()))
  }
  
}