/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.query.impl;

import com.vgg.vggcodechallenge.query.ProjectsQuery;
import com.vgg.vggcodechallenge.query.util.CustomIndexerProgressMonitor;
import com.vgg.vggcodechallenge.query.util.HibernateDataAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 *
 * @author Administrator
 */
public class ProjectsQueryImpl implements ProjectsQuery {
private static final Logger logger = LogManager.getLogger(ProjectsQueryImpl.class);
    @Override
    public boolean buildDatabaseIndex() throws Exception {
        HibernateDataAccess dao = new HibernateDataAccess();
        boolean done = false;
        try {
            dao.startOperation();

            FullTextSession fullTextSession = Search.getFullTextSession(dao.getSession());
            fullTextSession
                    .createIndexer()
                    .typesToIndexInParallel(2)
                    .batchSizeToLoadObjects(2000) //sql server cannot go beyond 2100 
                    .cacheMode(CacheMode.IGNORE)
                    .threadsToLoadObjects(20)
                    .idFetchSize(150)
                    .progressMonitor(new CustomIndexerProgressMonitor())
                    .startAndWait();

            dao.commit();
            done = true;
        } catch (Exception ex) {
            dao.rollback();
            logger.error("error thrown - ", ex);
            throw new Exception(ex);
        } finally {
            dao.closeSession();
        }
        return done;
    }
    
}
