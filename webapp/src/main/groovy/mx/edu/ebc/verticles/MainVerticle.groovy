package mx.edu.ebc.verticles

import io.vertx.core.AbstractVerticle

class MainVerticle extends AbstractVerticle {

  void start(){
    println "Hello world in Vertx ${vertx}"
  }
}
