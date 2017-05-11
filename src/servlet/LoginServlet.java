package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "登录", urlPatterns = { "/LoginServlet" })  
public class LoginServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    /** 
     * @see HttpServlet#HttpServlet() 
     */  
    public LoginServlet() {  
        super();  
    }  
  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
     *      response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        /* 先设置请求、响应报文的编码格式  */  
        request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");  
          
        // .....再进行我们的逻辑处理 
        String code = "";  
        String message = "";  
  
        String account = request.getParameter("account");  
        String password = request.getParameter("password");  
        System.out.println(account + ";" + password);  
  
        Connection connect = DBUtil.getConnect();  
        try {  
            Statement statement = connect.createStatement();  
            String sql = "select userAccount from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account  
                    + "' and userPassword='" + password + "'";  
            System.out.println(sql);  
            ResultSet result = statement.executeQuery(sql);  
            if (result.next()) { // 能查到该账号，说明已经注册过了  
                code = "200";  
                message = "登陆成功";  
            } else {  
  
                code = "100";  
                message = "登录失败，密码不匹配或账号未注册";  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
  
        response.getWriter().append("code:").append(code).append(";message:").append(message);  
    }  
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse 
     *      response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        System.out.println("不支持POST方法");  
    }  
  
}  
