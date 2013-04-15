package test

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {
  
  "Application controller" should {
  
    "send 0k on index access" in new WithApplication {
      
      val result = route(FakeRequest(GET, "/")).get
      
      status(result) must equalTo(OK)
      
    }
  }
  
  "Application" should {
    
    "send 404 on a bad request" in {
      running(FakeApplication()) {
        route(FakeRequest(GET, "/boum")) must beNone        
      }
    }
    
    "render the index page" in new WithApplication {
      val home = route(FakeRequest(GET, "/")).get
      
      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Your new application is ready.")
    }
    
    "render with test data" in new WithApplication {
      val home = route(FakeRequest(GET, "/?message=Hello%20World!")).get
      
      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Hello World!")
    }
  }
}