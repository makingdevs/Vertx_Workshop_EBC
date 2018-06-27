var eb = new EventBus("http://localhost:1234/eventbus")
eb.enableReconnect(true);
eb.onopen = function(){
  console.log("Connecting to Eventbus...");
  eb.registerHandler("mx.edu.ebc.clock", function(error, message){
    var source = $("#clock-template").html();
    var template = Handlebars.compile(source);
    var html = template(message);
    $("#clock").html(html);
  });
  eb.send("mx.edu.ebc.task.all", {}, function(error, msg){
    console.log(msg);
  });
  console.log("Ready 😎 ...");
};
