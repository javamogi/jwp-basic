package next.web;

import core.db.DataBase;
import next.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = DataBase.findUserById(req.getParameter("userId"));
        if(!user.getPassword().equals(req.getParameter("password"))){
            resp.sendRedirect("/user/login_failed.html");
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect("/user/list");
    }
}
