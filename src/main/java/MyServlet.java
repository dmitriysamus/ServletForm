import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ServletOutputStream outputStream = resp.getOutputStream();
        resp.setContentType("text/html");
        outputStream.println("<form action=\"post\">");
        outputStream.println("    First name:<input type=\"text\" name=\"firstName\"/><br/>");
        outputStream.println("    Last name:<input type=\"text\" name=\"lastName\"/><br/>");
        outputStream.println("    <input type=\"submit\" value=\"Submit\"/>");
        outputStream.println("</form>");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        session.setAttribute(firstName, lastName);
        Enumeration<String> attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String element = attributeNames.nextElement();
            outputStream.println("FirstName: " + element + ", LastName: " + session.getValue(element) + "<br>");
        }
        outputStream.close();
    }
}
