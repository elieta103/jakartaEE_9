<%@page contentType="UTF-8" import="java.util.*, org.elhg.apiservlet.webapp.jdbc.models.*" %>
<%
List<Producto> productos = (List<Producto>)request.getAttribute("productos");
Optional<String> username = (Optional<String>)request.getAttribute("username");

String mensajeRequest = (String) request.getAttribute("mensaje");
String mensajeApp = (String) getServletContext().getAttribute("mensaje");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de productos</title>
</head>
<body>
<% if(username.isPresent()){%>
<div>Hola <%=username.get()%> bienvenido !</div>
<% } %>
<h1>Listado de productos</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Tipo</th>
        <% if(username.isPresent()){%>
        <th>Precio</th>
        <th>Agregar</th>
        <%}%>
    </tr>
    <%for(Producto p : productos){%>
    <tr>
        <td><%=p.getId()%></td>
        <td><%=p.getNombre()%></td>
        <% if(username.isPresent()){%>
        <td><%=p.getTipo()%></td>
        <td><%=p.getPrecio()%></td>
        <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">Agregar al carro</a></td>
        <%}%>
    </tr>
    <%}%>
</table>
<p><%=mensajeApp%></p>
<p><%=mensajeRequest%></p>

</body>
</html>