package com.vmware.grm.dto;

import com.vmware.grm.model.Releaseprofiles;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/20/2018
 * Time:3:08 PM
 **/
public class ReleaseprofilesDto {

    int count;
    List<Releaseprofiles> results;

    public ReleaseprofilesDto(int count, List<Releaseprofiles> results) {
        this.count = count;
        this.results = results;
    }

    public ReleaseprofilesDto() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Releaseprofiles> getResults() {
        return results;
    }

    public void setResults(List<Releaseprofiles> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ReleaseprofilesDto{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
