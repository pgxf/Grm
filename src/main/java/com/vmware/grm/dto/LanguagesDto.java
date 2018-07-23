package com.vmware.grm.dto;

import com.vmware.grm.model.Languages;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/11/2018
 * Time:2:09 PM
 **/
public class LanguagesDto {
    int count;
    List<Languages> results;

    public LanguagesDto() {
    }

    public LanguagesDto(List<Languages> languages, int count) {
        this.results = languages;
        this.count = count;
    }

    public LanguagesDto(int count, List<Languages> languages) {
        this.count = count;
        this.results = languages;
    }

    public List<Languages> getLanguages() {
        return results;
    }

    public int getCount() {
        return count;
    }

    public void setLanguages(List<Languages> languages) {
        this.results = languages;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "LanguagesDto{" +
                "languages=" + results +
                ", count=" + count +
                '}';
    }
}
