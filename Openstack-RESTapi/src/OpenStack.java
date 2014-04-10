import java.util.ArrayList;
import java.util.List;


public class OpenStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String image_id = "03c45b5c-8567-4601-97e8-c88096299eaa";	
			String router_id;
			String network_id;
			String subnet_id;
			String gateway_ip_address = "192.168.5.1";
			Keystone keystone = new Keystone();			
			Glance glance = new Glance();
			Nova nova = new Nova();
			Neutron neutron = new Neutron();
			List<String> lTenants = new ArrayList<>();
			
			
			String token = keystone.authenticateAndGetToken();
			//lTenants = keystone.getTenantList(token);
			//keystone.getVersion(token);
			
			//network_id = neutron.createNetwork(token, "test-api-net");
			//subnet_id = neutron.createSubnet(token,"test-api-subnet", network_id, "192.168.5.0/24", gateway_ip_address);
			//router_id = neutron.createRouter(token, "test-api-router","7a372c5c-474e-47c4-ae85-1f156a740934");
			//neutron.addInterfare(token, router_id, subnet_id, gateway_ip_address);
			//neutron.getNetwors(token);			
			//neutron.getSubnet(token);
			//neutron.getPorts(token);
			//neutron.getRouters(token);
			
			//nova.getServer(token, lTenants);
			//nova.getFlavors(token);
			nova.createServer(token, image_id, "cd1c62c9-35d8-4c50-85d5-2f1cf662d785", "96d88ae4-9827-426d-a3ac-416baea08413");
			
			//glance.createImage(token);
			//glance.uploadImage(token, image_id);
			//glance.deleteImage(token, image_id);
			//glance.getImages(token);
			//glance.getImageDetails(token, image_id);
			
			
		} catch(Exception e){
			System.out.println("\n\n"+e.getMessage()+"\n\n");
			e.printStackTrace();
		    System.exit(e.hashCode());
		}
	}	
}
