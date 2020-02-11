/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.models.users;

import java.util.List;
import javax.validation.constraints.NotBlank;
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
public class ProjectDTO {

    @NotNull(message = "id cannot be null")
    private Integer id;
    @NotBlank(message = "name must be provided")
    private String name;
    @NotBlank(message = "description must be provided")
    private String description;
    private Integer completed;
    private String userStories;
    private List<ActionDTO> actions;
}
