<%-- 
    Document   : signIn
    Created on : 18 mars 2018, 12:29:50
    Author     : adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <%@include  file="WEB-INF/TopMenu.jsp"%>
        <%
            if(request.getAttribute("error")!=null){
                out.println("<font color=red>"+request.getAttribute("error")+"</font>");
            }
        %>
        <h1>S'inscrire</h1>
         <form name="signin" action="/ProjetElectek-application/SignIn" method="POST">
            <p>Nom</p>
            <input type="text" name="name" />
            <p>Prenom</p>
            <input type="text" name="lastname" />
            <p>mail</p>
            <input type="email" name="mail" />
            <p>login</p>
            <input type="text" name="username" /> 
            <p>mot de passe</p>
            <input type="password" name="password" />
            <p>Verification du mot de pass</p>
            <input type="password" name="password2"/>
            
            <input class="btn btn-success" type="submit" name="S'inscire !" /> 
        </form>
    </body>
</html>
