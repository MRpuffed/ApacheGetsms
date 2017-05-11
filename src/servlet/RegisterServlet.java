package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "注册使用的Servlet", urlPatterns = { "/RegisterServlet" })  
public class RegisterServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    /** +
     * Default constructor. 
     */  
    public RegisterServlet() {  
        System.out.println("RegisterServlet construct...");  
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
            String sql = "select userAccount from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";  
            System.out.println(sql);  
            ResultSet result = statement.executeQuery(sql);  
            if (result.next()) { // 能查到该账号，说明已经注册过了  
                code = "100";  
                message = "该账号已存在";  
            } else {  
                String sqlInsert = "insert into " + DBUtil.TABLE_PASSWORD + "(userAccount, userPassword) values('"  
                        + account + "', '" + password + "')";  
                System.out.println(sqlInsert);  
                if (statement.executeUpdate(sqlInsert) > 0) { // 否则进行注册逻辑，插入新账号密码到数据库  
                    code = "200";  
                    message = "注册成功";  
                } else {  
                    code = "300";  
                    message = "注册失败";  
                }  
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
  
    }  
  
    @Override  
    public void destroy() {  
        System.out.println("RegisterServlet destory.");  
        super.destroy();  
    }  
  
}  