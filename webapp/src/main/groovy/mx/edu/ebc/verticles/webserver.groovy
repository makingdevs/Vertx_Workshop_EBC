package mx.edu.ebc.verticles

import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.ext.web.handler.sockjs.SockJSHandler

httpServer = vertx.createHttpServer()

Router router = Router.router(vertx)

def route1 = router.get("/foo").handler { routingContext ->
  def response = routingContext.response()
  response.putHeader("content-type", "text/plain")
  response.end("Hola de nuevo FOO GET")
}

def route1_1 = router.post("/foo").handler { routingContext ->
  def response = routingContext.response()
  response.putHeader("content-type", "text/plain")
  response.end("Hola de nuevo FOO POST")
}

def route2 = router.route("/bar").handler { routingContext ->
  def response = routingContext.response()
  response.putHeader("content-type", "text/plain")
  response.end("Hola de nuevo BAR")
}

def content = router.route("/public/*").handler(StaticHandler.create().setCachingEnabled(false))

def options = [
  inboundPermitteds:[
    [address: "mx.edu.ebc.request_info"],
    [addressRegex: "mx.edu.ebc.task\\..+"]
  ],
  outboundPermitteds:[
    [address: "mx.edu.ebc.clock"],
    [addressRegex: "mx.edu.ebc.task\\..+"]
  ]
]
def sockJsHandler = SockJSHandler.create(vertx).bridge(options)
router.route("/eventbus/*").handler(sockJsHandler)

httpServer.requestHandler(router.&accept).listen(1234)
