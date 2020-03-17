package Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Client.ClientChat;

public class ServerCenter {

	ArrayList<ServerChat> sList = new ArrayList<>();

	public void addServaerChat(ServerChat s) {
		this.sList.add(s);

	}

	public void reMsg(String msg, String id) {
		sendAll("[" + id + "]" + msg);
	}

	public void sendAll(String msg) {
		for (int i = 0; i < sList.size(); i++) {
			sList.get(i).send(msg);
		}
	}

}
