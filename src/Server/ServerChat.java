package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ServerChat extends Thread {

	private Socket withClient = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private ServerCenter sc=null;
	ServerChat(Socket c,ServerCenter s) {
		this.withClient = c;
		this.sc=s;
		// streamSet();
	}

	@Override
	public void run() {
		// ServerChat(Socket c){ �� �ִ� streamSet();�� ��Ƽ������ ������� override ���� ������
		streamSet();
		receive();
		// send();

	}

	private void receive() {
		// recevie() �� ������ ������ ó��
		new Thread(new Runnable() {

			@Override
			public void run() { // �������� ������ �޴±�ɸ�
				// TODO Auto-generated method stub
				try {
					System.out.println("receive start!");
					while (true) {
						reMsg = withClient.getInputStream();
						byte[] reBuffer = new byte[100];
						reMsg.read(reBuffer);
						String msg = new String(reBuffer);
						sc.reMsg(msg,id);
						msg = msg.trim();

						if (msg.indexOf("/bye") == 0) {
							endChat();
							break;
						} else {
						//	System.out.println("[" + id + "]" + "��" + msg + "����");
						}

					}

				} catch (Exception e) {
					System.out.println("receive end");
					return;

				}

			}

		}).start();

	}

	private void endChat() {
		// TODO Auto-generated method stub
		try {
			System.out.println("��!");
			withClient.close();
			reMsg.close();
			sendMsg.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void send(String reMsg) {
		// private ���� public ���� �ؼ� �Ű����� �ְ� �ް�
	
		try {
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (Exception e) {
			System.out.println("send end");
			return;
		}
	}

	private void streamSet() {
		try {
			reMsg = withClient.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			id = new String(reBuffer);
			id = id.trim(); // Ʈ�� ���� ����

			InetAddress c_ip = withClient.getInetAddress();
			// withClient ��� ��Ĺ���� getInetAddress �� �޾Ƽ� c_ip�� ����
			String ip = c_ip.getHostAddress();
			// c_ip�� string���� ����
			System.out.println(id + "�� �α��� �ϼ̽��ϴ�");

			String reMsg = "�������� �Ǿ����ϴ�";
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
