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
        <%@include file="../template/Header.jsp" %>
    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div>
                <%                    if (request.getAttribute("error") != null) {
                        out.println("<font color=red>" + request.getAttribute("error") + "</font>");
                    }
                %>
                <h1>Recevoir les résultats par email</h1>

                <form action="/ProjetElectek-application/SendMail" method="post" >

                    Nom du candidat<br>
                    <input type="text" name="ncandidat"><br>
                    Prenom du candidat<br>
                    <input type="text" name="pcandidat"><br>
                    Election<br>
                    <input type="text" name="caption"><br>
                    Annee<br>
                    <input type="text" name="year"><br>
                    Tour<br>
                    <input type="text" name="turn"><br>
                    votre adress:<br>
                    N ° de Rue:
                    <input type="text" name="snum" ><br>
                    nom Rue : 
                    <input type="text" name="adress" ><br>
                    <input type="submit" value="Send">
                    <input type="reset" value="Reset">
                </form>

            </div>
        </div>
    </body>
    <%@include file="../template/Footer.jsp" %>
</html>
