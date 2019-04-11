import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartRequest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;

@WebServlet("/UploadSave")
public class UploadSave extends HttpServlet {

	public UploadSave() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SmartUpload su = new SmartUpload();
		try {

			su.initialize(this.getServletConfig(), request, response);
			su.upload();
			//在upload()之后调用
            SmartRequest smartRequest = su.getRequest();
            String username = smartRequest.getParameter("username");
            String password = smartRequest.getParameter("password");
            System.out.println(username+":"+password);
			String path = "F:\\Study\\IntelliJIDEA\\WebEmail\\web\\upload";
			//String path = getServletContext().getRealPath("/") + "/upload/";
			
			su.save(path);
			System.out.println(path);
			
			String filename=su.getFiles().getFile(0).getFileName();
		   // filename = new String(filename.getBytes("GBK"),"utf-8");
            System.out.println(filename.equals(""));
			HttpSession session=request.getSession();
			session.setAttribute("message","<script>alert(\"上传文件"+filename+"成功\");" +
                    "alert("+username+":"+password+");</script>");
			response.setCharacterEncoding("utf-8");
			response.sendRedirect("/upload/uploadFile.jsp");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
