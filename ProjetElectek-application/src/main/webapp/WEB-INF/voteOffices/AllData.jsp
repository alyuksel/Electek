<%-- 
    Document   : FoundVoteOfficeFromAdresse
    Created on : 16 mars 2018, 00:41:37
    Author     : drajasin
--%>

<%@page import="upec.groupe1.entities.VoteOffices"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>      
            <%
            ArrayList<VoteOffices> posts = (ArrayList<VoteOffices>) request.getAttribute("ListeVoteOffices"); 
            for (VoteOffices vO: posts) {   
            %>
            <tr>
            <td><%=vO.getIdVoteOffices()%></td>
            <td><%=vO.getNumber()%></td>
            <td><%=vO.getCaption()%></td>
            <td><%=vO.getAdress()%></td>
            <td><%=vO.getPostalCode()%></td>  
            </tr>
            <%}%>
        </table>      

    </body>
</html>