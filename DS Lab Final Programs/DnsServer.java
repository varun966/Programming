package prac3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class DnsServer extends UnicastRemoteObject implements DnsRmiIntf{
File namelist;
FileInputStream fin;
FileOutputStream fout;
Properties hostrecords;

	protected DnsServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		namelist=new File("E:/temp/namelist5.txt");
		hostrecords=new Properties();
		namelist.setReadOnly();
	}

	@Override
	public boolean addHost(String hostname, String hostip)
			throws RemoteException {
		// TODO Auto-generated method stub
		namelist.setWritable(true);
		try {
			fin=new FileInputStream(namelist);
			hostrecords.load(fin);
			fin.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		if(hostrecords.get(hostname)!=null)
		{
			return false;
		}
		hostrecords.put(hostname, hostip);
		try {
			fout=new FileOutputStream(namelist);
			hostrecords.store(fout,"");
			fout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		namelist.setReadOnly();
				
		
		
		return true;
	}

	@Override
	public String lookupHost(String hostname) throws RemoteException {
		// TODO Auto-generated method stu
		String ip=null;
		try {
			fin=new FileInputStream(namelist);
			hostrecords.load(fin);
			fin.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ip=(String) hostrecords.get(hostname);
		return ip;
	}

	@Override
	public String removeHost(String hostname) throws RemoteException {
		// TODO Auto-generated method stub
		namelist.setWritable(true);
		String ip=null;
		try {
			fin=new FileInputStream(namelist);
			hostrecords.load(fin);
			fin.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ip=(String) hostrecords.remove(hostname);
		try {
			fout=new FileOutputStream(namelist);
			hostrecords.store(fout,"");
			fout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		namelist.setReadOnly();
		return ip;
	}
	
public static void main(String[] args) {
	try {
		DnsRmiIntf ob=new DnsServer();
		LocateRegistry.createRegistry(1777);
		Naming.rebind("rmi://localhost:1777/demo",ob);
		System.out.println("object bounded");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
