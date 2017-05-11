package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class GetsmsServlet
 */
@WebServlet(description = "获取银行卡邀请验证码", urlPatterns = { "/GetsmsServlet" })
public class GetsmsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/** +
     * Default constructor. 
     */  
    public GetsmsServlet() {  
        System.out.println("GetsmsServlet construct...");  
    }  
  
    @Override  
    protected void service(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        String method = request.getMethod();  
        if ("GET".equals(method)) {  
            System.out.println("请求方法：GET");  
            doGet(request, response);  
        } else if ("POST".equals(method)) {  
            System.out.println("请求方法：POST");  
            doPost(request, response);  
        } else {  
            System.out.println("请求方法分辨失败！");  
        }  
    }  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* 先设置请求、响应报文的编码格式  */  
        request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");  
          
        // .....再进行我们的逻辑处理
        String code = "";  
        String message = "";  
        
        String bankcode = request.getParameter("verifycode");
        System.out.println(bankcode);
        
        Connection connect = DBUtil.getConnect();  
        try {  
            Statement statement = connect.createStatement();  
            String sql = "select verifycode from " + DBUtil.TABLE_BANKCODE + " where verifycode='" + bankcode + "'";  
            System.out.println(sql);  
            ResultSet result = statement.executeQuery(sql);  
            if (result.next()) { // 能查到该验证码，说明验证码已经写入数据库  
                code = "100";  
                message = "该验证码已存在";  
            } else {  
                String sqlInsert = "insert into " + DBUtil.TABLE_BANKCODE + "(verifycode) values('"  
                        + bankcode  + "')";  
                System.out.println(sqlInsert);  
                if (statement.executeUpdate(sqlInsert) > 0) { // 否则进行写入逻辑，插入新验证码到数据库  
                    code = "200";  
                    message = "写入成功";  
                } else {  
                    code = "300";  
                    message = "写入失败";  
                }  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
  
        response.getWriter().append("code:").append(code).append(";message:").append(message);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
