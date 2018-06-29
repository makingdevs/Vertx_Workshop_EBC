package mx.edu.ebc.verticles

import mx.edu.ebc.model.Task
import io.vertx.core.json.JsonObject

vertx.eventBus().consumer("mx.edu.ebc.task.all"){ msg ->
  println "*"*100
  println msg
  println "*"*100
  def response = [new Task(description: "Task 1", status: "TODO"), new Task(description:"Task 2", status: "WIP")].collect { t -> JsonObject.mapFrom(t) }
  msg.reply(response)
}

