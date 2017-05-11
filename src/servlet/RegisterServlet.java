package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "ע��ʹ�õ�Servlet", urlPatterns = { "/RegisterServlet" })  
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
            System.out.println("���󷽷���GET");  
            doGet(request, response);  
        } else if ("POST".equals(method)) {  
            System.out.println("���󷽷���POST");  
            doPost(request, response);  
        } else {  
            System.out.println("���󷽷��ֱ�ʧ�ܣ�");  
        }  
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
            String sql = "select userAccount from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";  
            System.out.println(sql);  
            ResultSet result = statement.executeQuery(sql);  
            if (result.next()) { // �ܲ鵽���˺ţ�˵���Ѿ�ע�����  
                code = "100";  
                message = "���˺��Ѵ���";  
            } else {  
                String sqlInsert = "insert into " + DBUtil.TABLE_PASSWORD + "(userAccount, userPassword) values('"  
                        + account + "', '" + password + "')";  
                System.out.println(sqlInsert);  
                if (statement.executeUpdate(sqlInsert) > 0) { // �������ע���߼����������˺����뵽���ݿ�  
                    code = "200";  
                    message = "ע��ɹ�";  
                } else {  
                    code = "300";  
                    message = "ע��ʧ��";  
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