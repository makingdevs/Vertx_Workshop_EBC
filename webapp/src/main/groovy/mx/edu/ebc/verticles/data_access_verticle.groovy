package mx.edu.ebc.verticles

import mx.edu.ebc.model.Task
import io.vertx.ext.asyncsql.MySQLClient
import io.vertx.core.json.JsonObject

def mySQLClientConfig = [
  host: "localhost",
  username: "root",
  password: "makingdevs",
  database: "scrumboard"
]


def mySQLClient = MySQLClient.createShared(vertx, mySQLClientConfig)

vertx.eventBus().consumer("mx.edu.ebc.data.get_tasks"){ message ->
  mySQLClient.getConnection { result ->
    def connection = result.result()
    connection.query("SELECT * FROM task", { dbResult ->
      println dbResult.result()
    })
  }
}

vertx.eventBus().consumer("mx.edu.ebc.data.find_one_task"){ message ->
  mySQLClient.getConnection { result ->
    def connection = result.result()
    Integer n = message.body().toInteger()
    connection.query("SELECT * FROM task WHERE id = ${n}", { dbResult ->
      println dbResult.result()
    })
  }
}

vertx.eventBus().consumer("mx.edu.ebc.data.save_task"){ message ->
  mySQLClient.getConnection { result ->
    def connection = result.result()
    def json = new JsonObject(message.body())
    def params = [json.map.description, json.map.status]
    connection.updateWithParams("INSERT INTO task(description, status, date_created) values(?,?,now())", params, { dbResult ->
      println dbResult.result()
    })
  }
}


vertx.eventBus().consumer("mx.edu.ebc.data.send_to_save"){ message ->
  Task task = new Task(description: "${new Date()}", status: "TODO")
  vertx.eventBus().send("mx.edu.ebc.data.save_task", JsonObject.mapFrom(task))
}
