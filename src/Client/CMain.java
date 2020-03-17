package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class CMain {

	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub

	
		Socket withServer = null;


		withServer = new Socket("10.0.0.96", 9999);
		new ClientChat(withServer);
	

	}

}
