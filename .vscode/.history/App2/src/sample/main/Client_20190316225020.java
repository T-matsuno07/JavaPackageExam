import java.rmi.Naming;
import sample.main.*;

public class Client {
    public static void main(String[] args) {
        try {
            System.setSecurityManager(new SecurityManager());//セキュリティポリシーをセット
            IFRmiInterface i = (IFRmiInterface) Naming.lookup("rmi://localhost/RmiTest");//localhost部分は固定。RmiTestをサーバー側と一致させる
            int count = 1;
            while (true) {
                Thread.sleep(1 * 1000);
                i.sendData("ok-" + count);//ここでok-1 とかの引数がサーバー側のメソッドで実行される
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}