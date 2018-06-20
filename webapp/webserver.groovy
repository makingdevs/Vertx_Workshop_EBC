import io.vertx.ext.web.Router

httpServer = vertx.createHttpServer()

Router router = Router.router(vertx)

def route1 = router.route("/foo").handler { routingContext ->
  def response = routingContext.response()
  response.putHeader("content-type", "text/plain")
  response.end("Hola de nuevo FOO")
}

def route2 = router.route("/bar").handler { routingContext ->
  def response = routingContext.response()
  response.putHeader("content-type", "text/plain")
  response.end("Hola de nuevo BAR")
}

httpServer.requestHandler(router.&accept).listen(1234)
