package mx.edu.ebc.verticles

import io.vertx.core.json.JsonObject
import mx.edu.ebc.model.Trip

def sharedData = vertx.sharedData()
def trips = sharedData.getLocalMap("trips")

println "*** Ready for listen ${this.class.name} 😎 ***"
vertx.eventBus().consumer("mx.edu.ebc.do_operation"){ msg ->
  def tripString = msg.body()
  def tripSplit = tripString.split(',')
  def trip = JsonObject.mapFrom(new Trip(genre: tripSplit[0], age: tripSplit[1].toInteger(), bicycle: tripSplit[2]))
  def processed = trips.processed.add(trip)
  trips.put("processed", processed)
  vertx.eventBus().send("mx.edu.ebc.massive_processing_done", trip.hashCode())
}

