import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;


public class Keystone {
	
	private HttpURLConnection con;
	private String url;
	private JsonString token;
	private JsonString expires;
	
	public Keystone() throws IOException{	    
	}

	/*
	 * Si autentica a Keystone. Se l'autenticazione è andata
	 * a buon fine restituisce il Token, se no restituisce null.
	 * 
	 */
	public String authenticateAndGetToken() throws IOException{
		url = "http://130.192.225.135:35357/v2.0/tokens";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
		
		String urlParameters = "{\"auth\": {\"tenantName\": \"admin\", \"passwordCredentials\": {\"username\": \"admin\", \"password\": \"stack\"}}}";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		// Get post response
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
		
		InputStream iss = new ByteArrayInputStream(response.toString().getBytes());
		JsonReader rdr = Json.createReader(iss);	
		
 
		JsonObject objjj = rdr.readObject();
		token = objjj.getJsonObject("access").getJsonObject("token").getJsonString("id");
		expires = objjj.getJsonObject("access").getJsonObject("token").getJsonString("expires");

		
		System.out.println("\n\nTest token: "+token.getString()+"\n");
		System.out.println("Test expires: "+expires+"\n\n");
		
		return token.getString();
	}
	
	/*
	 * Get Tenant List.
	 * Returns a List of Tenant.
	 * 
	 */
	public List<String> getTenantList(String token) throws IOException{
		url = "http://130.192.225.135:35357/v2.0/tenants";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
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
		
		//parse information in the JSON response
		InputStream iss = new ByteArrayInputStream(response.toString().getBytes());
		JsonReader rdr = Json.createReader(iss);	
		
		JsonObject objjj = rdr.readObject();
		List<String> lTenant = new ArrayList<String>();
		
		Iterator<JsonValue> i = objjj.getJsonArray("tenants").iterator();
		while( i.hasNext()){
			JsonObject jv = (JsonObject) i.next();
			
			System.out.println("\n\n\n\n");
			System.out.println(jv.getJsonString("id").getString());
			lTenant.add(jv.getJsonString("id").getString());
		}
		
		
		return lTenant;
	}
	
	/*
	 * Get api version.
	 * 
	 */
	public void getVersion(String token) throws IOException{
		url = "http://130.192.225.135:35357/v3";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
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
	}
	
	/*
	 * Restituisce il token di un autenticazione già effettuata,
	 * se non siamo autenticato o il token è scaduto restituisce null.
	 * 
	 * N.B. Non ancora implementato
	 */
	public String getToken(){
		return null;	
	}

}
