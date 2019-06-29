package prac3;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
public static void main(String[] args) {
RmiIntf ob = null;
try {
	ob = (RmiIntf) new Impl();
} catch (RemoteException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
	LocateRegistry.createRegistry(1478);
} catch (RemoteException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try {
	Naming.rebind("rmi://localhost:1478/demo", ob);
} catch (RemoteException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
