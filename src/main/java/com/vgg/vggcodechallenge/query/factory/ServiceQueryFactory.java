/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.query.factory;

import com.vgg.vggcodechallenge.query.DaoServiceQuery;
import com.vgg.vggcodechallenge.query.UsersQuery;
import com.vgg.vggcodechallenge.query.impl.DaoServiceQueryImpl;
import com.vgg.vggcodechallenge.query.impl.UsersQueryImpl;

/**
 *
 * @author Administrator
 */
public class ServiceQueryFactory {

    public static DaoServiceQuery getDaoServiceQuery() {
        return new DaoServiceQueryImpl();
    }

    public static UsersQuery getUsersServiceQuery() {
        return new UsersQueryImpl();
    }
}
