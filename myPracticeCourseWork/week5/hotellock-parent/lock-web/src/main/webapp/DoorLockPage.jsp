<%-- 
    Document   : DoorLockPage.jsp
    Created on : Oct 20, 2018, 6:34:33 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.CardKey"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.reception.HotelReceptionServiceImpl"%>
<!DOCTYPE html>
<%
    HotelReceptionService hotelReceptionService = (HotelReceptionService) session.getAttribute("hotelReceptionService");

    // If the user session has no hotelReceptionService, create a new one
    if (hotelReceptionService == null) {
        hotelReceptionService = new HotelReceptionServiceImpl();
        SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
        hotelReceptionService.setSecretKeyProvider(secretKeyProvider);
        session.setAttribute("hotelReceptionService", hotelReceptionService);
    }
    
    String roomNumber = (String) request.getParameter("roomNumber");
    String pgCardCode = (String) request.getParameter("cardCode");
    String submitAction = (String) request.getParameter("submitAction");
    String output = "";
   
    if("Unlock Door".equals(submitAction)){
        //Code here that executes when the button is pressed
        if(roomNumber == hotelReceptionService.readCard(pgCardCode).getRoomNumber()){
            output = "Door unlocked";
        }else{
            output = "Door locked";
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Door Lock Page</title>
    </head>
    <body>
        <h1>Door Lock</h1>

        <form action="./DoorLockPage.jsp">
            Enter Room Number:<br>
            <input type="text" name="roomNumber" value="">
            <br>
            Enter Card Code:<br>
            <input type="text" name="cardCode" value="">
            <br>
            <input name="submitAction" type="submit" value="Unlock Door">
        </form> 
         <br>
         <div id="result">
             <p><%=output%></p>
         </div>

    </body>
</html>
