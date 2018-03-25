<%-- 
    Document   : TopMenu
    Created on : 24 mars 2018, 19:34:11
    Author     : alpi9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <nav class="navbar navbar-dark bg-primary">
    <a class="navbar-brand" href="/ProjetElectek-application">Accueil</a>
    <a class="navbar-brand" href="/ProjetElectek-application/Candidates">Classement</a>
    <a class="navbar-brand" href="/ProjetElectek-application/SortResults">TrisResults</a>
    <a class="navbar-brand" href="/ProjetElectek-application/MyResults">Mes Résultats</a>
    <a class="navbar-brand" href="/ProjetElectek-application/scores/presidentielle">Resultats</a>
    <a class="navbar-brand" href="/ProjetElectek-application/voteoffices">Bureaux</a>            
    <%
       if(session.getAttribute("user")!=null){
           out.println("<a class=\"navbar-brand\" href=\"/ProjetElectek-application/Logout\">Logout</a>");
       }
    %>
</nav>
</div>

