/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.config;

import com.vgg.vggcodechallenge.query.ActionServiceQuery;
import com.vgg.vggcodechallenge.query.DaoServiceQuery;
import com.vgg.vggcodechallenge.query.ProjectsQuery;
import com.vgg.vggcodechallenge.query.UsersQuery;
import com.vgg.vggcodechallenge.query.factory.ServiceQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Administrator
 */
@Configuration
public class HibernateConfig {

    @Bean
    public DaoServiceQuery getDaoServiceQuery() {
        return ServiceQueryFactory.getDaoServiceQuery();
    }

    @Bean
    public UsersQuery getUsersQuery() {
        return ServiceQueryFactory.getUsersServiceQuery();
    }

    @Bean
    public ActionServiceQuery getActionServiceQuery() {
        return ServiceQueryFactory.getActionServiceQuery();
    }

    @Bean
    public ProjectsQuery getProjectsQuery() {
        return ServiceQueryFactory.getProjectsQuery();
    }

}
