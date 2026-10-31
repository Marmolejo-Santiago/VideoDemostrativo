<%@ page import="org.gerdoc.service.AlumnoMateriaService" %>
<%@ page import="org.gerdoc.service.impl.AlumnoMateriaServiceImpl" %>
<%@ page import="org.gerdoc.model.AlumnoMateria" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Proyecto funcionando</h1>
    <%
        String AlumnoMateriaId = request.getParameter( "id" );

        AlumnoMateriaService AlumnoMateriaService = null;
        Long id = null;
        AlumnoMateria AlumnoMateria = null;
        if( AlumnoMateriaId != null )
        {
            id = Long.parseLong( AlumnoMateriaId );
            AlumnoMateriaService = new AlumnoMateriaServiceImpl( );
            AlumnoMateria = AlumnoMateriaService.findById( id );
        }
    %>
    <table class="table">
        <thead>
        <tr class="table-dark" align="center">
            <th scope="col" colspan="2" >Información</th>
        </tr>
        </thead>
        <%
        if( AlumnoMateria != null )
        {
        %>
            <tbody>
                <tr>
                    <td>Alumno</td>
                    <td><%= AlumnoMateria.getAlumno( ).getNombre()%></td>
                </tr>
                <tr>
                    <td>Materia</td>
                    <td><%= AlumnoMateria.getMateria( ).getNombre() %></td>
                    </tr>
            </tbody>
        <%
        }
        %>
    </table>
    <a href="AlumnoMaterias.jsp" class="btn btn-secondary">
        Regresar
    </a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>