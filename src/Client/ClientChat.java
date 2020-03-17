package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {

	private Socket withServer = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private Scanner input = new Scanner(System.in);

	ClientChat(Socket c) {
		this.withServer = c;
		streamSet();
		send();
		receive();
	}

	private void receive() {
		// recevie() 만 별도로 쓰레드 처리
		new Thread(new Runnable() {

			@Override
			public void run() { // 독자적인 스레드 받는기능만
				// TODO Auto-generated method stub
				try {
					System.out.println("recevie start!");
					while (true) {
						reMsg = withServer.getInputStream();
						byte[] reBuffer = new byte[100];
						reMsg.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
						System.out.println("[server]" + msg);
					}
				} catch (Exception e) {

				}

			}
		}).start();

	}

	private void send() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("send start!");
					Scanner in = new Scanner(System.in);
					while (true) {
						String reMsg = in.nextLine();
						sendMsg = withServer.getOutputStream();
						sendMsg.write(reMsg.getBytes());
						System.out.println("메세지 전송  완료");
						if (reMsg.indexOf("/bye") == 0) {
							endChat();
							break;
						}
					}
				} catch (Exception e) {

				}
			}

		}).start();

	}

	private void endChat() {
		// TODO Auto-generated method stub
		try {
			System.out.println("중단");
			withServer.close();
			reMsg.close();
			sendMsg.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void streamSet() {

		try {
			System.out.println("ID 를 입력 하세요");
			id = input.nextLine();
			sendMsg = withServer.getOutputStream();
			sendMsg.write(id.getBytes());

			reMsg = withServer.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			String msg = new String(reBuffer);
			msg = msg.trim();
			System.out.println(msg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
