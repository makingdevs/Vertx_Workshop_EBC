// Muestra ejecución de código asíncrono, por que
// estioy enviando un mensaje y no estoy esperando una respuesta
vertx.eventBus().consumer("mx.edu.ebc.ping") { message ->
  println message.body()
}

// Muestra la ejecución de código sincrono por que
// estoy esperando una respuesta de quién me llama
vertx.eventBus().consumer("mx.edu.ebc.pong") { message ->
  println message.body()
  message.reply("Pang neodevelop!")
}

