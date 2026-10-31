package org.gerdoc.dao.impl;

import org.gerdoc.dao.AlumnoMateriaDao;
import org.gerdoc.model.AlumnoMateria;
import org.gerdoc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class AlumnoMateriaDaoImpl implements AlumnoMateriaDao
{
    private Session session;

    public AlumnoMateriaDaoImpl( )
    {
        session = HibernateUtil
                .getSessionFactory( )
                .openSession( );
    }

    @Override
    public List<AlumnoMateria> findAll()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<AlumnoMateria> criteria =
                builder.createQuery(AlumnoMateria.class);
        criteria.from(AlumnoMateria.class);
        return session
                .createQuery(criteria)
                .getResultList();
    }

    @Override
    public AlumnoMateria findById(Long id)
    {
        return session.get( AlumnoMateria.class, id );
    }

    @Override
    public AlumnoMateria save(AlumnoMateria AlumnoMateria)
    {
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            session.save( AlumnoMateria );
            transaction.commit( );
            return AlumnoMateria;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
            return null;
        }
    }

    @Override
    public void deleteById(Long id)
    {
        Transaction transaction = null;
        AlumnoMateria AlumnoMateria = null;
        try
        {
            AlumnoMateria = findById( id );
            if( AlumnoMateria != null )
            {
                transaction = session.beginTransaction();
                session.delete( AlumnoMateria );
                transaction.commit( );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
        }
    }

    @Override
    public AlumnoMateria update(AlumnoMateria AlumnoMateria )
    {
        Transaction transaction = null;
        AlumnoMateria updatedAlumnoMateria = null;
        try
        {
            transaction = session.beginTransaction();
            updatedAlumnoMateria = (AlumnoMateria) session.merge( AlumnoMateria );
            transaction.commit( );
            return updatedAlumnoMateria;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
            return null;
        }
    }
}
