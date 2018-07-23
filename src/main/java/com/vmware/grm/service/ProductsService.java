package com.vmware.grm.service;

import com.vmware.grm.model.Languages;
import com.vmware.grm.model.Products;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/13/2018
 * Time:10:23 AM
 **/
public interface ProductsService {

    Object insertProduct(Products products);

    List<Products> listProducts(Integer limit, Integer offset, String name);

    int countProducts(String name);

    Products getProduct(String id);

    String deleteProduct(String id);

    Object updateProduct(Products product);
}
