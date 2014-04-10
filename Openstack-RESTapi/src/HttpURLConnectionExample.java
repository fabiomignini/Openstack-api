import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.json.Json;
//import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
 
public class HttpURLConnectionExample {
 
	//private String token;
 
	public static void main(String[] args) throws Exception {
 
		HttpURLConnectionExample http = new HttpURLConnectionExample();	
 
		//test
		//facebookTest();
		
		System.out.println("\nAutenticazione");
		http.sendPost();
		
		//System.out.println("\nList Images");
		//http.sendGet();
		
	}
	
	
 
	// HTTP GET request
	/*private void sendGet() throws Exception {
 
		String url = "http://130.192.225.135:9292/v2/images";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}*/
 
	// HTTP POST request
	private void sendPost() throws Exception {
		//String url = "https://selfsolve.apple.com/wcResults.do";
		String url = "http://130.192.225.135:35357/v2.0/tokens";
		URL obj = new URL(url);
		//obj.
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		//add reuqest header
		
		//con.setRequestProperty("User-Agent", USER_AGENT);
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
 
		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		String urlParameters = "{\"auth\": {\"tenantName\": \"admin\", \"passwordCredentials\": {\"username\": \"admin\", \"password\": \"stack\"}}}";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		/************************************/
		InputStream is = new ByteArrayInputStream(response.toString().getBytes());
		JsonParser parser = Json.createParser(is);
		while (parser.hasNext()) {
			Event e = parser.next();
		    if (e == Event.KEY_NAME) {
		    	switch (parser.getString()) {
		        case "access":
		        	parser.next();
		        	//System.out.print(parser.getString());
		        	System.out.print("access\n");
		        	break;
		        case "token":
		            parser.next();
		            //System.out.println(parser.getString());
		            System.out.println("token");
		            break;
		    	case "id":
		    		parser.next();
		            System.out.println(parser.getString());
		            System.out.println("id");
		            break;
		    	}
	        }
		 }
		
	
		
		/************************************/
		
		InputStream iss = new ByteArrayInputStream(response.toString().getBytes());
		JsonReader rdr = Json.createReader(iss);	
		
 
		JsonObject objjj = rdr.readObject();
		JsonString token = objjj.getJsonObject("access").getJsonObject("token").getJsonString("id");
		
		
		System.out.println("/n/nTest token: "+token+"/n/n");
		
		/************************************/
		
		
		
		//print result
		System.out.println(response.toString());
		int start = response.indexOf("\"id\": \"");
		int end = response.indexOf("\", \"tenant\"");
		System.out.println("start: "+start+"\nend: "+end);
		
		/*token = response.substring(start+7, end);
		System.out.println(token);*/
		
		
	}
 
}