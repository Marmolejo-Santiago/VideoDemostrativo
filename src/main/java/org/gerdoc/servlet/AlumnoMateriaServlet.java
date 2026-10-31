package org.gerdoc.servlet;

import org.gerdoc.model.Materia;
import org.gerdoc.model.Alumno;
import org.gerdoc.model.AlumnoMateria;
import org.gerdoc.service.MateriaService;
import org.gerdoc.service.AlumnoMateriaService;
import org.gerdoc.service.AlumnoService;
import org.gerdoc.service.impl.MateriaServiceImpl;
import org.gerdoc.service.impl.AlumnoMateriaServiceImpl;
import org.gerdoc.service.impl.AlumnoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/AlumnoMateriaServlet")
public class AlumnoMateriaServlet extends HttpServlet
{
    @Serial
    private static final long serialVersionUID = 1L;
    private final AlumnoMateriaService AlumnoMateriaService = new AlumnoMateriaServiceImpl( );
    private final AlumnoService AlumnoService = new AlumnoServiceImpl( );
    private final MateriaService materiaService = new MateriaServiceImpl( );

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String accion = req.getParameter("accion");
        if ("delete".equals(accion))
        {
            Long id = Long.parseLong( req.getParameter("id" ) );
            AlumnoMateriaService.deleteById( id );
            resp.sendRedirect("AlumnoMaterias.jsp" );
            return;
        }
        Long AlumnoId = Long.parseLong( req.getParameter("AlumnoId" ) );
        Long materiaId = Long.parseLong( req.getParameter("materiaId" ) );
        Long id = Long.parseLong( req.getParameter("id" ) );
        Alumno Alumno = AlumnoService.findById( AlumnoId );
        Materia materia = materiaService.findById( materiaId );

        AlumnoMateria alumnoMateria = AlumnoMateria.builder( )
                .materia(materia)
                .Alumno(Alumno)
                .build();
        if( "update".equals(accion) )
        {
            alumnoMateria.setId( id );
            AlumnoMateriaService.update( alumnoMateria );
        }
        else
        {
            AlumnoMateriaService.save( alumnoMateria );
        }
        resp.sendRedirect("AlumnoMaterias.jsp");
    }
}
