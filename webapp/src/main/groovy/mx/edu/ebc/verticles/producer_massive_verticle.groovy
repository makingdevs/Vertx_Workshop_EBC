package mx.edu.ebc.verticles

vertx.eventBus().consumer("mx.edu.ebc.massive_processing"){ msg ->
  println "#"*100
  (1..1000).each { n ->
    vertx.eventBus().send("mx.edu.ebc.do_operation", n)
  }
}
