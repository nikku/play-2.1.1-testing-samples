package test

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends Specification {
  
  "Application" should {
    
    "go to login page without credentials" in new WithApplication {
      val result  = route( FakeRequest( GET, "/")).get
      
      // did a redirect happen?
      status(result) must equalTo(303)
      header("Location", result) must equalTo(Some("/login"))
    }
    
    "list the secured product page with credentials" in new WithApplication {
      val result  = route( FakeRequest( GET, "/").withSession("email"->"guillaume@sample.com")).get
      status(result) must equalTo(200)
    }.pendingUntilFixed
  }
}
