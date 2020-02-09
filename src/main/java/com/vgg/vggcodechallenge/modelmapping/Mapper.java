/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.modelmapping;

import com.vgg.vggcodechallenge.entities.Users;
import com.vgg.vggcodechallenge.models.users.UsersDTO;
import com.vgg.vggcodechallenge.query.factory.ServiceQueryFactory;

/**
 *
 * @author Administrator
 */
public class Mapper {

    public Users dtoToUsers(UsersDTO dto) throws Exception {
        Users user = new Users();
        if (dto != null) {
            if (dto.getId() > 0 && dto.getId() > 0 && ServiceQueryFactory.getDaoServiceQuery().
                    checkObjectExists(Users.class, dto.getId())) {
                user = (Users) ServiceQueryFactory.getDaoServiceQuery().getEntity(Users.class, dto.getId());
            }
            user.setId(dto.getId());
            user.setUsername(dto.getUsername());
        }
        return user;
    }
}
