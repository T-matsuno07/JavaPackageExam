package sample.lib1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote {
    void sendData(String i) throws RemoteException;
}