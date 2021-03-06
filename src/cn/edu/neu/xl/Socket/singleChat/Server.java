package cn.edu.neu.xl.Socket.singleChat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
	public static void main(String args[]) {
		         try {
		             //创建一个socket对象
		             ServerSocket s = new ServerSocket(8888);
		             //socket对象调用accept方法，等待连接请求
		             Socket s1 = s.accept();
		             //打开输出流
		             OutputStream os = s1.getOutputStream();
		             //封装输出流
		             DataOutputStream dos = new DataOutputStream(os);
		             //打开输入流
		             InputStream is = s1.getInputStream();
		             //封装输入流
		             DataInputStream dis = new DataInputStream(is);
		             //读取键盘输入流
		             InputStreamReader isr = new InputStreamReader(System.in);
		             //封装键盘输入流
		             BufferedReader br = new BufferedReader(isr);
		 
		             String info;
		             while (true) {
		                 //接受客户端发送过来的信息
		                 info = dis.readUTF();
		                 //打印接受的信息
		                 System.out.println("对方说: " + info);
		                 //如果发现接受的信息为：bye，那么就结束对话
		                 if (info.equals("bye"))
		                     break;
		                 //读取键盘的输入流
		                 info = br.readLine();
		                 //写入到网络连接的另一边，即客户端
		                 dos.writeUTF(info);
		                 //如果服务器自己说：bye，也是结束对话
		                 if (info.equals("bye"))
		                     break;
		             }
		             //关闭输入流
		             dis.close();
		             //关闭输出流
		             dos.close();
		             //关闭socket对象
		             s.close();
		             s.close();
		         } catch (SocketException e) {
		             System.out.println("网络连接异常，程序退出!");
		         } catch (IOException e) {
		             e.printStackTrace();
		         }
		     }

}
