<%-- 
    Document   : SearchVoteOfficeFromAdress
    Created on : 16 mars 2018, 22:04:14
    Author     : drajasin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <h1>Trouver votre bureau de vote!</h1>
        
        <%String message = (String)request.getAttribute("message");
         
            if(message!=null){
            %>
            <div style="color: red;"><%=message%></div>
            <%
            }
        
        %>
        <form method="post" action="/ProjetElectek-application/voteoffices/searchAdress">
            <fieldset>
                <legend>Adresse</legend>
                <p>Entrez votre adresse.</p>
                <label for="numero">Numéro <span class="requis">*</span></label>
                <input type="text" id="numero" name="numero" value="" size="20" maxlength="100" />
                <br />

                <label for="rue">Rue <span class="requis">*</span></label>
                <input type="text" id="rue" name="rue" value="" size="20" maxlength="300" />
                <br />

                <label for="cp">Code Postale <span class="requis">*</span></label>
                <input type="text" id="cp" name="cp" value="750" size="20" maxlength="5" />
                <br />

                <label for="pays">Pays</label>
                <input type="text" id="pays" name="pays" value="FRANCE" size="20" maxlength="20" readonly/>
                <br />

                <input type="submit" value="Trouver" class="sansLabel" />
                <br />
            </fieldset>
        </form>
    </body>
</html>
