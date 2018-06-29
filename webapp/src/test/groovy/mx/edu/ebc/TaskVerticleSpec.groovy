package mx.edu.ebc

import spock.lang.*
import spock.util.concurrent.PollingConditions
import io.vertx.core.Vertx

class TaskVerticleSpec extends Specification {

  @Shared Vertx vertx
  @Shared String verticleId
  PollingConditions conditions = new PollingConditions(timeout: 10)

  def setupSpec(){
    vertx = Vertx.vertx()
    vertx.deployVerticle("src/main/groovy/mx/edu/ebc/verticles/task_verticle.groovy"){ deployResponse ->
      assert deployResponse.succeeded()
      verticleId = deployResponse.result
    }
    Thread.sleep 1000
  }

  def cleanupSpec(){
    Thread.sleep 2000
    vertx.undeploy(verticleId) { undeployResponse ->
      assert undeployResponse.succeeded()
      vertx.close()
    }
  }

  def "should be double eventually"(){
    given:
      def value = 5
    when:
      Thread.start {
        sleep(3000)
        value *= 2
      }
    then:
      conditions.eventually {
        assert value == 10
      }
  }
}
