package prac3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DnsRmiIntf extends Remote {
public boolean addHost(String hostname,String hostip) throws RemoteException;
public String lookupHost(String hostname)  throws  RemoteException;
public String removeHost(String hostname) throws RemoteException;
}
