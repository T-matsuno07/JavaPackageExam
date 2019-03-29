package sample.main;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements IFRmiInterface {

  public static void main(String[] args) throws Exception {
    Server obj = new Server();
    IFRmiInterface stub = (IFRmiInterface) UnicastRemoteObject.exportObject((Remote) obj, 0);
        LocateRegistry.getRegistry().bind("RmiTest", stub);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {

                try {
                    LocateRegistry.getRegistry().unbind("RmiTest");
                } catch (RemoteException | NotBoundException e) {
                    e.printStackTrace();
                }
            }
        }));
        Thread.sleep(999 * 1000);
    }

    @Override
    public void sendData(String i) {
        System.err.println(i);
    }
}