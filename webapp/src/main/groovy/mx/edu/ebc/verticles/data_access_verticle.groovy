package mx.edu.ebc.verticles

import io.vertx.ext.asyncsql.MySQLClient

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
