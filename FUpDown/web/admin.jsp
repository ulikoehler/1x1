<%-- 
    Document   : admin
    Created on : 17.01.2010, 20:12:03
    Author     : uli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.File" %>
<%@page import="fupdown.TokenProvider" %>
<%@page import="fupdown.StaticIPCredentials" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                    if(!StaticIPCredentials.isAllowed(request.getRemoteAddr()))
                    {
        %>
        <%= "Remote address not 127.0.0.1 but " + request.
                                getRemoteAddr()%>
        <%      }
                            else
                            {
                                String token = request.getParameter(
                                        "token");
                                File file = new File(
                                        request.getParameter("file"));
                                TokenProvider.addToken(token, file);
        %>
        <%= "Successfully added the token"%><%
                    }
        %>
    </body>
</html>
