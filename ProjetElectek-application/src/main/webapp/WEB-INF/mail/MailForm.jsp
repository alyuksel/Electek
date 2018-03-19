<%-- 
    Document   : MailForm
    Created on : 19 mars 2018, 06:58:57
    Author     : drajasin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2>Recevoir les résultats par email</h2>

        <form action="/ProjetElectek-application/SendMail" method="post" >

        E-mail:<br>
        <input id="emailAddress" name="emailAddress" type="email" multiple><br>
        Object:<br>
        <input type="text" name="object"><br>
        Comment:<br>
        <input type="text" name="message" size="50" style="height:200px"><br><br>
        <input type="submit" value="Send">
        <input type="reset" value="Reset">
        </form>
        
    </body>
</html>
