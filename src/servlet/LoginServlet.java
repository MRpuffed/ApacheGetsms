package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "��¼", urlPatterns = { "/LoginServlet" })  
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
        /* ������������Ӧ���ĵı����ʽ  */  
        request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");  
          
        // .....�ٽ������ǵ��߼����� 
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
            if (result.next()) { // �ܲ鵽���˺ţ�˵���Ѿ�ע�����  
                code = "200";  
                message = "��½�ɹ�";  
            } else {  
  
                code = "100";  
                message = "��¼ʧ�ܣ����벻ƥ����˺�δע��";  
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
        System.out.println("��֧��POST����");  
    }  
  
}  
