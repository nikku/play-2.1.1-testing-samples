package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action { implicit request =>
    
    val message = request.queryString.get("message")
    
    message match {
      case None => Ok(views.html.index("Your new application is ready."))
      case _ => Ok(views.html.index(message.get.head))
    }
  }
}