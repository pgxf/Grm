package com.vmware.grm.dto;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/18/2018
 * Time:5:18 PM
 **/
public class ComponentsLanguagesResult {

    int count;
    List<ComponentsLanguages> results;

    public ComponentsLanguagesResult() {
    }

    public ComponentsLanguagesResult(int count, List<ComponentsLanguages> results) {
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ComponentsLanguages> getResults() {
        return results;
    }

    public void setResults(List<ComponentsLanguages> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ComponentsLanguagesResult{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }

}
