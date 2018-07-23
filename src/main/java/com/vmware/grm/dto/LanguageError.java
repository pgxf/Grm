package com.vmware.grm.dto;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/13/2018
 * Time:4:51 PM
 **/
public class LanguageError {

    List<String> supported_languages;

    public LanguageError(List<String> supported_languages) {
        this.supported_languages = supported_languages;
    }

    public LanguageError() {
    }

    public List<String> getSupported_languages() {
        return supported_languages;
    }


    public void setSupported_languages(List<String> supported_languages) {
        this.supported_languages = supported_languages;
    }

    @Override
    public String toString() {
        return "LanguageError{" +
                "supported_languages=" + supported_languages +
                '}';
    }
}
