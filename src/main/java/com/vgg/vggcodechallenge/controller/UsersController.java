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
@Api(value = "vgg controller API calls")
@RequestMapping("/api/users")
public class UsersController {

    private @Autowired
    UsersService userService;

    @PostMapping("/register")
    @ApiOperation(value = "SAC User login")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful", response = ResponseInformation.class),
        @ApiResponse(code = 400, message = "incorrect information provided", response = ResponseInformation.class),
        @ApiResponse(code = 500, message = "internal error from database or other system functions - critical!", response = ResponseInformation.class)
    })
    public ResponseEntity<?> register(@Valid @RequestBody UsersDTO userDTO, BindingResult result) throws Exception {
        return userService.register(userDTO, result);
        
    }
    
    @PostMapping("/auth")
    public ResponseEntity<?> auth(@Valid @RequestBody UsersDTO userDTO, BindingResult result) throws Exception {
        return userService.createAuthenticationToken(userDTO, result);
        
    }

}
