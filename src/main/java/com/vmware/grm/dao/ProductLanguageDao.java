package com.vmware.grm.dao;

import com.vmware.grm.model.Products;

import java.util.List;
import java.util.UUID;

/**
 * Author:fxing@vmware.com
 * Date:7/13/2018
 * Time:3:00 PM
 **/
public interface ProductLanguageDao {

    int insertProductLanguage(String pid,String uid);

    int deleteProductLanguage(UUID uuid);

    List<String> listLanguageNamesByProductId(String id);
}
