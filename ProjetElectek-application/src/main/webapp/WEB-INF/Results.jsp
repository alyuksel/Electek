<%-- 
    Document   : Results
    Created on : 15 mars 2018, 23:41:07
    Author     : alpi
--%>

<%@page import="java.util.stream.Collectors"%>
<%@page import="upec.groupe1.dto.Score"%>
<%@page import="upec.groupe1.dto.Candidate"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
            String path = (String)(request.getContextPath()+request.getAttribute("path"));
        %>
        <%=path%>
        <form action="<%=path%>" method="POST">
            <select name="fullName">
                <%
                List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates");
                for (Candidate cand : candidates)
                {
                        String item = (String) cand.getFullName();
                %>
                   <option value="<%=item%>"><%=item%></option>
                <%
                }
                %>
            </select>
            <select name="turn">
                <option value="1er">1er</option>
                <option value="2eme">2eme</option>
            </select>
            <select name="place">
                <option>global</option>
            </select>
            <input type="submit" value="Submit" />
        </form>
            <%
                if((Boolean)request.getAttribute("isScore") == true){
            %>        
                 <p>
                Score du candidat :<br>
                <%
                    Score score = (Score) request.getAttribute("score");
                    String total = ""+score.getVoteNumber();
                    String percent = ""+score.getPercent();
                %>
                <%=total%><br>
                <%=percent%>
                </p>
            <%}%>
            
        </p>
    </body>
</html>
