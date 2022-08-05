package com.example.movieweb.dao;

import com.example.movieweb.utility.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AbstractDao<T> {
    public static final Session SESSION = HibernateUtil.getSession();

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {
        HibernateUtil.shutdown();
        super.finalize();
    }

    public T finById(Class<T> clazz, Integer id) {
        return SESSION.find(clazz, id);
    }

    public List<T> findByAll(Class<T> clazz, Boolean existIsActive, Integer page, Integer pageSize) {
        String entityName = clazz.getSimpleName();
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT o FROM ").append(entityName).append(" o");

        if (existIsActive != null) {
            if (existIsActive) {
                jpql.append(" WHERE active = true");
            } else {
                jpql.append(" WHERE active = false");
            }
        }

        TypedQuery<T> query = SESSION.createQuery(jpql.toString(), clazz);
        if (page != null || pageSize != null) {
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(page);
        }
        return query.getResultList();
    }

    public T findOne(Class<T> clazz, String jpql, Object... params) {
        TypedQuery<T> query = SESSION.createQuery(jpql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        List<T> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public List<T> findMany(Class<T> clazz, String jpql, Object... params) {
        TypedQuery<T> query = SESSION.createQuery(jpql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findManyByNativeQuery(Class<T> clazz, String jpql, Object... params) {
        Query query = SESSION.createQuery(jpql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }
    public T saveOrUpdate(T entity){
        try {
            SESSION.getTransaction().begin();
            SESSION.saveOrUpdate(entity);
            SESSION.getTransaction().commit();
            return entity;
        }catch (Exception e){
            SESSION.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public T delete(T entity){
        try {
            SESSION.getTransaction().begin();
            SESSION.delete(entity);
            SESSION.getTransaction().commit();
            return entity;
        }catch (Exception e){
            SESSION.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

}
