<%-- 
    Document   : VoteOfficeDetail
    Created on : 25 mars 2018, 13:40:43
    Author     : drajasin
--%>

<%@page import="upec.groupe1.entities.VoteOffices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            #map {
             height: 400px;
             width: 100%;
            }
        </style>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bureau de Vote en détail</title>
    </head>
    <body>
        <%@include  file="../TopMenu.jsp"%>
        <div class="container">
            <%
                VoteOffices vO = (VoteOffices) request.getAttribute("VoteOffices");
                String message = (String) request.getAttribute("message");
                if(message!=null || vO == null){
                    %>
                    <h1>Bureaux de votes !</h1>
                    <p>Mauvais Filtrage : <%=message%></p>
                    <%
                }else{
                    %>
                    <h1>Bureaux de votes : <%=vO.getNumber()%></h1>
                        <div>
                            <table> 
                            <thead>
                                <tr>
                                    <th>Numéro</th>
                                    <th>Libélé</th>
                                    <th>Adresse</th>
                                    <th>Code Postale</th>
                                </tr>
                            </thead>
                            <tr>
                                <td><%=vO.getNumber()%></td>
                                <td><%=vO.getCaption()%></td>
                                <td><%=vO.getAdress()%></td>
                                <td><%=vO.getPostalCode()%></td>  
                            </tr>
                            </table>
                        </div>
                        <div>
                            <div id="map"></div>
                        </div>
                    <%
                }
            %>
        </div>
    </body>
    <% if(vO!=null){%>
        <script>
        function initMap() {
          var uluru = {lat:<%=vO.getGeoPoint().getY()%>, lng: <%=vO.getGeoPoint().getX()%>};
          var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 15,
            center: uluru
          });
          var marker = new google.maps.Marker({
            position: uluru,
            map: map
          });
        }
      </script>
      <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZ86xz8TOlbA3hYecJD9OgzrhCY3qGUxw&callback=initMap">
      </script>
    
    <%
    }
    %>
</html>

