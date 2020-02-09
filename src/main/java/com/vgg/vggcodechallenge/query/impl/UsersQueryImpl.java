/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.query.impl;

import com.vgg.vggcodechallenge.entities.Users;
import com.vgg.vggcodechallenge.query.UsersQuery;
import com.vgg.vggcodechallenge.query.util.HibernateDataAccess;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

/**
 *
 * @author Administrator
 */
public class UsersQueryImpl implements UsersQuery {

    private static final Logger logger = LogManager.getLogger(UsersQueryImpl.class);

    @Override
    public boolean checkUserExists(String username) throws Exception {
        HibernateDataAccess dao = new HibernateDataAccess();
        Long count = 0L;
        try {
            dao.startOperation();
            CriteriaBuilder cb = dao.getSession().getCriteriaBuilder();
            CriteriaQuery<Long> cr = cb.createQuery(Long.class);
            System.out.println("username sent to query class:::"+username);

            Root<Users> root = cr.from(Users.class);
            cr.select(cb.count(root)).where(cb.equal(root.get("username"), username));

            Query<Long> query = dao.getSession().createQuery(cr);
            count = query.getSingleResult();

            dao.commit();
        } catch (Exception ex) {
            dao.rollback();
            logger.error("error thrown - ", ex);
            throw new Exception(ex);
        } finally {
            dao.closeSession();
        }
        return count > 0;
    }

    @Override
    public Users getUserInformation(String username) throws Exception {
        HibernateDataAccess dao = new HibernateDataAccess();
        Users user = new Users();
        try {
            dao.startOperation();
            CriteriaBuilder cb = dao.getSession().getCriteriaBuilder();
            CriteriaQuery<Users> cr = cb.createQuery(Users.class);

            Root<Users> root = cr.from(Users.class);
            cr.select(root).where(cb.equal(root.get("username"), username));

            Query<Users> query = dao.getSession().createQuery(cr);
            user = query.getSingleResult();

            dao.commit();
        } catch (Exception ex) {
            dao.rollback();
            logger.error("error thrown - ", ex);
            throw new Exception(ex);
        } finally {
            dao.closeSession();
        }
        return user;
    }

    @Override
    public List<Users> getUserInformation() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
