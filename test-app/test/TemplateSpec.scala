
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._

class TemplateSpec extends Specification {

  "View #index" should {
    
    "render correct HTML" in new WithApplication {
      
      val html = views.html.index("Hello World")
      
      contentType(html) must equalTo("text/html")
      contentAsString(html) must contain("Hello World")
    }
  } 
}