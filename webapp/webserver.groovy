httpServer = vertx.createHttpServer()

httpServer.requestHandler { request ->
  request
  .response()
  .putHeader("content-type", "text/plain")
  .end("Hello world")
}

httpServer.listen(1234)
