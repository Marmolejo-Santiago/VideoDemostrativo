<%@ page import="org.gerdoc.service.AlumnoMateriaService" %>
<%@ page import="org.gerdoc.service.impl.AlumnoMateriaServiceImpl" %>
<%@ page import="org.gerdoc.model.AlumnoMateria" %>
<%@ page import="java.util.List" %>
<%@ page import="org.gerdoc.model.Alumno" %>
<%@ page import="org.gerdoc.service.AlumnoService" %>
<%@ page import="org.gerdoc.service.impl.AlumnoServiceImpl" %>
<%@ page import="org.gerdoc.service.impl.MateriaServiceImpl" %>
<%@ page import="org.gerdoc.service.MateriaService" %>
<%@ page import="org.gerdoc.model.Materia" %>
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
    <h1>AlumnoMaterias</h1>
    <%
        AlumnoMateriaService AlumnoMateriaService = new AlumnoMateriaServiceImpl( );
        List<AlumnoMateria> AlumnoMaterias = AlumnoMateriaService.findAll( );
        AlumnoService AlumnoService = new AlumnoServiceImpl( );
        List<Alumno> Alumnoes = AlumnoService.findAll( );

        MateriaService materiaService = new MateriaServiceImpl( );
        List<Materia> materias = materiaService.findAll( );
    %>
    <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#AlumnoMateriaModal" onclick="nuevoAlumnoMateria()" >
        Agregar AlumnoMateria
    </button>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Alumno</th>
            <th scope="col">Materia</th>
            <th scope="col">Acción</th>
        </tr>
        </thead>
        <%
            int i = 0;
            for (AlumnoMateria AlumnoMateria : AlumnoMaterias)
            {
        %>
                <tbody>
                    <tr>
                        <th scope="row"><%=++i%></th>
                        <td>
                            <a href="AlumnoMateria.jsp?id=<%= AlumnoMateria.getId( ) %>" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                                <%= AlumnoMateria.getAlumno( ).getNombre() %>
                            </a>
                        </td>
                        <td><%= AlumnoMateria.getMateria( ).getNombre()%></td>
                        <td>
                            <button type="button" class="btn btn-warning btn-sm btn-editar" data-bs-toggle="modal" data-bs-target="#AlumnoMateriaModal"
                                    data-id="<%= AlumnoMateria.getId( ) %>" data-AlumnoId="<%= AlumnoMateria.getAlumno( ).getId() %>" data-materiaId="<%= AlumnoMateria.getMateria( ).getId( ) %>"
                                    >
                                Editar
                            </button>
                            <form action="AlumnoMateriaServlet" method="post" style="display:inline;">
                                <input type="hidden" name="accion" value="delete">
                                <input type="hidden" name="id" value="<%= AlumnoMateria.getId( ) %>">
                                <button type="submit" class="btn btn-danger btn-sm"
                                        onclick="return confirm('¿Seguro que deseas borrar este AlumnoMateria?');">
                                    Borrar
                                </button>
                            </form>
                        </td>
                    </tr>
                </tbody>
        <%
            }
        %>
    </table>
    <a href="index.jsp" class="btn btn-secondary">Home</a>
</div>
<div class="modal fade" id="AlumnoMateriaModal" tabindex="-1" aria-labelledby="AlumnoMateriaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="AlumnoMateriaModalLabel">Formulario de Alumno Materia</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>

            <form action="AlumnoMateriaServlet" method="post" id="formAlumnoMateria">
                <input type="hidden" id="accion" name="accion" value="save" />
                <input type="hidden" id="id" name="id" value="">
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="AlumnoId" class="form-label">Alumno</label>
                        <select class="form-select" id="AlumnoId" name="AlumnoId" required>
                            <option value="">Selecciona un Alumno</option>
                            <%
                                for (Alumno Alumno : Alumnoes)
                                {
                            %>
                            <option value="<%= Alumno.getId( ) %>">
                                <%= Alumno.getNombre( ) %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="materiaId" class="form-label">Materia</label>
                        <select class="form-select" id="materiaId" name="materiaId" required>
                            <option value="">Selecciona una materia</option>
                            <%
                                for (Materia materia : materias)
                                {
                            %>
                            <option value="<%= materia.getId( ) %>">
                                <%= materia.getNombre( ) %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Cerrar
                    </button>
                    <button type="submit" class="btn btn-primary" id="btnGuardar">
                        Guardar Alumno Materia
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

<script>
    function nuevoAlumnoMateria()
    {
        document.getElementById("formAlumnoMateria").reset();
        document.getElementById("id").value = "0";
        document.getElementById("accion").value = "save";
        document.getElementById("AlumnoMateriaModalLabel").textContent = "Agregar Alumno Materia";
        document.getElementById("btnGuardar").textContent = "Guardar Alumno Materia";
    }
    const modalAlumnoMateria = document.getElementById("AlumnoMateriaModal");
    modalAlumnoMateria.addEventListener("show.bs.modal",
        function(event)
        {
            const boton = event.relatedTarget;
            if (!boton || !boton.classList.contains("btn-editar"))
            {
                return;
            }
            const id = boton.getAttribute( "data-id" );
            const AlumnoId = boton.getAttribute( "data-AlumnoId" );
            const materiaId = boton.getAttribute( "data-materiaId" );
            document.getElementById( "id" ).value = id;
            document.getElementById( "AlumnoId" ).value = AlumnoId;
            document.getElementById( "materiaId" ).value = materiaId;
            document.getElementById( "accion" ).value = "update";
            document.getElementById( "AlumnoMateriaModalLabel" ).textContent = "Editar Alumno Materia";
            document.getElementById( "btnGuardar" ).textContent = "Actualizar Alumno Materia";
        }
    );
</script>
</body>
</html>