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
  eb.registerHandler("mx.edu.ebc.task.render_all", function(error, message){
    var source = $("#task-list-template").html();
    var template = Handlebars.compile(source);
    var html = template({tasks: message.body});
    $("#task_list").html(html);
  });
  eb.registerHandler("mx.edu.ebc.task.save_confirm", function(error, message){
    console.log("Task saved !!! " + message);
  });
  eb.send("mx.edu.ebc.task.all", {});
  console.log("Ready 😎 ...");
};

$("#new_task").on("submit", function(event){
  var description = $("input[name=description]").val()
  var task = { 'description': description, 'status': 'TODO' }
  eb.send("mx.edu.ebc.task.save", task);
  event.preventDefault();
});
