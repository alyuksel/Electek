<%-- 
    Document   : Results
    Created on : 15 mars 2018, 23:41:07
    Author     : alpi
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>
        <p>Ceci est une page générée depuis une JSP.</p>
        <p>
            
            <%
            ArrayList<String> posts = (ArrayList<String>) request.getAttribute("test"); 
            for (String str: posts) {   
            %>
            <tr>
              <td><%=str%></td>
              ....
             </tr>
            <%}%>
        </p>
    </body>
</html>
