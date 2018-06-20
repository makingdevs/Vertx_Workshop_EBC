// Muestra ejecución de código asíncrono, por que
// estioy enviando un mensaje y no estoy esperando una respuesta
vertx.eventBus().consumer("mx.edu.ebc.ping") { message ->
  println message.body()
}

vertx.setPeriodic 5000, {
  msg = "Hello at ${new Date()}"
  vertx.eventBus().send("mx.edu.ebc.ping", msg)
}

// Muestra la ejecución de código sincrono por que
// estoy esperando una respuesta de quién me llama
vertx.eventBus().consumer("mx.edu.ebc.pong") { message ->
  println message.body()
  message.reply("Pang back!")
}

vertx.setPeriodic 2000, {
  vertx.eventBus().send("mx.edu.ebc.pong", "Adios mundo!") {reply ->
    if(reply.succeeded())
      println reply.result().body()
    else
      println "No reply"
  }
}

