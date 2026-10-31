package org.gerdoc.service.impl;

import org.gerdoc.dao.AlumnoMateriaDao;
import org.gerdoc.dao.impl.AlumnoMateriaDaoImpl;
import org.gerdoc.model.AlumnoMateria;
import org.gerdoc.service.AlumnoMateriaService;

import java.util.List;

public class AlumnoMateriaServiceImpl implements AlumnoMateriaService
{
    private AlumnoMateriaDao AlumnoMateriaDao;

    public AlumnoMateriaServiceImpl( )
    {
        this.AlumnoMateriaDao = new AlumnoMateriaDaoImpl( );
    }

    @Override
    public List<AlumnoMateria> findAll()
    {
        return AlumnoMateriaDao.findAll( );
    }

    @Override
    public AlumnoMateria findById(Long id)
    {
        return AlumnoMateriaDao.findById( id );
    }

    @Override
    public void save(AlumnoMateria AlumnoMateria)
    {
        AlumnoMateriaDao.save( AlumnoMateria );
    }

    @Override
    public void deleteById(Long id)
    {
        AlumnoMateriaDao.deleteById( id );
    }

    @Override
    public void update(AlumnoMateria AlumnoMateria)
    {
        AlumnoMateriaDao.update( AlumnoMateria );
    }
}
