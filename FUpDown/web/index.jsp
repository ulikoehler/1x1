<%-- 
    Document   : index
    Created on : 17.01.2010, 19:20:55
    Author     : uli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Upload:</h1><br/>
        <form action="upload.jsp"
        enctype="multipart/form-data" method="post">
        <input type="file" name="datafile" size="40">
        <input type="submit" value="Send">
        </form><br/>
        <h1>Download:</h1><br/>
        <form action="download.jsp"
        enctype="multipart/form-data" method="get">
            Token:<input type="text" name="token" size="40">
        <input type="submit" value="Get">
        </form>
        <h1>Admin:</h1><br/>
        <form action="admin.jsp"
        enctype="multipart/form-data" method="get">
            Token:<input type="text" name="token" size="40"><br/>
            File:<input type="text" name="file" size="40"><br/>
        <input type="submit" value="Add">
        </form>
        <br/>
        <a href="log.jsp"><h2>Log</h2></a>
    </body>
</html>
