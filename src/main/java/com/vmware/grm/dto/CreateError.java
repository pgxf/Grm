package com.vmware.grm.dto;

/**
 * Author:fxing@vmware.com
 * Date:7/12/2018
 * Time:11:31 AM
 **/
public class CreateError {
    String name;
    String code;

    public CreateError() {
    }

    public CreateError(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public CreateError setName(String name) {
        this.name = name;
        return this;
    }

    public CreateError setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "CreateError{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
