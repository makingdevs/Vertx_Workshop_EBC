package mx.edu.ebc.verticles

vertx.setPeriodic(1000, {
  def msg = new Date().format('dd/MM/yy hh:mm:ss')
  vertx.eventBus().send("mx.edu.ebc.clock", msg)
})
