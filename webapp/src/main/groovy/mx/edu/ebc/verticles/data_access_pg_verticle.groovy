package mx.edu.ebc.verticles

import io.vertx.ext.asyncsql.PostgreSQLClient

def postgreSQLClientConfig = [
  host: "localhost",
  username: "makingdevs",
  password: "makingdevs",
  database: "scrumboard"
]
def postgreSQLClient = PostgreSQLClient.createShared(vertx, postgreSQLClientConfig)



postgreSQLClient.getConnection { result ->
  println result.properties
}
