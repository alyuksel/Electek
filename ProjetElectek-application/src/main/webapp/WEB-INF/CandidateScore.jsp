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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
       
    </head>
    <body>
         <nav class="navbar navbar-dark bg-primary">
            <a class="navbar-brand" href="/ProjetElectek-application/index.html">Accueil</a>
            <a class="navbar-brand" href="/ProjetElectek-application/Candidates">Classement</a>
            <a class="navbar-brand" href="/ProjetElectek-application/SortResults">Resultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/MyResults">Mes Résultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/results/presidentielle">Resultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/voteoffices">Bureaux</a>
            <%
               if(session.getAttribute("user")!=null){
                   out.println("<a class=\"navbar-brand\" href=\"/ProjetElectek-application/Logout\">Logout</a>");
               }
            %>
        </nav>
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
                <option value="1">1er</option>
                <option value="2">2eme</option>
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
