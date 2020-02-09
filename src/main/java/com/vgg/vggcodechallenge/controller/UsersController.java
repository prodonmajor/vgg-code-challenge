/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.controller;

import com.vgg.vggcodechallenge.models.response.ResponseInformation;
import com.vgg.vggcodechallenge.models.users.UsersDTO;
import com.vgg.vggcodechallenge.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/users")
@Api(value = "Account controller API calls")
public class UsersController {

    private @Autowired
    UsersService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UsersDTO userDTO, BindingResult result) throws Exception {
        return userService.register(userDTO, result);
        
    }
    
    @PostMapping("/auth")
    public ResponseEntity<?> auth(@Valid @RequestBody UsersDTO userDTO, BindingResult result) throws Exception {
        return userService.createAuthenticationToken(userDTO, result);
        
    }

}
