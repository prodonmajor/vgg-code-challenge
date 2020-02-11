/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.models.users;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
@ToString
public class ActionDTO {
    @NotNull(message = "id cannot be null")
    private Integer id;
    @NotNull(message = "project id cannot be null")
    private Integer projectId;
    @NotNull(message = "action description cannot be null")
    private String description;
    @NotNull(message = "action note cannot be null")
    private String note;
    private String projectName;
}
