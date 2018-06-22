package mx.edu.ebc.verticles

import io.vertx.core.AbstractVerticle

class MainVerticle extends AbstractVerticle {

  void start(){
    String path = "src/main/groovy/mx/edu/ebc/verticles"
    vertx.deployVerticle("${path}/webserver.groovy")
    vertx.deployVerticle("${path}/data_access_verticle.groovy")
    println "*** Deploy done 😎 ***"
  }
}
