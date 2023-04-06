import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/login")
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



        PreparedStatement statement;
        Statement statement1;
        String admin = "admin";
        String email = request.getParameter("email");
        HttpSession httpSession = request.getSession();

        if(email.equals(admin)){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-list.jsp");
            requestDispatcher.forward(request,response);
        }else{
            httpSession.setAttribute("email",email);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile");
            requestDispatcher.forward(request,response);
        }


    }
}
