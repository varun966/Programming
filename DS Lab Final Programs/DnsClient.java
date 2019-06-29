package prac3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DnsClient implements ActionListener {
JTextField tf1,tf2;
JLabel l1,l2;
JButton b1,b2,b3,b4;
JFrame jfr;
JPanel p1,p2;
public DnsClient() {
	// TODO Auto-generated constructor stub
	jfr=new JFrame();
	p1=new JPanel();
	p2=new JPanel();
	l1=new JLabel("Hostname");
	l2=new JLabel("Hostip");
	tf1=new JTextField(20);
	tf2=new JTextField(20);
	b1=new JButton("add");
	b2=new JButton("lookup");
	b3=new JButton("remove");
	b4=new JButton("refresh");
	p1.add(l1);
	p1.add(tf1);
	p1.add(l2);
	p1.add(tf2);
	
	jfr.add(p1,"North");
	p2.add(b1);
	p2.add(b2);
	p2.add(b3);
	p2.add(b4);
	jfr.add(p2,"South");
	jfr.setSize(600,300);
	jfr.setVisible(true);
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	
	
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	String s=e.getActionCommand();
	DnsRmiIntf dri=null;
	try {
		 dri=(DnsRmiIntf) Naming.lookup("rmi://localhost:1777/demo");
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (RemoteException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (NotBoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	if(s.equals("refresh"))
			{
		tf1.setText("");
		tf2.setText("");
		
			}
	if(s.equals("add"))
	{
		try {
			if(dri.addHost(tf1.getText(), tf2.getText())==true)
			{
				tf2.setText("registerd");
			}
			else
			{
				tf2.setText("alresdy registerd");
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	if(s.equals("lookup"))
	{
		String b=null;
		try {
			 b=dri.lookupHost(tf1.getText());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(b!=null)
		{
			tf2.setText(b);
		}
	}
	if(s.equals("remove"))
	{
		String b=null;
		try {
			 b=dri.removeHost(tf1.getText());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(b!=null)
		{
			tf2.setText("removed");
		}
	}
}
public static void main(String[] args) {
	new DnsClient();
}
}
