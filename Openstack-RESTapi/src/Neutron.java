import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class Neutron {
	private HttpURLConnection con;
	private String url;
	
	public Neutron() throws IOException{
		
	}
	
	/*
	 * Lists networks to which the specified tenant has access.
	 * 
	 */
	public void getNetwors(String token) throws IOException{

		url = "http://controller:9696/v2.0/networks";
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
	 * Lists subnets to which the specified tenant has access.
	 * 
	 */
	public void getSubnet(String token) throws IOException{
		url = "http://controller:9696/v2.0/subnets";
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
	 * Lists ports to which the tenant has access.
	 * 
	 */
	public void getPorts(String token) throws IOException{
		url = "http://controller:9696/v2.0/ports";
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
	 * Lists routers to which the tenant has access.
	 * 
	 */
	public void getRouters(String token) throws IOException{
		url = "http://controller:9696/v2.0/routers";
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
	 * Creates a network.
	 * It returns the network ID.
	 * 
	 */
	public String createNetwork(String token, String network_name) throws IOException{	
		url = "http://controller:9696/v2.0/networks";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		con.setRequestProperty("Content-Type", "application/json");
		String urlParameters = "{\"network\":{ \"name\":\""+network_name+"\",\"admin_state_up\":true}}";
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		// Response
		int responseCode = con.getResponseCode();
		
		System.out.println("\nSending 'POST' request to URL : " + url);
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
		
		InputStream iss = new ByteArrayInputStream(response.toString().getBytes());
		JsonReader rdr = Json.createReader(iss);	
		
 
		JsonObject objjj = rdr.readObject();
		String network_id = objjj.getJsonObject("network").getJsonString("id").getString();
		
		return network_id;
	}
	
	/*
	 * Lists, shows information for, creates, updates, and deletes subnet resources.
	 * It returns the subnet ID.
	 * N.B. sul sito l'esempio di request è sbagliato
	 * 
	 */
	public String createSubnet(String token, String name, String network_id, String cidr, String gateway_ip) throws IOException{
		url = "http://controller:9696/v2.0/subnets";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		con.setRequestProperty("Content-Type", "application/json");
			
		String urlParameters = "{\"subnet\":{\"name\":\""+name+"\",\"network_id\":\""+network_id+"\",\"gateway_ip\":\""+gateway_ip+"\",\"ip_version\":4,\"cidr\":\""+cidr+"\"}}";
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
		System.out.println(response.toString());
		
		InputStream iss = new ByteArrayInputStream(response.toString().getBytes());
		JsonReader rdr = Json.createReader(iss);	
		
 
		JsonObject objjj = rdr.readObject();
		String subnet_id = objjj.getJsonObject("subnet").getJsonString("id").getString();
		
		return subnet_id;
		
	}
	
	/*
	 * Creates a router.
	 * It returns the router ID.
	 * 
	 */
	public String createRouter(String token, String name, String external_network_uuid) throws IOException{
		url = "http://controller:9696/v2.0/routers";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		con.setRequestProperty("Content-Type", "application/json");
		String urlParameters = "{\"router\":{\"name\":\""+name+"\",\"admin_state_up\":true,\"external_gateway_info\":{\"network_id\":\""+external_network_uuid+"\"}}}";
		
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
		System.out.println(response.toString());
		
		InputStream iss = new ByteArrayInputStream(response.toString().getBytes());
		JsonReader rdr = Json.createReader(iss);	
		
 
		JsonObject objjj = rdr.readObject();
		String router_id = objjj.getJsonObject("router").getJsonString("id").getString();
		
		return router_id;
	}
	
	public void addInterfare(String token, String router_id, String subnet_id, String gateway_ip_address) throws IOException{
		url = "http://controller:9696/v2.0/routers/"+router_id+"/add_router_interface";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json");
			
		String urlParameters = "{\"subnet_id\":\""+subnet_id+"\"}";
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		// Response
		int responseCode = con.getResponseCode();
		
		System.out.println("\nSending 'PUT' request to URL : " + url);
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
		System.out.println(response.toString());
	}
	
	


}
