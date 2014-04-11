import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class OpenStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//final String ext_net_name = "ext-net";
			final String admin_pass = "stack";
			final String flavor_tiny = "1";
					
			String gateway_ip_address = "192.168.5.1";
			
			Keystone keystone = new Keystone();			
			Glance glance = new Glance();
			Nova nova = new Nova();
			Neutron neutron = new Neutron();
			//List<String> lTenants = new ArrayList<>();	
			
			String token = keystone.authenticateAndGetToken("demo","stack","demo");
			//lTenants = keystone.getTenantList(token);
			//keystone.getVersion(token);
			
			
			String network_id = neutron.createNetwork(token, "DEMO-api-net-demo");
			String subnet_id = neutron.createSubnet(token,"DEMO-api-subnet-demo", network_id, "192.168.5.0/24", gateway_ip_address);
			String router_id = neutron.createRouter(token, "DEMO-api-router-demo","da1ec0dd-07a4-4b11-a6b7-535970a4aca4");
			neutron.addInterfare(token, router_id, subnet_id, gateway_ip_address);
			
			
			//neutron.getNetwors(token);			
			//neutron.getSubnet(token);
			//neutron.getPorts(token);
			//neutron.getRouters(token);		
			
			glance.createImage(token, "DEMO-images-demo");
			glance.uploadImage(token, glance.getImageId());
			
			//glance.deleteImage(token, glance.getImageId());
			//glance.getImages(token);
			//glance.getImageDetails(token, glance.getImageId());	
			
			//nova.getFlavors(token, keystone.getTenantId());
			
			nova.createServer(token, glance.getImageId(), flavor_tiny, network_id, keystone.getTenantId(), admin_pass, "DEMO-instance-demo");			
			
			//nova.deleteServer(token, keystone.getTenantId(), nova.getServerId());
			//nova.getServer(token, lTenants, keystone.getTenantId());
			
				
			
		} catch(Exception e){
			System.out.println("\n\n"+e.getMessage()+"\n\n");
			e.printStackTrace();
		    System.exit(e.hashCode());
		}
	}		 
}
