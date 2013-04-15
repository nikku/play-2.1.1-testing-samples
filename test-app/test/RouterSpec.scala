
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._

class RouterSpec extends Specification {

  "Router" should {
    
    "route / to Application#index" in new WithApplication {
      
      val Some(result) = route(FakeRequest(GET, "/"))
      
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("Your new application is ready.")
    }
  }
}