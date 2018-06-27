var eb = new EventBus("http://localhost:1234/eventbus")
eb.enableReconnect(true);
eb.onopen = function(){
  console.log("Connecting to Eventbus...");
  eb.registerHandler("mx.edu.ebc.clock", function(error, message){
    console.log(message);
  });
  console.log("Ready 😎 ...");
};
