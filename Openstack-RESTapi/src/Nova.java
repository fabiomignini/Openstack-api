import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;


public class Nova {

	private HttpURLConnection con;
	private String url;
	
	public Nova() throws IOException{	
	}
	
	/*
	 * Lists details for all servers.
	 * 
	 */
	public void getServer(String token, List<String> lTenants) throws IOException{
		Iterator<String> i = lTenants.iterator();
		while(i.hasNext()){
			String s = i.next();
			if(!s.equals("e22ff4fbcf4148e49cf2a5d1e17056ce")) continue;
			url = "http://130.192.225.135:8774/v2/e22ff4fbcf4148e49cf2a5d1e17056ce/servers/detail";
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
	}
	
	/*
	 * Lists all details for available flavors.
	 * 
	 */
	public void getFlavors(String token) throws IOException{
		url = "http://130.192.225.135:8774/v2/e22ff4fbcf4148e49cf2a5d1e17056ce/flavors/detail";
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
	public void createServer(String token, String image_id, String flavor_id, String network_uuid) throws IOException{
		url = "http://130.192.225.135:8774/v2/e22ff4fbcf4148e49cf2a5d1e17056ce/servers";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		con.setRequestProperty("Content-Type", "application/json");
			
		String urlParameters = "{\"server\":{\"name\":\"server-test-1\",\"imageRef\":\""+image_id+"\",\"flavorRef\":\""+flavor_id+"\",\"max_count\":1,\"min_count\":1,\"networks\":[{\"uuid\":\""+network_uuid+"\"}]}}";
		
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
	}
}
