package Controller.SendEmails;

import Dao.SendEmailDAO;
import Dao.SendEmailDAOImpl;
import Tools.SMTPSendEmail;
import bean.Email;
import bean.SentEmail;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取附件
        SmartUpload upload = new SmartUpload();
        String path = "";
        String filename = "";
        try {

            upload.initialize(this.getServletConfig(), request, response);
            upload.upload();
            //在upload()之后调用
//            SmartRequest smartRequest = upload.getRequest();
//            String username = smartRequest.getParameter("username");
//            String password = smartRequest.getParameter("password");
//            System.out.println(username + ":" + password);
             path = "F:\\Study\\IntelliJ_IDEA\\WebEmail\\web\\upload";
            //String path = getServletContext().getRealPath("/") + "/upload/";

            upload.save(path);

             filename = upload.getFiles().getFile(0).getFileName();
            System.out.println(path+":"+filename);
            // filename = new String(filename.getBytes("GBK"),"utf-8");
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }

        //通过JSPSmartUpload获取表单非文件参数
        SmartRequest smartRequest = upload.getRequest();
        HttpSession session = request.getSession();
        Email email = (Email) session.getAttribute("currEmail");
        String email_to = smartRequest.getParameter("email_to");
        String email_cc = smartRequest.getParameter("email_cc");
        String email_bcc = smartRequest.getParameter("email_bcc");
        //附件稍后处理
        String subject = smartRequest.getParameter("subject");
        String content = smartRequest.getParameter("content");

        SentEmail sentEmail = new SentEmail();
        sentEmail.setUserName(email.getUsername());
        sentEmail.setEmailFrom(email.getEmail());
        sentEmail.setEmailTO(email_to);
        sentEmail.setEmailCC(email_cc);
        sentEmail.setEmailBC(email_bcc);
        sentEmail.setSubject(subject);
        sentEmail.setContent(content);

        //判断是否有附件
        if(!filename.equals("")){
            sentEmail.setContainsAttachment(true);
            sentEmail.setAttachmentPath(path);
            sentEmail.setAttachmentName(filename);
            sentEmail.setAttachmentSize(upload.getFiles().getFile(0).getSize() / 1024);
        }

        //设置发送时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        sentEmail.setSendDate(df.format(new Date()));
        //开始发送邮件
        // System.out.println(email.getEmail()+":"+email.getPassword()+":"+email.getSmtpHost()+":"+email_to);

        SMTPSendEmail.setProps(email);
        if (SMTPSendEmail.send2(sentEmail)) {
            //设置邮件为已发送
            sentEmail.setSent(true);
            SendEmailDAO dao = new SendEmailDAOImpl();
            dao.add(sentEmail);
            session.setAttribute("sendEmailMess", "邮件发送成功");
            //更新session
            List<SentEmail> list = (List<SentEmail>) dao.getAllSentEmail(email);
            session.setAttribute("sentEmailList", list);
            session.setAttribute("sentEmailNumber", list.size());
        } else {
            session.setAttribute("sendEmailMess", "邮件发送失败");
        }
        session.setAttribute("active", 4);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sentEmailList.jsp");
        dispatcher.forward(request, response);
    }
}
