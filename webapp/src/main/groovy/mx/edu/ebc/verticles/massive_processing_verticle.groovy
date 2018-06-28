package mx.edu.ebc.verticles

println "*** Deploying ${this.class.name} 🔥 ***"
String path = "src/main/groovy/mx/edu/ebc/verticles"
vertx.deployVerticle("${path}/producer_massive_verticle.groovy")
vertx.deployVerticle("${path}/consumer_massive_verticle.groovy")

