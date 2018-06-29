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
    var source = $("#task-list-template").html();
    var template = Handlebars.compile(source);
    var html = template({tasks: msg.body});
    $("#task_list").html(html);
  });
  console.log("Ready 😎 ...");
};

$("#new_task").on("submit", function(event){
  var description = $("input[name=description]").val()
  var task = { 'description': description, 'status': 'TODO' }
  eb.send("mx.edu.ebc.task.save", task, function(error, msg){
    console.log("Saved ....");
  })
  event.preventDefault();
});
