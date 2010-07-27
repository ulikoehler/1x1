<%-- 
    Document   : upload
    Created on : 17.01.2010, 19:29:54
    Author     : uli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@page import="org.apache.commons.fileupload.FileItem" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Date" %>
<%@page import="java.io.File" %>
<%@page import="fupdown.MemoryLogProvider" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                    // Create a factory for disk-based file items
                    DiskFileItemFactory factory = new DiskFileItemFactory();

                    // Set factory constraints
                    factory.setSizeThreshold(100000000);
                    factory.setRepository(new File("/dev/shm"));

                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);

                    // Set overall request size constraint
                    upload.setSizeMax(100000000);

                    // Parse the request
                    List<FileItem> items = upload.parseRequest(request);
                    String filename = null;
                    for (FileItem item : items) //Should only be one
                    {
                        if (!item.isFormField())
                        {
                            File uploadedFile = new File(new File("/dev/shm"), item.getName());
                            filename = item.getName();
                            item.write(uploadedFile);
                        }
                    }
                    //Log a message
                    MemoryLogProvider.log(String.format("%s uploaded %s at %s", request.getRemoteAddr(),
                            filename, new Date().toString()));
        %>
        <%= "Upload was successful"%>
    </body>
</html>
