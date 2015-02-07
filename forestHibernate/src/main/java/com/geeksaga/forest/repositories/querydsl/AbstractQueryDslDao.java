package com.geeksaga.forest.repositories.querydsl;

import com.geeksaga.forest.entity.BaseEntity;
import com.mysema.query.types.path.EntityPathBase;
//import org.hibernate.SessionFactory;

public abstract class AbstractQueryDslDao<T extends BaseEntity, Q extends EntityPathBase<T>>
{
//    @Autowired
//    protected SessionFactory sessionFactory;
//    protected final Q q;
//    protected final Class<?> clazz;
//
//    protected AbstractQueryDslDao(Class<?> clazz, Q q)
//    {
//        this.clazz = clazz;
//        this.q = q;
//    }
//
//    protected HibernateQuery getSelectQuery()
//    {
//        return new HibernateQuery(sessionFactory.getCurrentSession()).from(q);
//    }
//
//    protected HibernateDeleteClause getDeleteQuery()
//    {
//        return new HibernateDeleteClause(sessionFactory.getCurrentSession(), q);
//    }
//
//    protected HibernateUpdateClause getUpdateQuery()
//    {
//        return new HibernateUpdateClause(sessionFactory.getCurrentSession(), q);
//    }
//
//    @Override
//    public List<T> getAll()
//    {
//        HibernateQuery query = getSelectQuery();
//        return query.list(q);
//    }
//
//    @Override
//    public void deleteAll()
//    {
//        getDeleteQuery().execute();
//    }
//
//    @Override
//    public T getById(int id)
//    {
//        return (T) sessionFactory.getCurrentSession().get(clazz, id);
//    }
//
//    @Override
//    public void add(T entity)
//    {
//        sessionFactory.getCurrentSession().saveOrUpdate(entity);
//    }
//
//    @Override
//    public void update(T entity)
//    {
//        sessionFactory.getCurrentSession().saveOrUpdate(entity);
//    }
//
//    @Override
//    public int countAll()
//    {
//        Long count = getSelectQuery().count();
//        return count.intValue();
//    }
}