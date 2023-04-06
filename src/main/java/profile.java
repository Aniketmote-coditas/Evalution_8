import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/profile")
public class profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession httpSession = req.getSession();
        PrintWriter out = resp.getWriter();
        String str = (String)httpSession.getAttribute("email");

        String s = (String)httpSession.getAttribute("email");
        if(httpSession.getAttribute("email")!=null) {

            out.println(" <a href=\"logout\">logout</a>");
        }else {
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "1122");

            Statement statement = con.createStatement();
            String query = "select * from users where email='"+str+"'";
            ResultSet rs = statement.executeQuery(query);

            out.println("<table border=2px> <tr>" +
                    "<th>Id</th>" +
                    "<th>Name</th>" +
                    "<th>Email</th>" +
                    "<th>Country</th>");
            while (rs.next()){
                out.println("<tr>");
                out.println("<td>");
                out.println("<h1> " +rs.getInt(1)+"</h1>");
                out.println("</td><td>");
                out.println("<h1> " +rs.getString(2)+"</h1>");
                out.println("</td><td>");
                out.println("<h1> " +rs.getString(3)+"</h1>");
                out.println("</td><td>");
                out.println("<h1> " +rs.getString(4)+"</h1>");
                out.println("</td></tr>");
            }
            out.println("</table>");

        } catch (SQLException | ClassNotFoundException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
    }
}
