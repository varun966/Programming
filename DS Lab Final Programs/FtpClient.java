package prac3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FtpClient extends JFileChooser {
JFrame jfr;
JButton b1,b2;
Socket s;
JTextArea ta;
public FtpClient() {
	// TODO Auto-generated constructor stub
	try {
		s=new Socket("localhost",7777);
	} catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	jfr=new JFrame();
	ta=new JTextArea(10,20);
	jfr.add(ta);
	if(s!=null)
	{
		ta.append("connected to client+\n");
	}
	jfr.setTitle("server Files");
	jfr.setSize(300,400);
	jfr.setLayout(new FlowLayout());
	b1=new JButton("upload");
	b2=new JButton("download");
	jfr.add(b1);
	jfr.add(b2);
	b1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFileChooser j=new JFileChooser();
			int val=j.showOpenDialog(FtpClient.this);
			String name=j.getSelectedFile().getName();
			String despath="C:/Users/vasudev/Desktop/ftpserver/"+name;
			File f=new File(despath);
			try {
				f.createNewFile();
				String sourcepath=j.getSelectedFile().getPath();
				
				copyFile(sourcepath,despath);
				ta.append("File uploaded");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});
b2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFileChooser j=new JFileChooser("C:/Users/vasudev/Desktop/ftpserver");
			int val=j.showOpenDialog(FtpClient.this);
			String name=j.getSelectedFile().getName();
			String despath="C:/Users/vasudev/Desktop/ftpclient/"+name;
			File f=new File(despath);
			try {
				f.createNewFile();
				String sourcepath=j.getSelectedFile().getPath();
				copyFile(sourcepath,despath);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});
jfr.setVisible(true);
}



protected void copyFile(String sourcepath, String despath) throws IOException {
	// TODO Auto-generated method stub
	FileChannel inpchn=null;
	FileChannel outputchn=null;
	inpchn=new FileInputStream(sourcepath).getChannel();
	outputchn=new FileOutputStream(despath).getChannel();
	outputchn.transferFrom(inpchn, 0, inpchn.size());
			
}

public static void main(String[] args) {
	new FtpClient();
}


}
