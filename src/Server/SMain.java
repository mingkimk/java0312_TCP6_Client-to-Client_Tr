package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SMain {
	

	public static void main(String[] args) throws Exception{
		// ������ Ŭ���̾�Ʈ�� ����� ��� ���α׷� ���� 1.0
		//TCP ����� �ϱ� ���� �ڿ�
		ServerSocket serverS=null; // ������ ��ٸ�
		//ServerSocket Ŭ������ TCP ������ ������ �մϴ�. Ŭ���̾�Ʈ�� ���� ��û�� ��ٸ��� ��û�� ���� ��û�� �����մϴ�.

		Socket withClient=null; 
		//����ϱ� ���� �ڿ�
		
		serverS= new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.96",9999)); //���ε�
		
		ArrayList<Socket>cList= new ArrayList<>(); // �����
		ServerCenter sc = new ServerCenter();
		//�������Ϳ� �ѱ�� ���ؼ� �������� ��ü ������ذ� 
		
		while(true) {
		System.out.println("���� �����");
		withClient=serverS.accept(); 
		// Ŭ���̾�Ʈ ���  socket�� ����   withClient�� ��Ĺ  Ŭ���̾�Ʈ�� ��� �ϴ� ��Ĺ �������
		cList.add(withClient);
		System.out.println(cList);
		System.out.println(withClient.getInetAddress()+"Ŭ���̾�Ʈ ���� ��");
		ServerChat s=new ServerChat(withClient,sc);
		sc.addServaerChat(s);
		//serverchat s ��  sc servercenter�� �Ѱ���
		s.start();
		
		}
	}

}
