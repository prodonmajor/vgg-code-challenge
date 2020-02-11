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
import com.vgg.vggcodechallenge.models.users.ProjectDTO;
import com.vgg.vggcodechallenge.query.ActionServiceQuery;
import com.vgg.vggcodechallenge.query.DaoServiceQuery;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
@Component
public class ProjectService {

    private static final Logger logger = LogManager.getLogger(ProjectService.class);
    private @Autowired
    DaoServiceQuery daoService;

    private @Autowired
    ActionServiceQuery actionService;

//    private @Autowired
//    FileStorageService fileStorageService;
//    @Value("${file.upload-dir}")
//    private String uploadPath;

//    public ResponseEntity<?> uploadProjectFile(MultipartFile file, Integer projectId) throws Exception {
//
//        if (!daoService.checkObjectExists(Projects.class, projectId)) {
//            return ResponseEntity.badRequest()
//                    .body(new ResponseInformation("The specified project id is not valid"));
//        }
//
//        Projects project = (Projects) daoService.getEntity(Projects.class, projectId);
//        String fileName = fileStorageService.storeFile(file);
//
//        project.setUserStories(uploadPath + "/" + fileName);
//
//        boolean updated = daoService.saveUpdateEntity(project);
//
//        if (!updated) {
//            return ResponseEntity.badRequest()
//                    .body(new ResponseInformation("The database could not make the necessary change. "
//                                    + "Please contact Administrator"));
//        }
//
//        return ResponseEntity.ok(new ResponseInformation("successful"));
//    }

    public ResponseEntity<?> createNewProject(ProjectDTO dto, BindingResult result) throws Exception {
        if (result.hasFieldErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(p -> p.getDefaultMessage()).collect(Collectors.joining("\n"));
            return ResponseEntity.badRequest().body(new ResponseInformation("Missing Information found: " + errors));
        }

        Projects project = new Projects();

        project.setCompleted(0);
        project.setId(dto.getId());
        project.setDescription(dto.getDescription());
        project.setName(dto.getName());
        project.setUserStories("");

        boolean saved = daoService.saveUpdateEntity(project);
        if (!saved) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The database could not save record, "
                                    + "Please contact Administrator"));
        }

        return ResponseEntity.ok(new ResponseInformation("Successful"));

    }

    public ResponseEntity<?> retrieveAllProjects() throws Exception {

        List<Projects> pr_db = daoService.getAllEntityRecords(Projects.class);
        List<ProjectDTO> projects_out = new ArrayList<>();
        ProjectDTO dto = new ProjectDTO();

        for (Projects pr : pr_db) {
            dto.setName(pr.getName());
            dto.setDescription(pr.getDescription());
            dto.setUserStories(pr.getUserStories());
            dto.setId(pr.getId());
            projects_out.add(dto);

        }
        return ResponseEntity.ok(projects_out);

    }

    public ResponseEntity<?> retrieveProjectById(int projectId) throws Exception {

        if (!daoService.checkObjectExists(Projects.class, projectId)) {
            return ResponseEntity.badRequest()
                    .body(new ResponseInformation("The specified project id does not exist!"));
        }

        Projects pr_db = (Projects) daoService.getEntity(Projects.class, projectId);

        ProjectDTO dto = new ProjectDTO();
        dto.setName(pr_db.getName());
        dto.setDescription(pr_db.getDescription());
        dto.setCompleted(pr_db.getCompleted());
        dto.setId(pr_db.getId());
        dto.setUserStories(pr_db.getUserStories());

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
}
