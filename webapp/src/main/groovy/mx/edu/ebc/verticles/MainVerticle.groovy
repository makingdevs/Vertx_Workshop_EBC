package mx.edu.ebc.verticles

import io.vertx.core.AbstractVerticle

class MainVerticle extends AbstractVerticle {

  void start(){
    String path = "src/main/groovy/mx/edu/ebc/verticles"
    vertx.deployVerticle("${path}/webserver.groovy")
    vertx.deployVerticle("${path}/data_access_verticle.groovy")
    vertx.deployVerticle("${path}/clock_verticle.groovy")
    //vertx.deployVerticle("${path}/data_access_pg_verticle.groovy")
    vertx.deployVerticle("mx.edu.ebc.verticles.ShellVerticle")
    println "*** Deploy done 😎 ***"
  }
}
