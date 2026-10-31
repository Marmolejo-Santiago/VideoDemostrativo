package org.gerdoc.dao;

import org.gerdoc.model.AlumnoMateria;

import java.util.List;

public interface AlumnoMateriaDao
{
    List<AlumnoMateria> findAll( );
    AlumnoMateria findById(Long id);
    AlumnoMateria save(AlumnoMateria AlumnoMateria);
    void deleteById(Long id);
    AlumnoMateria update(AlumnoMateria AlumnoMateria);
}
