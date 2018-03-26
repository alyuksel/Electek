<%-- 
    Document   : profile
    Created on : 26 mars 2018, 20:30:27
    Author     : adam
--%>

<%@page import="upec.groupe1.entities.Users"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
        <%@include file="../template/Header.jsp" %>
    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div class="container">
                
                <%
                    if(request.getAttribute("users")!=null){
                %>
                    <table class="table">
                    <tr> <th scope=col> login </th> <th scope=col> type </th><th scope=col> Action ? </th> </tr>
                <%
                    List<Users> users =(List<Users>) request.getAttribute("users");
                    for (Users u : users){
                        
                %>
                    <tr> <td><%=u.getId()%></td><td><%=u.getType()%></td><td><form action="/ProjetElectek-application/ProfileServlet" method="POST">
                                <input type="hidden" value="<%=u.getId()%>" name="login">
                                        <input class="btn btn-outline-secondary" type="submit" value="détail" name="detail" />
                                        </form></td></tr>
                    

                <%
                    }}
                %>
                </table>
                <form name="login" action="/ProjetElectek-application/ProfileServlet" method="POST">
                    <p>mot de passe</p>
                    <input type="password" name="password" /> 
                    <input class="btn btn-success" type="submit" name="Changer" /> 
                </form>
                <form name="mail" action="/ProjetElectek-application/ProfileServlet" method="POST">
                    <p>mot de passe</p>
                    <input type="email" name="mail" /> 
                    <input class="btn btn-success" type="submit" name="Changer" /> 
                </form>
               
            </div>

        </div>
        <div>
            <%@include file="../template/Footer.jsp" %>
        </div>
    </body>
</html>
