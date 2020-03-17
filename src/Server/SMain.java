package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SMain {
	

	public static void main(String[] args) throws Exception{
		// 서버와 클라이언트와 양방향 통신 프로그램 버전 1.0
		//TCP 통신을 하기 위한 자원
		ServerSocket serverS=null; // 서버를 기다림
		//ServerSocket 클래스는 TCP 서버의 역할을 합니다. 클라이언트의 연결 요청을 기다리며 요청이 오면 요청을 수락합니다.

		Socket withClient=null; 
		//통신하기 위한 자원
		
		serverS= new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.96",9999)); //바인딩
		
		ArrayList<Socket>cList= new ArrayList<>(); // 저장용
		ServerCenter sc = new ServerCenter();
		//서버센터에 넘기기 위해서 서버센터 객체 만들어준것 
		
		while(true) {
		System.out.println("서버 대기중");
		withClient=serverS.accept(); 
		// 클라이언트 대기  socket을 리턴   withClient이 소캣  클라이언트랑 통신 하는 소캣 만들어줌
		cList.add(withClient);
		System.out.println(cList);
		System.out.println(withClient.getInetAddress()+"클라이언트 접속 함");
		ServerChat s=new ServerChat(withClient,sc);
		sc.addServaerChat(s);
		//serverchat s 를  sc servercenter에 넘겨줌
		s.start();
		
		}
	}

}
