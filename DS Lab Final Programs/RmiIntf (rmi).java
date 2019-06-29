package prac3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiIntf extends Remote {
public int add(int a,int b) throws RemoteException;
}
