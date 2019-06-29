package prac3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cserver implements ActionListener {
JFrame jfr;
JTextArea ta;
JTextField tf;
JButton b1;
ServerSocket ss;
Socket s;
InputStream is=null;
OutputStream os;
public Cserver() {
	// TODO Auto-generated constructor stub
	try
	{
		
		ss=new ServerSocket(4444);
		s=ss.accept();
		os=s.getOutputStream();
		is=s.getInputStream();
	}
	catch(Exception e)
	{
		
	}
jfr=new JFrame();
jfr.setTitle("Server Messenger");
jfr.setSize(300, 400);
jfr.setLayout(new FlowLayout());
ta=new JTextArea(10,20);
tf=new JTextField(20);
b1=new JButton("Send");
jfr.add(ta);
jfr.add(tf);
jfr.add(b1);
b1.addActionListener(this);
jfr.setVisible(true);
chat();
}

public static void main(String[] args) {
	new Cserver();
}
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
	String tf1="Server: "+tf.getText();
	String tf2=tf.getText();
	ta.append("\n"+tf1);
	try {
		os.write(tf2.getBytes());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void chat() {
	// TODO Auto-generated method stub
	while(true)
	{
	byte[] b=new byte[1000];
	try {
		is.read(b);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
String n=new String(b).trim()+" :Client";
ta.append("\t\t"+n);
	}
	
}
}
