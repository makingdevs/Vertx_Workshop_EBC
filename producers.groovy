vertx.setPeriodic 5000, {
  msg = "Hello at ${new Date()}"
  vertx.eventBus().send("mx.edu.ebc.ping", msg)
}

vertx.setPeriodic 2000, {
  vertx.eventBus().send("mx.edu.ebc.pong", "Adios mundo!") {reply ->
    if(reply.succeeded())
      println reply.result().body()
    else
      println "No reply"
  }
}

