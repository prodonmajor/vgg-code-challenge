/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.query;

import com.vgg.vggcodechallenge.entities.Users;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface UsersQuery {
    /**
     * Checks if a user exists.
     * @param username the username
     * @return true, if username exists
     * @throws Exception 
     */
    public boolean checkUserExists(String username) throws Exception;
    
    /**
     * Gets the member profile for the specified username.
     * @param username the username
     * @return the member profile of the username
     * @throws Exception 
     */
    public Users getUserInformation(String username) throws Exception;
    
    /**
     * Gets all users
     * @return the member profile of the username
     * @throws Exception 
     */
    public List<Users> getUserInformation() throws Exception;
}
