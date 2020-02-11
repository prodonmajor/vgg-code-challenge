/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.service;

import com.vgg.vggcodechallenge.entities.Actions;
import com.vgg.vggcodechallenge.entities.Projects;
import com.vgg.vggcodechallenge.models.response.ResponseInformation;
import com.vgg.vggcodechallenge.models.users.ActionDTO;
import com.vgg.vggcodechallenge.query.ActionServiceQuery;
import com.vgg.vggcodechallenge.query.DaoServiceQuery;
import com.vgg.vggcodechallenge.query.impl.ActionServiceQueryImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Administrator
 */
@Component
public class ActionService {

    private static final Logger logger = LogManager.getLogger(ActionServiceQueryImpl.class);
    private @Autowired
    DaoServiceQuery daoService;

    private @Autowired
    ActionServiceQuery actionService;

    public ResponseEntity<?> createNewAction(ActionDTO dto, BindingResult result) throws Exception {
        if (result.hasFieldErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(p -> p.getDefaultMessage()).collect(Collectors.joining("\n"));
            return ResponseEntity.badRequest().body(new ResponseInformation("Missing Information found: " + errors));
        }

        if (!daoService.checkObjectExists(Projects.class, dto.getProjectId())) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified project id does not exist!"));
        }

        Projects project = (Projects) daoService.getEntity(Projects.class, dto.getProjectId());
        Actions action_db = new Actions();

        action_db.setNote(dto.getNote());
        action_db.setDescription(dto.getDescription());
        action_db.setProjects(project);
        action_db.setId(dto.getId());

        boolean saved = daoService.saveUpdateEntity(action_db);
        if (!saved) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The database could not save record, "
                                    + "Please contact Administrator"));
        }

        return ResponseEntity.ok(new ResponseInformation("Successful"));

    }

    public ResponseEntity<?> retrieveAllAction() throws Exception {

        List<Actions> actions_db = daoService.getAllEntityRecords(Actions.class);
        List<ActionDTO> actions_out = new ArrayList<>();
        ActionDTO dto = new ActionDTO();

        for (Actions action_db : actions_db) {
            dto.setNote(action_db.getNote());
            dto.setDescription(action_db.getDescription());
            dto.setProjectId(action_db.getProjects().getId());
            dto.setId(action_db.getId());
            actions_out.add(dto);

        }
        return ResponseEntity.ok(actions_out);

    }

    public ResponseEntity<?> retrieveActionById(int actionId) throws Exception {

        if (!daoService.checkObjectExists(Actions.class, actionId)) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified action id does not exist!"));
        }

        Actions action_db = (Actions) daoService.getEntity(Actions.class, actionId);

        ActionDTO dto = new ActionDTO();
        dto.setNote(action_db.getNote());
        dto.setDescription(action_db.getDescription());
        dto.setProjectId(action_db.getProjects().getId());
        dto.setId(action_db.getId());

        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<?> retrieveActionsByProjectId(int projectId) throws Exception {

        if (!daoService.checkObjectExists(Projects.class, projectId)) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified project id does not exist!"));
        }

        Projects project = (Projects) daoService.getEntity(Projects.class, projectId);
        List<Actions> actions_db = actionService.retrieveActionsByProject(projectId);
        List<ActionDTO> actions_out = new ArrayList<>();
        ActionDTO dto = new ActionDTO();

        for (Actions action_db : actions_db) {
            dto.setNote(action_db.getNote());
            dto.setDescription(action_db.getDescription());
            dto.setProjectId(action_db.getProjects().getId());
            dto.setId(action_db.getId());
            dto.setProjectName(project.getName());
            actions_out.add(dto);

        }
        return ResponseEntity.ok(actions_out);

    }

    public ResponseEntity<?> retrieveActionByIdAndProjectId(int actionId, int projectId) throws Exception {

        if (!daoService.checkObjectExists(Actions.class, actionId)) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified action id does not exist!"));
        }

        if (!daoService.checkObjectExists(Projects.class, actionId)) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified project id does not exist!"));
        }

        Actions action_db = actionService.getSingleActionByIdAndProjectId(actionId, projectId);

        ActionDTO dto = new ActionDTO();
        dto.setNote(action_db.getNote());
        dto.setDescription(action_db.getDescription());
        dto.setProjectId(action_db.getProjects().getId());
        dto.setId(action_db.getId());

        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<?> updateActionForAProject(ActionDTO dto, BindingResult result) throws Exception {

        if (!daoService.checkObjectExists(Actions.class, dto.getId())) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified action id does not exist!"));
        }

        if (!daoService.checkObjectExists(Projects.class, dto.getProjectId())) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified project id does not exist!"));
        }

        Actions action_db = actionService.getSingleActionByIdAndProjectId(dto.getId(), dto.getProjectId());

        action_db.setNote(dto.getNote());
        action_db.setDescription(dto.getDescription());

        boolean saved = daoService.saveUpdateEntity(action_db);
        if (!saved) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The database could not save record, "
                                    + "Please contact Administrator"));
        }

        return ResponseEntity.ok(new ResponseInformation("Successful"));

    }

    public ResponseEntity<?> deleteActionForAProject(int actionId, int projectId) throws Exception {

        if (!daoService.checkObjectExists(Actions.class, actionId)) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified action id does not exist!"));
        }

        if (!daoService.checkObjectExists(Projects.class, projectId)) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified project id does not exist!"));
        }

        Actions action_db = actionService.getSingleActionByIdAndProjectId(actionId, projectId);

        boolean deleted = daoService.deleteObject(action_db);
        if (!deleted) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The database could not save record, "
                                    + "Please contact Administrator"));
        }

        return ResponseEntity.ok(new ResponseInformation("Successful"));

    }

}
