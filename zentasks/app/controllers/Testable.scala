package controllers

import play.api.mvc.Controller
import play.api.mvc.Results
import play.api.mvc.Action

trait Testable {
  
  this: Controller =>

  def index = Action { implicit request =>
    Results.Ok
  }
  
  def forbidden = Action { implicit request =>
    Results.Forbidden
  }
  
  def forbiddenUnlessSecred = Action { implicit request => 
    
    val secret = request.queryString.find ({
      case ("secret", _) => true
      case _ => false
    })
    
    secret match {
      case None => Results.Forbidden
      case _ => Results.Ok
    }
  }
}

object Testable extends Controller with Testable
