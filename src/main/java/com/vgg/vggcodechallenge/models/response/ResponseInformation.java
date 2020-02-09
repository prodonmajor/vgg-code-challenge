/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.models.response;

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
public class ResponseInformation {

    private String errorcode;
    private String message;
    private String jwt;

    public ResponseInformation() {
    }

    public ResponseInformation(String message) {
        this.message = message;
    }

    public ResponseInformation(String errorcode, String message, String jwt) {
        this.errorcode = errorcode;
        this.message = message;
        this.jwt = jwt;
    }

}
