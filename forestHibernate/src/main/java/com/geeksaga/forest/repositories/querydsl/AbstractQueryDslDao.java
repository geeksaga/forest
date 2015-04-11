package com.geeksaga.forest.repositories.querydsl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.geeksaga.forest.entity.BaseEntity;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.jpa.hibernate.HibernateUpdateClause;
import com.mysema.query.types.path.EntityPathBase;

public abstract class AbstractQueryDslDao<T extends BaseEntity, Q extends EntityPathBase<T>>
{
    // @Autowired
    protected SessionFactory sessionFactory;
    protected final Q q;
    protected final Class<?> clazz;

    protected AbstractQueryDslDao(Class<?> clazz, Q q)
    {
        this.clazz = clazz;
        this.q = q;
    }

    protected HibernateQuery getSelectQuery()
    {
        return new HibernateQuery(sessionFactory.getCurrentSession()).from(q);
    }

    protected HibernateDeleteClause getDeleteQuery()
    {
        return new HibernateDeleteClause(sessionFactory.getCurrentSession(), q);
    }

    protected HibernateUpdateClause getUpdateQuery()
    {
        return new HibernateUpdateClause(sessionFactory.getCurrentSession(), q);
    }

    public List<T> getAll()
    {
        HibernateQuery query = getSelectQuery();
        return query.list(q);
    }

    public void deleteAll()
    {
        getDeleteQuery().execute();
    }

    @SuppressWarnings("unchecked")
    public T getById(int id)
    {
        return (T) sessionFactory.getCurrentSession().get(clazz, id);
    }

    public void add(T entity)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public void update(T entity)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public int countAll()
    {
        Long count = getSelectQuery().count();
        return count.intValue();
    }
}