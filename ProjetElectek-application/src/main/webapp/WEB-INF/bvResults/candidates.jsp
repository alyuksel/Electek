<%-- 
    Document   : candidates
    Created on : 16 mars 2018, 10:25:36
    Author     : AMM
--%>

<%@page import="java.util.Map"%>
<%@page import="upec.groupe1.entities.Results"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choix pour le classement des candidats</title>


    </head>
    <body>
        <%@include  file="TopMenu.jsp"%>
        <div class="container">
            <h1>Choisissez les critères de recherches</h1>
            <form action="/ProjetElectek-application/resultatBV" method="POST">
                <div class="form-check">
                    <label> Elections :</label> <br>
                    <input type="radio" name="election" value="Presidentielle" checked/> Présidentielles <br>
                    <input type="radio" name="election" value="Legislatives"/> Législatives <br>
                </div>
                <div class="form-check">
                    <label> Tour : </label><br>
                    <input type="radio" name="turn" value="1" checked/>1er<br>
                    <input type="radio" name="turn" value="2"/>2ème<br>
                </div>
                <div class="form-check">
                    <label>Année : </label><br>
                    <input type="radio" name="year" value="2007" checked> 2007<br> 
                    <input type="radio" name="year" value="2012">2012<br> 
                    <input type="radio" name="year" value="2017">2017<br> 
                </div>
                <input class="btn btn-success" type="submit" value="valider" name="valide" />
            </form>
        </div>
    </body>
</html>
