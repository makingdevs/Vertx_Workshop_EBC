import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.ERROR
import static ch.qos.logback.classic.Level.WARN
import static ch.qos.logback.classic.Level.FINEST
import static ch.qos.logback.classic.Level.SEVERE

import ch.qos.logback.classic.encoder.PatternLayoutEncoder

String env = System.getenv("ENVIRONMENT") ?: "development"
String container = System.getenv("APP_SERVER_HOME") ?: "."

levelsByEnvironment = [
  "development" : INFO,
  "test" : WARN,
  "production" : ERROR,
]

appender("FILE", FileAppender) {
  file = "${container}/vertx.log"
  append = true
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %highlight(%-5level) %cyan(%logger{15}) - %msg %n"
  }
}

root(levelsByEnvironment[env], ["FILE"])

logger("mx.edu.ebc", INFO)

