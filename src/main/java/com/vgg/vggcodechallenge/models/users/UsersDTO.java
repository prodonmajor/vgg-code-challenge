/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.models.users;

import javax.validation.constraints.Min;
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
public class UsersDTO {

    
    @NotNull(message = "id cannot be null")
    private Integer id;
    @NotBlank(message = "username must be provided")
    private String username;
    @NotBlank(message = "password must be provided")
    private String password;
}
