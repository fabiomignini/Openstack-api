import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;


public class Nova {

	private HttpURLConnection con;
	private String url;
	private JsonString server_id;
	
	public Nova() throws IOException{	
	}
	
	/*
	 * Lists details for all servers.
	 * 
	 */
	public void getServer(String token, String tenant_admin_id) throws IOException{
		
		url = "http://controller:8774/v2/"+tenant_admin_id+"/servers/detail";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		con.setRequestProperty("Accept", "application/json");
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
		System.out.println("\n OK\n\n");
		System.out.println(response.toString());
	}
	
	/*
	 * Lists all details for available flavors.
	 * 
	 */
	public void getFlavors(String token, String tenant_admin_id) throws IOException{
		url = "http://controller:8774/v2/"+tenant_admin_id+"/flavors/detail";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		con.setRequestProperty("Accept", "application/json");
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
	}

	/*
	 * Creates a server.
	 * 
	 */
	public void createServer(String token, String image_id, String flavor_id, String network_uuid, String tenant_admin_id, String admin_pass, String name) throws IOException{
		url = "http://controller:8774/v2/"+tenant_admin_id+"/servers";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		con.setRequestProperty("Content-Type", "application/json");
			
		//String urlParameters = "{\"server\":{\"name\":\"server-test-3\",\"imageRef\":\""+image_id+"\",\"key_name\": \"mykey\",\"flavorRef\":\""+flavor_id+"\",\"networks\":[{\"uuid\":\""+network_uuid+"\"}],\"security_groups\": [{\"name\": \"default\"}]}}";
		//String urlParameters = "{\"server\":{\"name\":\"test Boot da casa2\",\"imageRef\":\"b023b072-0bac-420a-bac8-cf094be80c4e\",\"flavorRef\":\"1\",\"adminPass\":\"stack\",\"networks\":[{\"uuid\":\"0aaedccc-726c-4441-82f2-04a886f19a1f\"}]}}";
		String urlParameters = "{\"server\":{\"name\":\""+name+"\",\"imageRef\":\""+image_id+"\",\"flavorRef\":\""+flavor_id+"\",\"adminPass\":\""+admin_pass+"\",\"networks\":[{\"uuid\":\""+network_uuid+"\"}]}}";

		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		// Response
		int responseCode = con.getResponseCode();
		
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Parameters : " + urlParameters);
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
		System.out.println("\n OK\n\n");
		System.out.println(response.toString());
		
		InputStream iss = new ByteArrayInputStream(response.toString().getBytes());
		JsonReader rdr = Json.createReader(iss);	
		
 
		JsonObject objjj = rdr.readObject();
		server_id = objjj.getJsonObject("server").getJsonString("id");
	}
	
	/*
	 * Get server's ID of the created server
	 * 
	 */
	public String getServerId(){
		return server_id.getString();
	}
	
	/*
	 * Delete a specific server.
	 * 
	 */
	public void deleteServer(String token, String tenant_admin_id, String server_id) throws IOException{
		url = "http://controller:8774/v2/"+tenant_admin_id+"/servers/"+server_id;
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		int responseCode = con.getResponseCode();
		
		System.out.println("\nSending 'DELETE' request to URL : " + url);
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
	}
}
