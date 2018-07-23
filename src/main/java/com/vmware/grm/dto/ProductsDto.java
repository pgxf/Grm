package com.vmware.grm.dto;

import com.vmware.grm.model.Products;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/16/2018
 * Time:2:55 PM
 **/
public class ProductsDto {

    int count;
    List<Products> results;

    public ProductsDto() {
    }

    public ProductsDto(int count, List<Products> results) {
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public List<Products> getResults() {
        return results;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setResults(List<Products> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ProductsDto{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
