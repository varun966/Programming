package prac3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FtpServer implements ActionListener
{
JFrame jfr;
JButton b1;
JTextArea ta;
ServerSocket ss;
Socket s;

public FtpServer() throws IOException {
	// TODO Auto-generated constructor stub
	ss=new ServerSocket(7777);
	s=ss.accept();
	jfr=new JFrame();
	jfr.setTitle("server Files");
	jfr.setSize(300,400);
	jfr.setLayout(new FlowLayout());
	ta=new JTextArea(10, 20);
	b1=new JButton("refresh");
	jfr.add(ta);
	jfr.add(b1);
	b1.addActionListener(this);
	
	jfr.setVisible(true);
	
}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
File f=new File("C:/Users/vasudev/Desktop/ftpserver");
File files[]=f.listFiles();
for(File file:files)
{
	ta.append("\n"+file.getName());
}
}
public static void main(String[] args) throws IOException {
	new FtpServer();
}
}
