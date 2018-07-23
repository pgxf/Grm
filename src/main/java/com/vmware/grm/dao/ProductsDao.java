package com.vmware.grm.dao;

import com.vmware.grm.model.Products;

import java.util.List;
import java.util.UUID;

/**
 * Author:fxing@vmware.com
 * Date:7/13/2018
 * Time:10:24 AM
 **/
public interface ProductsDao {

    int insertProduct(Products products);

    List<Products> getProductByName(String name);

    List<Products> listLanguagesByLimitOffset(int limit, int offset);

    List<Products> listLanguagesByLimitOffsetName(Integer limit, Integer offset,String name) throws Exception;

    Products getProductById(String id) throws Exception;

    int deleteProduct(UUID uuid);

    Products updateProduct(Products product);
}
