import io.vertx.ext.asyncsql.MySQLClient

def mySQLClientConfig = [
  host: "localhost",
  username: "makingdevs",
  password: "makingdevs",
  database: "scrumboard"
]

def mySQLClient = MySQLClient.createShared(vertx, mySQLClientConfig)

println mySQLClient
