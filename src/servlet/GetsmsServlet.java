package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class GetsmsServlet
 */
@WebServlet(description = "��ȡ���п�������֤��", urlPatterns = { "/GetsmsServlet" })
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* ������������Ӧ���ĵı����ʽ  */  
        request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");  
          
        // .....�ٽ������ǵ��߼�����
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
            if (result.next()) { // �ܲ鵽����֤�룬˵����֤���Ѿ�д�����ݿ�  
                code = "100";  
                message = "����֤���Ѵ���";  
            } else {  
                String sqlInsert = "insert into " + DBUtil.TABLE_BANKCODE + "(verifycode) values('"  
                        + bankcode  + "')";  
                System.out.println(sqlInsert);  
                if (statement.executeUpdate(sqlInsert) > 0) { // �������д���߼�����������֤�뵽���ݿ�  
                    code = "200";  
                    message = "д��ɹ�";  
                } else {  
                    code = "300";  
                    message = "д��ʧ��";  
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
