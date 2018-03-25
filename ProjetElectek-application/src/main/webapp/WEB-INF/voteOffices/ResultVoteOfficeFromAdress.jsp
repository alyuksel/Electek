<%--
    Document   : ResultVoteOfficeFromAdress
    Created on : 17 mars 2018, 00:24:31
    Author     : drajasin
--%>

<%@page import="upec.groupe1.entities.VoteOffices"%>
<%@page import="upec.groupe1.entities.AttachedZone"%>
<%@page import="upec.groupe1.entities.Adresse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Votre Bureau de vote</title>
        <%@include file="../template/Header.jsp" %>
        <style>
            #map {
                height: 400px;
                width: 70%;
            }
        </style>
    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div>
                <div class="container">
                    <%                    Adresse posts = (Adresse) request.getAttribute("adresse");
                    %>
                    <h1 >Bureau de vote pour votre adresse:</h1>
                    <h2><span >Adresse:</span></h2>
                    <table>
                        <tbody>
                            <tr>
                                <td ><strong><span>Numéro :</span></strong></td>
                                <td ><%=posts.getStreetNum()%></td>
                            </tr>
                            <tr>
                                <td >Rue :</span></strong></td>
                                <td ><%=posts.getStreetName()%></td>
                            </tr>
                            <tr>
                                <td >Arrondissement :</span></strong></td>
                                <td ><%=posts.getArr()%></td>
                            </tr>
                            <tr>
                                <td >Pays :</span></strong></td>
                                <td >FRANCE</td>
                            </tr>
                        </tbody>
                    </table>
                    <h2 >Bureau de vote :</h2>
                    <table>
                        <thead>
                            <tr>
                                <td>Id Bureau</td>
                                <td>Bureau de vote</td>
                                <td>Libélé</td>
                                <td>Adresse</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <%  AttachedZone zone = (AttachedZone) posts.getAttachedZone();
                            VoteOffices vo = (VoteOffices) zone.getVoteOffice();%>
                                <td><%=vo.getIdVoteOffices()%></td>
                                <td><%=vo.getNumber()%></td>
                                <td><%=vo.getCaption()%></td>
                                <td><%=vo.getAdress()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div>
                    <div id="map"></div>
                </div>
                <script>
                    function initMap() {
                        var depart = {lat: <%=posts.getGeoPoint().getY()%>, lng:<%=posts.getGeoPoint().getX()%>};
                        var arrive = {lat: <%=vo.getGeoPoint().getY()%>, lng: <%=vo.getGeoPoint().getX()%>};

                        var map = new google.maps.Map(document.getElementById('map'), {
                            center: depart,
                            zoom: 7
                        });

                        var directionsDisplay = new google.maps.DirectionsRenderer({
                            map: map
                        });

                        // Set destination, origin and travel mode.
                        var request = {
                            destination: arrive,
                            origin: depart,
                            travelMode: 'WALKING'
                        };

                        // Pass the directions request to the directions service.
                        var directionsService = new google.maps.DirectionsService();
                        directionsService.route(request, function (response, status) {
                            if (status == 'OK') {
                                // Display the route on the map.
                                directionsDisplay.setDirections(response);
                            }
                        });
                    }

                </script>
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZ86xz8TOlbA3hYecJD9OgzrhCY3qGUxw&callback=initMap"
                        async defer>
                </script>
            </div>
        </div>
    </body>
    <%@include file="../template/Footer.jsp" %>
</html>
