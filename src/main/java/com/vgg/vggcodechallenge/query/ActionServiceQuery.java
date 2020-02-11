/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.query;

import com.vgg.vggcodechallenge.entities.Actions;
import java.util.List;

/**
 *Retrieves actions by a project id
 * @author Administrator
 */
public interface ActionServiceQuery {
     public List<Actions> retrieveActionsByProject(int projectId) throws Exception;
     public Actions getSingleActionByIdAndProjectId(int actionId,int projectId) throws Exception;
}
