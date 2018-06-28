package mx.edu.ebc.verticles

import io.vertx.core.json.JsonObject

def sharedData = vertx.sharedData()
def trips = sharedData.getLocalMap("trips")

println "*** Ready for listen ${this.class.name} 😎 ***"
vertx.eventBus().consumer("mx.edu.ebc.do_operation"){ msg ->
  def tripString = msg.body()
  def trip = tripString.split(',')
  def processed = trips.processed.add(tripString)
  trips.put("processed", processed)
  vertx.eventBus().send("mx.edu.ebc.massive_processing_done", trip.hashCode())
}

