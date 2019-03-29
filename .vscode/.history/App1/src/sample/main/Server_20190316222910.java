package sample.main;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements IFRmiInterface {

  public static void main(String[] args) throws Exception {
    Server obj = new Server();
    RmiInterface stub = (RmiInterface) UnicastRemoteObject.exportObject((Remote) obj, 0);
        LocateRegistry.getRegistry().bind("RmiTest", stub);//ここでRmiTestの名前で接続を待つ
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                //unbindせずにプロセスが死ぬと、再度bindする時に怒られてrmiregistryプロセスの再起動が必要になる
                try {
                    LocateRegistry.getRegistry().unbind("RmiTest");
                } catch (RemoteException | NotBoundException e) {
                    e.printStackTrace();
                }
            }
        }));
        Thread.sleep(999 * 1000);//終了されるとマズいから手抜き
    }

    @Override
    public void sendData(String i) {//クライアントのインスタンスからコールされる
        System.err.println(i);
    }
}