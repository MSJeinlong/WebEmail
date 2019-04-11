package Controller.ReceivedEmail;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //特别注意：------DownloadServlet不用设置跳转，设置跳转反而会出错
        String filePath = request.getParameter("filePath");
        SmartUpload su = new SmartUpload();
        //System.out.println("Download:"+filePath);
        su.initialize(getServletConfig(), request, response);
        // 设置响应类型
        su.setContentDisposition(null);

        try {
            //开始下载附件
            su.downloadFile(filePath);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        //特别注意：------DownloadServlet不用设置跳转，设置跳转反而会出错
    }
}
