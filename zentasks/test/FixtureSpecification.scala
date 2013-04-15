package test

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import fixtures.TestFixtures
import play.api.mvc.Result
import org.specs2.execute.AsResult

class FixtureSpecification extends Specification {

  abstract class WithDbData extends WithApplication {
    override def around[T](t: => T)(implicit evidence: AsResult[T]) = super.around {
      TestFixtures.insert
      t
    }
  }
  
  def runningWithTestData[T](block: => T) {
    val fakeApp = FakeApplication(additionalConfiguration = inMemoryDatabase())
  
    running(fakeApp) {
      TestFixtures.insert()
      block
    }
  }
  
  def runningWithoutTestData[T](block: => T) {
    val fakeApp = FakeApplication(additionalConfiguration = inMemoryDatabase())
  
    running(fakeApp) {
      block
    }
  }
}