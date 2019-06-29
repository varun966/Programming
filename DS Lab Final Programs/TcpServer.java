import java.io.*;
import java.net.*;

public class TcpServer {
	public static void main(String[] args) throws Exception	{
		ServerSocket ss=new ServerSocket(8088);
		System.out.println("server is ready!");
		while (true){
			Socket cs=ss.accept();
			System.out.println("Client Port is "+cs.getPort());
			//READING DATA FROM CLIENT
			InputStream is=cs.getInputStream();
			byte data[]=new byte[50];
			is.read(data);
			String mfc=new String(data).trim();
			//mfc: message from client
			String mfs="Hello:"+mfc;
			//mfs: message from server
			//SENDING MSG TO CLIENT
			OutputStream os=cs.getOutputStream();
			os.write(mfs.getBytes());
			cs.close();
		}
	}
}