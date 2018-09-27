/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.sun.istack.internal.Nullable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author AG
 */
public class SignUpServlet extends HttpServlet {
    
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) //todo: remove after module 2 home work
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get public user profile
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //sign up
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType("text/html;charset=utf-8");


        if (login.equals("") || password.equals("")) {
            response.setStatus(HttpServletResponse.SC_PAYMENT_REQUIRED);
            response.getWriter().println("Логин/пароль не могут быть пустыми");
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if (profile != null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Пользователь с : " + login + " уже существует");
        }
        else {
            accountService.addNewUser(new UserProfile(login, password));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Пользователь " + login + " успешно создан");
        }
    }
    
}
