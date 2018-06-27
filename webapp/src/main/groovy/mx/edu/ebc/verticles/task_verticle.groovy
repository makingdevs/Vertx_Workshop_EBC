package mx.edu.ebc.verticles

vertx.eventBus().consumer("mx.edu.ebc.task.all"){ msg ->
  println "*"*100
  println msg
  println "*"*100
  msg.reply("Hellpo back")
}

