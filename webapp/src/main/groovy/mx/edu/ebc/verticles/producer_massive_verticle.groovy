package mx.edu.ebc.verticles

def sharedData = vertx.sharedData()
def trips = sharedData.getLocalMap("trips")

trips.put("processed", [])

vertx.eventBus().consumer("mx.edu.ebc.massive_processing"){ msg ->
  vertx.fileSystem().readFile("src/main/resources/2010-02.csv", { result ->
    if (result.succeeded()) {
      def lines = result.result().toString().split("\n")
      trips.size = lines.size()
      lines.each { l ->
        vertx.eventBus().send("mx.edu.ebc.do_operation", l)
      }
    } else {
      System.err.println("Oh oh ...${result.cause()}")
    }
  })
}

vertx.eventBus().consumer("mx.edu.ebc.massive_processing_done"){ msg ->
  println msg.body()
  println "${trips.size} == ${trips.processed.size()}"
  if(trips.size == trips.processed.size()){
    println "*** Done 👊 ***"
  }
}
