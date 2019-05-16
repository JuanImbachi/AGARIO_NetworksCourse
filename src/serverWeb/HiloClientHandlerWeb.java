package serverWeb;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import dataBaseServer.DataBaseServer;

public class HiloClientHandlerWeb extends Thread{
	
	private Socket socket;
	private ServerWeb server;
	
	public HiloClientHandlerWeb(Socket socket, ServerWeb server) {
		
		this.socket = socket;
		this.server = server;
	}
	
	public void run() {
		//System.out.println(this.socket);
		
		handleRequest(this.socket);
	}

	private void handleRequest(Socket socket2) {

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String headerLine = in.readLine();
			System.out.print("");
			// A tokenizer is a process that splits text into a series of tokens
			StringTokenizer tokenizer =  new StringTokenizer(headerLine);
			//The nextToken method will return the next available token
			String httpMethod = tokenizer.nextToken();
			// The next code sequence handles the GET method. A message is displayed on the
			// server side to indicate that a GET method is being processed
			if(httpMethod.equals("GET"))
			{
				System.out.println("Get method processed");
				String httpQueryString = tokenizer.nextToken();
				//System.out.println(httpQueryString);
				if(httpQueryString.equals("/")|| httpQueryString.contains("/?usr=?"))
				{
					StringBuilder responseBuffer =  new StringBuilder();
					String str="";
					BufferedReader buf = new BufferedReader(new FileReader("web/LoginAgario.html"));			
					while ((str = buf.readLine()) != null) {
						responseBuffer.append(str);
				    }
					sendResponse(socket, 200, responseBuffer.toString());		
				    buf.close();
				}
				//permite obtener el dato ingresado en el submit
				if(httpQueryString.contains("/?usr="))
				{
					//System.out.println("Get method processed 2");
					String[] response =  httpQueryString.split("&");
					String usr = response[0].split("=")[1];
					String pass = response[1].split("=")[1];

					String message = DataBaseServer.LOGIN_DB + "," + usr + "," + pass;
					String nick=server.connectWithDB(message);

					if(nick != null) {
						String mensajeObtenido = server.infoUser(nick);
					//	String mensajeObtenido="";
						String[] lista = mensajeObtenido.split("=");
						StringBuilder responseBuffer =  new StringBuilder();
						responseBuffer
						.append("<html>")
						.append("<head>")
						.append("<style>")
							.append("body{")
								.append("background-image: url(\"https://www.movilzona.es/app/uploads/2016/04/agar-io-b.jpg\");")
								.append("margin : 0;")
								.append("background-size: cover;")
								.append("background-attachment: fixed;")
								.append("text-align: center;")
							.append("}")
							.append(".table_title{")
								.append("text-align: center;")
								.append("color:black;")
								.append("font-size: 40px;")
								.append("font-weight: bold;")
							.append("}")
							.append("table{")
								.append("margin : auto;")
								.append("border: 1px solid #000;")
								.append("border-collapse: collapse;")
								.append("width: 80%;")
							.append("}")
							.append("table th{")
								.append("padding: 5px;")
								.append("background-color: #255ed1;")
								.append("text-align: center;")
								.append("font-size: 20px;")
								.append("font-family: Arial,Verdana,Sans-serif,serif;")
							.append("}")
							.append("table td{")
								.append("border: 1px solid #000;")
								.append("padding: 15px;")
								.append("background-color: #d1e0ff;")
								.append("text-align: center;")
							.append("}")
							.append("table tr:hover{")
								.append("background: #666666;")
							.append("}")
							.append("table td:hover{")
							.append("background: #749ff7;")
							.append("color:white;")
						.append("}")
						.append("</style>")
						.append("<title>Icesi Games SA - AgarIO</title>")
						.append("</head>")
						.append("<body>")
						.append("<h1> AgarIO - Player History</h1>")
						.append("<h2>Nickname: "+nick+"</h1>")
						.append("<table>")
						.append("<tr>")
						.append("<th><strong>Score</strong></th>")
						.append("<th><strong>Date</strong></th>")
						.append("<th><strong>Players</strong></th>")
						.append("<th><strong>Result</strong></th>");
						infoTable(lista,responseBuffer);
						responseBuffer.append("<body>")
						.append("<table>")
						
						.append("<body>")
						.append("</html>");
						
						sendResponse(socket, 200, responseBuffer.toString());		
					    
					}else {
						StringBuilder responseBuffer =  new StringBuilder();
						String str="";
						BufferedReader buf = new BufferedReader(new FileReader("web/LoginAgario.html"));			
						while ((str = buf.readLine()) != null) {
							responseBuffer.append(str);
					    }
						sendResponse(socket, 200, responseBuffer.toString());		
					    buf.close();
					}
					
				}
			}
			else
			{
				System.out.println("The HTTP method is not recognized");
				sendResponse(socket, 405, "Method Not Allowed");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	private void infoTable(String[] lista , StringBuilder responseBuffer ) {
		
		
		for (int i = 0; i < lista.length; i++) {
				responseBuffer.append("<tr>");
				responseBuffer.append("<td>"+lista[i].split(",")[0]+"</td>");
				responseBuffer.append("<td>"+lista[i].split(",")[1]+"</td>");
				responseBuffer.append("<td>"+lista[i].split(",")[2]+"</td>");
				responseBuffer.append("<td>"+lista[i].split(",")[3]+"</td>");
				responseBuffer.append("<tr>");
			
		}
		
	}
	
	public void sendResponse(Socket socket, int statusCode, String responseString)
	{
		String statusLine;
		String serverHeader = "Server: WebServer\r\n";
		String contentTypeHeader = "Content-Type: text/html\r\n";
		
		try {
			DataOutputStream out =  new DataOutputStream(socket.getOutputStream());
			if (statusCode == 200) 
			{
				statusLine = "HTTP/1.0 200 OK" + "\r\n";
				String contentLengthHeader = "Content-Length: "
				+ responseString.length() + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes(serverHeader);
				out.writeBytes(contentTypeHeader);
				out.writeBytes(contentLengthHeader);
				out.writeBytes("\r\n");
				out.writeBytes(responseString);
				} 
			else if (statusCode == 405) 
			{
				statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			} 
			else 
			{
				statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}