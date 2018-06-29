package mx.edu.ebc.verticles

import mx.edu.ebc.model.Task
import io.vertx.core.json.JsonObject

vertx.eventBus().consumer("mx.edu.ebc.task.all"){ msg ->
  println "*"*100
  println msg
  println "*"*100
  vertx.eventBus().send("mx.edu.ebc.data.get_tasks", null){ reply ->
    println reply.result().body()
    def dbResults = reply.result().body()["results"]
    def response = dbResults.collect { r ->
      new Task(id: r[0],description: r[1], status: r[2])
    }.collect { o ->
      JsonObject.mapFrom(o)
    }
    msg.reply(response)
  }
}

