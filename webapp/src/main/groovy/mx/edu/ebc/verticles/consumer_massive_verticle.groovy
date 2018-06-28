package mx.edu.ebc.verticles

println "*** Ready for listen ${this.class.name} 😎 ***"
vertx.eventBus().consumer("mx.edu.ebc.do_operation"){ msg ->
  Thread.sleep(100)
  println msg.body().toInteger()
}

