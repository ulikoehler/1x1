<%-- 
    Document   : log
    Created on : 19.01.2010, 23:16:14
    Author     : uli
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fupdown.MemoryLogProvider" %>
<%@page import="fupdown.StaticIPCredentials" %>
<%
            response.setContentType("text/plain");
            response.reset();
            if (!StaticIPCredentials.isAllowed(request.getRemoteAddr()))
            {
%>
<%= "Remote address not 127.0.0.1 but " + request.getRemoteAddr()%>
<%      }
              else
              {
%>
<%= MemoryLogProvider.getLog()%><%
                    }%>