package mx.edu.ebc.verticles

import io.vertx.core.AbstractVerticle
import io.vertx.ext.shell.ShellService

class ShellVerticle extends AbstractVerticle {

  void start(){
    def service = ShellService.create(vertx, [
    telnetOptions: [
        host: "localhost",
        port: 4000
      ]
    ])
    service.start()
  }

}

