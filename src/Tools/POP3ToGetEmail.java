package Tools;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

/**
 * 根据POP3协议获取邮件的工具
 */
public class POP3ToGetEmail {
    private static String port = "110";   // 端口号
    private static String servicePath;   // 服务器地址
    private static String user;     //账号
    private static String password;            //密码
    private static final String EASTNET = "pop.163.com";
    private static final String SINA = "pop.sina.cn";

    //登录邮箱服务器
    public boolean login(String email, String password) {
        //设置pop服务器地址
        if (email.indexOf("@163.com") != -1) {
            servicePath = EASTNET;
        } else if (email.indexOf("@sina.cn") != -1) {
            servicePath = SINA;
        }

        // 准备连接服务器的会话信息
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");       // 使用pop3协议
        props.setProperty("mail.pop3.port", port);           // 端口
        props.setProperty("mail.pop3.host", servicePath);       // pop3服务器

        Session session = Session.getInstance(props);

        Store store = null;
        Folder folder = null;
        try {
            store = session.getStore("pop3");
            store.connect(user, password); //163邮箱程序登录属于第三方登录所以这里的密码是163给的授权密码而并非普通的登录密码

            // 获得收件箱
            folder = store.getFolder("INBOX");
            /* Folder.READ_ONLY：只读权限
             * Folder.READ_WRITE：可读可写（可以修改邮件的状态）
             */
            folder.open(Folder.READ_WRITE); //打开收件箱
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if(store != null){
                    store.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
