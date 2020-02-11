/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.controller;

import com.vgg.vggcodechallenge.models.response.ResponseInformation;
import com.vgg.vggcodechallenge.models.users.ActionDTO;
import com.vgg.vggcodechallenge.models.users.UsersDTO;
import com.vgg.vggcodechallenge.service.ActionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@Api(value = "action controller API calls")
@RequestMapping("/api/actions")
public class ActionController {

    private @Autowired
    ActionService userService;

    @PostMapping("/createnewaction")
    @ApiOperation(value = "create a new project API call")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful", response = ResponseInformation.class),
        @ApiResponse(code = 400, message = "incorrect information provided", response = ResponseInformation.class),
        @ApiResponse(code = 500, message = "internal error from database or other system functions - critical!", response = ResponseInformation.class)
    })
    public ResponseEntity<?> createNewAction(@Valid @RequestBody ActionDTO userDTO, BindingResult result) throws Exception {
        return userService.createNewAction(userDTO, result);

    }

    @GetMapping("/retrieveallaction")
    @ApiOperation(value = "Retrieve all actions")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful", response = ActionDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "incorrect information provided", response = ResponseInformation.class),
        @ApiResponse(code = 500, message = "internal error from database or other system functions - critical!", response = ResponseInformation.class)
    })
    public ResponseEntity<?> retrieveAllAction() throws Exception {
        return userService.retrieveAllAction();

    }

    @GetMapping("/retrieveactionbyid")
    @ApiOperation(value = "Retrieve action by action id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful", response = ActionDTO.class),
        @ApiResponse(code = 400, message = "incorrect information provided", response = ResponseInformation.class),
        @ApiResponse(code = 500, message = "internal error from database or other system functions - critical!", response = ResponseInformation.class)
    })
    public ResponseEntity<?> retrieveActionById(@RequestParam(value = "actionid", required = true) int actionId) throws Exception {
        return userService.retrieveActionById(actionId);

    }

    @GetMapping("/retrieveactionsbyprojectid")
    @ApiOperation(value = "Retrieve actions by project id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful", response = ActionDTO.class),
        @ApiResponse(code = 400, message = "incorrect information provided", response = ResponseInformation.class),
        @ApiResponse(code = 500, message = "internal error from database or other system functions - critical!", response = ResponseInformation.class)
    })
    public ResponseEntity<?> retrieveActionsByProjectId(@RequestParam(value = "projectid", required = true) int projectId) throws Exception {
        return userService.retrieveActionsByProjectId(projectId);
    }

    @GetMapping("/retrieveactionbyidandprojectid")
    @ApiOperation(value = "Retrieve action by its id and project id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful", response = ActionDTO.class),
        @ApiResponse(code = 400, message = "incorrect information provided", response = ResponseInformation.class),
        @ApiResponse(code = 500, message = "internal error from database or other system functions - critical!", response = ResponseInformation.class)
    })
    public ResponseEntity<?> retrieveActionByIdAndProjectId(@RequestParam(value = "actionid", required = true) int actionId, @RequestParam(value = "projectid", required = true) int projectId) throws Exception {
        return userService.retrieveActionByIdAndProjectId(actionId, projectId);
    }

    @PostMapping("/updateactionforaproject")
    @ApiOperation(value = "Updating action API call")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful", response = ResponseInformation.class),
        @ApiResponse(code = 400, message = "incorrect information provided", response = ResponseInformation.class),
        @ApiResponse(code = 500, message = "internal error from database or other system functions - critical!", response = ResponseInformation.class)
    })
    public ResponseEntity<?> updateActionForAProject(ActionDTO dto, BindingResult result) throws Exception {
        return userService.updateActionForAProject(dto, result);
    }

    @PostMapping("/deleteactionforaproject")
    @ApiOperation(value = "deleting action API call")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful", response = ResponseInformation.class),
        @ApiResponse(code = 400, message = "incorrect information provided", response = ResponseInformation.class),
        @ApiResponse(code = 500, message = "internal error from database or other system functions - critical!", response = ResponseInformation.class)
    })
    public ResponseEntity<?> deleteActionForAProject(@RequestParam(value = "actionid", required = true) int actionId, @RequestParam(value = "projectid", required = true) int projectId) throws Exception {
        return userService.deleteActionForAProject(actionId, projectId);
    }
}
