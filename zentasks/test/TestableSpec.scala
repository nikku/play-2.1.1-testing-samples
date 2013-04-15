package test

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import play.api.mvc.Controller
import controllers.Testable
import play.api.mvc.Results

class TestableSpec extends Specification {

  class TestTestable extends Controller with Testable
  
  "Testable Controller #index should return HTTP Success" in new WithApplication {
      
      val controller = Testable
      val result = controller.index().apply(FakeRequest( GET, "/"))
      
      result must equalTo(Results.Ok)
  }
  
  "Testable Controller #forbidden should return HTTP Forbidden" in {
      
      val controller = new TestTestable()
      val result = controller.forbidden().apply(FakeRequest( GET, "/"))
      
      result must equalTo(Results.Forbidden)
  }
  
  "Testable Controller #forbiddenUnlessSecred should return HTTP Forbidden on no secret" in new WithApplication {
      
      val controller = new TestTestable()
      val result = controller.forbiddenUnlessSecred().apply(FakeRequest( GET, "/"))
      
      result must equalTo(Results.Forbidden)
  }
  
  "Testable Controller #forbiddenUnlessSecred should return HTTP Success on secret" in new WithApplication {
      
      val controller = new TestTestable()
      val result = controller.forbiddenUnlessSecred().apply(FakeRequest( GET, "/?secret=foo"))
      
      result must equalTo(Results.Ok)
  }
}