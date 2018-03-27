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
                        <a class="navbar-brand" href="<%=request.getContextPath()%>">Accueil</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="<%=request.getContextPath()%>/Candidates">Classement</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="<%=request.getContextPath()%>/SortResults">Résultats triés</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="<%=request.getContextPath()%>/SendMail">Messagerie</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="<%=request.getContextPath()%>/scores">Scores candidats</a>
                    </li>
                    <li class="nav-link">
                        <a class="navbar-brand" href="<%=request.getContextPath()%>/voteoffices">Bureaux</a>            
                    </li>
                    <%
                        if (session.getAttribute("user") != null) {
                            out.println("<li class=\"nav-link\"><a class=\"navbar-brand\" href=\""+request.getContextPath()+"/Logout\">Logout</a></li>");
                        }
                    %>
                </ul>
            </div>
        </nav>

</div>

