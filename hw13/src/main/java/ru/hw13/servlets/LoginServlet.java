package ru.hw13.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final String LOGIN_PARAMETER_NAME = "login";
    private static final String PASS_PARAMETER_NAME = "password";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String requestLogin = request.getParameter(LOGIN_PARAMETER_NAME);
        String requestPass = request.getParameter(PASS_PARAMETER_NAME);

        if (requestLogin.equals(LOGIN) && requestPass.equals(PASSWORD)) {
            request.getSession().setAttribute(LOGIN_PARAMETER_NAME, requestLogin);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect(String.format("%s/admin", request.getContextPath()));
        } else {
            response.sendRedirect(String.format("%s/", request.getContextPath()));
        }
    }
}