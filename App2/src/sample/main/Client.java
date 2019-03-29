package sample.main;

import java.rmi.Naming;
import sample.main.*;
import sample.lib1.*;

public class Client {
    public static void main(String[] args) {
        try {
            System.setSecurityManager(new SecurityManager());
            IFRmiInterface i = (IFRmiInterface) Naming.lookup("rmi://localhost/RmiTest");
            int count = 1;
            while (true) {
                Thread.sleep(1 * 1000);
                i.sendData("ok-" + count);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}