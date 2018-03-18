<%-- 
    Document   : login
    Created on : 18 mars 2018, 12:24:40
    Author     : adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
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
        <% 
            if(request.getAttribute("error")!=null){
                out.println("<font color=red>"+request.getAttribute("error")+"</font>");
            }
            if(request.getAttribute("signin")!=null){
                out.println("<font color=green>"+request.getAttribute("signin")+"</font>");
            }
        %>
        <h1>Login</h1>
        <form name="login" action="/ProjetElectek-application/Login" method="POST">
            <p>login</p>
            <input type="text" name="username" /> 
            <p>mot de passe</p>
            <input type="password" name="password" /> 
            <input class="btn btn-success" type="submit" name="Se Connecter" /> 
        </form>
        <p>Vous ne posséder pas de compte ? <a href="/ProjetElectek-application/SignIn" >Par ici ! </a>
    </body>
</html>
