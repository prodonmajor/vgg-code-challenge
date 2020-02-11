/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.query.impl;

import com.vgg.vggcodechallenge.entities.Actions;
import com.vgg.vggcodechallenge.entities.Projects;
import com.vgg.vggcodechallenge.query.ActionServiceQuery;
import com.vgg.vggcodechallenge.query.util.HibernateDataAccess;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

/**
 *
 * @author Administrator
 */
public class ActionServiceQueryImpl implements ActionServiceQuery {

    private static final Logger logger = LogManager.getLogger(ActionServiceQueryImpl.class);

    @Override
    public List<Actions> retrieveActionsByProject(int projectId) throws Exception {
        HibernateDataAccess dao = new HibernateDataAccess();
        List<Actions> actions = new ArrayList<>();
        try {
            dao.startOperation();
            CriteriaBuilder cb = dao.getSession().getCriteriaBuilder();
            CriteriaQuery<Actions> cr = cb.createQuery(Actions.class);

            Root<Actions> root = cr.from(Actions.class);
            Join<Actions, Projects> project_join = root.join("projects");
            cr.select(root).where(cb.equal(project_join.get("id"), projectId));

            Query<Actions> query = dao.getSession().createQuery(cr);
            actions = query.getResultList();

            dao.commit();
        } catch (Exception ex) {
            dao.rollback();
            logger.error("error thrown - ", ex);
            throw new Exception(ex);
        } finally {
            dao.closeSession();
        }
        return actions;
    }

    @Override
    public Actions getSingleActionByIdAndProjectId(int actionId, int projectId) throws Exception {
        HibernateDataAccess dao = new HibernateDataAccess();
        Actions actions = new Actions();
        try {
            dao.startOperation();
            CriteriaBuilder cb = dao.getSession().getCriteriaBuilder();
            CriteriaQuery<Actions> cr = cb.createQuery(Actions.class);

            Root<Actions> root = cr.from(Actions.class);
            Join<Actions, Projects> project_join = root.join("projects");
            cr.select(root).where(cb.equal(project_join.get("id"), projectId),
                    cb.equal(root.get("id"), actionId)
            );

            Query<Actions> query = dao.getSession().createQuery(cr);
            actions = query.getSingleResult();

            dao.commit();
        } catch (Exception ex) {
            dao.rollback();
            logger.error("error thrown - ", ex);
            throw new Exception(ex);
        } finally {
            dao.closeSession();
        }
        return actions;
    }

}
