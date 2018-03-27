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
                <div>
                    <%
                        String messagePassword = (String) request.getAttribute("messagePassword");
                        if (messagePassword != null) {
                    %>
                    <p style="color:green;"><%=messagePassword%><p>
                        <%
                            }
                            String messageMail = (String) request.getAttribute("messageMail");
                            if (messageMail != null) {
                        %>
                    <p style="color:green;"><%=messageMail%><p>
                        <%
                            }
                            Users user = (Users) request.getAttribute("user");
                            if (user != null) {%>

                    <h1>Mettre a jour vos informations</h1>

                    <form name="login" action="<%=request.getContextPath()%>/ProfileServlet" method="POST">
                        <table class="table">
                            
                            <thead>
                                    <tr>
                                        <th>Champs</th>      <th>Valeur</th>          <th>Nouvelle valeur</th>
                                    </tr>
                            </thead>
                           
                            <tr><td>Login</td>      <td><%=user.getId()%></td>          <td></td></tr>
                            <tr><td>Last Name</td>  <td><%=user.getLastName()%></td>    <td></td></tr>
                            <tr><td>First Name</td> <td><%=user.getName()%></td>        <td></td></tr>
                            <tr><td>Type</td>       <td><%=user.getType()%></td>        <td></td></tr>
                            <tr><td>Email</td>      <td><%=user.getMail()%></td>        <td><input type="email" name="mail" value=""/></td></tr>
                            <tr><td>Password</td>   <td>*******</td>                   <td><input type="password" name="password" value=""/> </td></tr>
                        </table>
                        <input class="btn btn-success" type="submit" name="Changer" /> 
                    </form>
                    <%}%>
                </div>
                
                <div>
                        <%
                        Boolean b = (Boolean)request.getAttribute("access");
                        if (b !=null && b== true) {
                        %>
                         <h2>Mettre a jour la base de données</h2>
                    <a href="<%=paths%>/LoadData">Importer les données</a>
                        <%}%>
                </div>
                
                <div>
                    <%
                        if (request.getAttribute("users") != null) {
                    %>
                     <h3>Rajouter ou Supprimer un admin</h3>
                    <table class="table">
                        <tr> <th scope=col> login </th> <th scope=col> type </th><th scope=col> Action ? </th> </tr>
                                <%
                                    List<Users> users = (List<Users>) request.getAttribute("users");
                                    for (Users u : users) {

                                %>
                        <tr> 
                            <td><%=u.getId()%></td><td><%=u.getType()%></td>
                            <td>
                                <form action="<%=request.getContextPath()%>/ProfileServlet" method="POST">
                                    <input type="hidden" value="<%=u.getId()%>" name="login">
                                    <input class="btn btn-outline-secondary" type="submit" value="Changer"/>
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                    <%
                        }
                    %>
                </div>
                <div>
                    <%@include file="../template/Footer.jsp" %>
                </div>
            </div>

        </div>
        
    </body>
</html>
