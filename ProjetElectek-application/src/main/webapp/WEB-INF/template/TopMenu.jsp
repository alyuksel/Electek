<%-- 
    Document   : TopMenu
    Created on : 24 mars 2018, 19:34:11
    Author     : alpi9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
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
                        <a class="navbar-brand" href="/ProjetElectek-application/SortResults">Résultats triés</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="/ProjetElectek-application/SendMail">Messagerie</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="/ProjetElectek-application/scores">Scores candidats</a>
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

</div>

