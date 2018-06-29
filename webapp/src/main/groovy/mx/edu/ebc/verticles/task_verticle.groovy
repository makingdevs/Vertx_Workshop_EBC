package mx.edu.ebc.verticles

import mx.edu.ebc.model.Task
import io.vertx.core.json.JsonObject

vertx.eventBus().consumer("mx.edu.ebc.task.all"){ msg ->
  vertx.eventBus().send("mx.edu.ebc.data.get_tasks", null){ reply ->
    def dbResults = reply.result().body()["results"]
    def response = dbResults.collect { r ->
      new Task(id: r[0],description: r[1], status: r[2])
    }.collect { o ->
      JsonObject.mapFrom(o)
    }
    // msg.reply(response)
    vertx.eventBus().publish("mx.edu.ebc.task.render_all", response)
  }
}

vertx.eventBus().consumer("mx.edu.ebc.task.save"){ msg ->
  vertx.eventBus().send("mx.edu.ebc.data.save_task", msg.body()) {res ->
    println res.result()?.body()
    msg.reply JsonObject.mapFrom(res.result()?.body())
  }
}
