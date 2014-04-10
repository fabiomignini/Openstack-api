import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class Glance {
	
	private HttpURLConnection con;
	private String url;
	private BufferedInputStream bis;
	
	public Glance() throws IOException{
		
	}
	
	/*
	 * Deletes a specified image.
	 * 
	 */
	public void deleteImage(String token, String image_id) throws IOException{
		url = "http://130.192.225.135:9292/v2/images/"+image_id;
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
	
	
	/*
	 * Gets details for a specified image.
	 * 
	 */
	public String getImageDetails(String token, String image_id) throws IOException{
		url = "http://130.192.225.135:9292/v2/images/"+image_id;
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
		
		return null;		
	}
	
	/*
	 * Lists public virtual machine (VM) images.
	 * 
	 */
	public String getImages(String token) throws IOException{
		url = "http://130.192.225.135:9292/v2/images";
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
		
		return null;		
	}
	
	/*
	 * Creates a virtual machine (VM) image, it returns the ID of the created image.
	 * 
	 */
	public String createImage(String token) throws IOException{
		url = "http://130.192.225.135:9292/v2/images";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		
		con.setRequestProperty("Content-Type", "application/json");
		
		
		String urlParameters = "{\"name\": \"test-create-image\",\"visibility\": \"public\",\"container_format\": \"bare\", \"disk_format\": \"qcow2\"}";

		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		
		
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
		
		System.out.println(response);
		
		//InputStream iss = new ByteArrayInputStream(response.toString().getBytes());
		//JsonReader rdr = Json.createReader(iss);		
 
		//JsonObject objjj = rdr.readObject();
		//objjj.getJsonObject(arg0)
		
		return null;
		
	}
	
	/*
	 * Updates a specified image.
	 * 
	 */
	public void uploadImage(String token, String image_id) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		
		url = "http://130.192.225.135:9292/v2/images/"+image_id+"/file";
		URL obj = new URL(url);
		File file = new File("/Users/fabiomignini/ubuntu-12.04-server-cloudimg-i386-disk1.img");
		
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("Content-Type", "application/octet-stream");		
		con.setRequestProperty("User-Agent", "python-keystoneclient");
		con.setRequestProperty("X-Auth-Token", token);
		
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		bis = new BufferedInputStream(new FileInputStream(file));
		int i;
		// Read byte by byte until end of stream
		while (bis.available()>0) {
			i = bis.read();
			wr.write(i);
		}
		wr.flush();
		wr.close();
	
		// Get post response
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'PUT' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		System.out.println(response);	
	}
	
	/*
	 * Used to bypass problems with PATCH method
	 * 
	 */
	@SuppressWarnings({ "unused" })
	private static final void setRequestMethodUsingWorkaroundForJREBug(final HttpURLConnection httpURLConnection, final String method) {
		try {
			httpURLConnection.setRequestMethod(method);
			// Check whether we are running on a buggy JRE
		} catch (final ProtocolException pe) {
			Class<?> connectionClass = httpURLConnection
					.getClass();
			Field delegateField = null;
			try {
				delegateField = connectionClass.getDeclaredField("delegate");
				delegateField.setAccessible(true);
				HttpURLConnection delegateConnection = (HttpURLConnection) delegateField
						.get(httpURLConnection);
				setRequestMethodUsingWorkaroundForJREBug(delegateConnection, method);
			} catch (NoSuchFieldException e) {
				// Ignore for now, keep going
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			try {
				Field methodField;
				while (connectionClass != null) {
					try {
						methodField = connectionClass
								.getDeclaredField("method");
					} catch (NoSuchFieldException e) {
						connectionClass = connectionClass.getSuperclass();
						continue;
					}
					methodField.setAccessible(true);
					methodField.set(httpURLConnection, method);
					break;
				}
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}


