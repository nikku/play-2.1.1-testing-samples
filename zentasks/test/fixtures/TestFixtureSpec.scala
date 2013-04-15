package fixtures

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import models.User
import test.FixtureSpecification

class TestFixtureSpec extends FixtureSpecification {
  
  "Application with test fixtures" should {
    
    "have 4 users loaded" in {
      runningWithTestData {
        
        val users = User.findAll
        users must have size(4)
      }
    }
  }
  
  "Application without test fixtures" should {
    
    "have no users loaded" in {
      runningWithoutTestData {
        
        val users = User.findAll
        users must be empty
      }
    }
  }
  
  "Application with new style test fixtures" should {
    
    "have 4 users loaded" in new WithDbData {
      
      val users = User.findAll
      users must have size(4)
    }
  }
  
  
  "Application new style without test fixtures" should {
    
    "have no users loaded" in new WithApplication {
      
      val users = User.findAll
      users must be empty
    }
  }
}