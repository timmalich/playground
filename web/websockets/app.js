// npm install websocket
// npm install string-math

const http = require('http');
const WebSocketServer = require('websocket').server;
const server = http.createServer();
var stringMath = require('string-math');

server.listen(9898);
const wsServer = new WebSocketServer({
    httpServer: server
});
var ids=[];
wsServer.on('request', function(request) {
    const connection = request.accept(null, request.origin);
    connection.on('message', function(message) {
	  console.log(message);
	  data = message.utf8Data;
	  if(data.startsWith("id:")){
		  var id = data.split(":")[1];
		  var math = data.split(":")[2];
		  try{
			ids[id] += stringMath(math);
			connection.sendUTF(ids[id]);
		  }catch(ex) {
			  console.log(ex);
			  connection.sendUTF("INVALID MATH!!!");
		  }
	  }else{
		  console.log("Granting new session");
		  ids.push(0);
		  connection.sendUTF('id:' + (ids.length-1));
	  }
	});
    connection.on('close', function(reasonCode, description) {
        console.log('Client has disconnected.');
    });
});
