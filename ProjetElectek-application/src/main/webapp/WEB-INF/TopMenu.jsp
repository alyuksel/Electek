<%-- 
    Document   : TopMenu
    Created on : 24 mars 2018, 19:34:11
    Author     : alpi9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark  bg-dark">
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-link">
                        <a class="navbar-brand" href="/ProjetElectek-application">Accueil</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="/ProjetElectek-application/Candidates">Classement</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="/ProjetElectek-application/SortResults">TrisResults</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="/ProjetElectek-application/MyResults">Mes Résultats</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="/ProjetElectek-application/scores/presidentielle">Resultats</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="/ProjetElectek-application/voteoffices">Bureaux</a>            
                    </li>
                    <%
                        if (session.getAttribute("user") != null) {
                            out.println("<li class=\"nav-link\"><a class=\"navbar-brand\" href=\"/ProjetElectek-application/Logout\">Logout</a></li>");
                        }
                    %>
                </ul>
            </div>

        </nav>
    </header>
</html>
