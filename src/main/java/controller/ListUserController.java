package controller;

import db.DataBase;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpSession;
import model.User;
import util.HttpRequestUtils;

import java.util.Map;

public class ListUserController extends AbstractController {
    @Override
    public void doGet(HttpRequest request, HttpResponse response){

        boolean logined = isLogined(request.getSession());

        if(logined){
            StringBuilder sb = new StringBuilder();
            sb.append("<table border='1'>");
            for(User user : DataBase.findAll()){
                sb.append("<tr>");
                sb.append("<td>" + user.getUserId() + "</td>");
                sb.append("<td>" + user.getName() + "</td>");
                sb.append("<td>" + user.getEmail() + "</td>");
                sb.append("</tr>");
            }
            sb.append("</table>");
            byte[] body = sb.toString().getBytes();
            response.forwardBody(body, "html");
        }

        if(!logined) {
            response.sendRedirect("localhost:8080/user/login.html");
        }
    }

    private boolean isLogined(HttpSession session){
        Object user = session.getAttribute("user");
        if(user == null)
            return false;

        return true;
    }




}
