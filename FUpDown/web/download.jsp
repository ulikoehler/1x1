<%-- 
    Document   : download
    Created on : 17.01.2010, 19:52:40
    Author     : uli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fupdown.TokenProvider" %>
<%@page import="fupdown.StaticIPCredentials" %>
<%@page import="java.io.File" %>
<%@page import="java.util.Date" %>
<%@page import="java.io.BufferedInputStream" %>
<%@page import="java.io.FileInputStream" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                    File f =
                         TokenProvider.getFile(request.getParameter("token"));
                    if (f != null)
                    {
                        response.reset();
                        response.setContentType("application/octet-stream");
                        response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
                        BufferedInputStream fin = new BufferedInputStream(new FileInputStream(
                                f));
                        ServletOutputStream op = response.getOutputStream();

                        byte[] buffer = new byte[4096];
                        while (fin.available() > 0)
                        {
                            int read = fin.read(buffer);
                            op.write(buffer, 0, read);
                        }
                        fin.close();
                        response.getOutputStream().flush();

                    //Log a message
                    MemoryLogProvider.log(String.format("%s uploaded %f at %s", request.getRemoteAddr(),
                            filename, new Date().toString()));
                    }
                    else
                    {
        %><%= "Token not in database!"%><%
                    }
        %>
    </body>
</html>
