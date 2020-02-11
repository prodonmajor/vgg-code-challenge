/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.service;

import com.vgg.vggcodechallenge.entities.Users;
import com.vgg.vggcodechallenge.modelmapping.Mapper;
import com.vgg.vggcodechallenge.models.response.ResponseInformation;
import com.vgg.vggcodechallenge.models.users.UsersDTO;
import com.vgg.vggcodechallenge.query.DaoServiceQuery;
import com.vgg.vggcodechallenge.query.UsersQuery;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Administrator
 */
@Component
public class UsersService {

    private @Autowired
    PasswordEncoder encoder;
    private @Autowired
    UsersQuery usersQuery;
    private @Autowired
    Mapper modelMapping;
    private @Autowired
    DaoServiceQuery daoService;
//    @Autowired
//    AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(UsersDTO dto, BindingResult result) throws Exception {

        if (result.hasFieldErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(p -> p.getDefaultMessage()).collect(Collectors.joining("\n"));
            return ResponseEntity.badRequest().body(new ResponseInformation("Information missing from DTO: " + errors));
        }

        if (usersQuery.checkUserExists(dto.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The username already exist!"));
        }

        Users user = modelMapping.dtoToUsers(dto);

        char[] charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random random = new SecureRandom();
        char[] result_ = new char[8];
        for (int i = 0; i < result_.length; i++) {
            int randomCharIndex = random.nextInt(charset.length);
            result_[i] = charset[randomCharIndex];
        }
        String randomPassword = new String(result_);
        String hashedRandomPassword = encoder.encode(randomPassword);

        user.setPassword(hashedRandomPassword);

        boolean saved = daoService.saveUpdateEntity(user);
        if (!saved) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The database could not save record, "
                                    + "Please contact Administrator"));
        }

        return ResponseEntity.ok(new ResponseInformation("Successful"));
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            if (!usersQuery.checkUserExists(username)) {
//                throw new UsernameNotFoundException("User not found with username: " + username);
//            }
//            System.out.println("user found:::");
//            Users user = usersQuery.getUserInformation(username);
//            System.out.println("my user name::"+user.getUsername()+", password:::"+user.getPassword());
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                    new ArrayList<>());
//        } catch (Exception ex) {
//            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//
//    }

    public ResponseEntity<?> createAuthenticationToken(UsersDTO authenticationRequest, BindingResult result) throws Exception {

//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//        final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());
//
//        final String jwt = util.generateToken(userDetails);

        return ResponseEntity.ok("");
    }

//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }
}
