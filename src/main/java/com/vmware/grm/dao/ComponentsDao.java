package com.vmware.grm.dao;

import com.vmware.grm.model.Components;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/18/2018
 * Time:11:32 AM
 **/
public interface ComponentsDao {

    Components getComponentByID(String id) throws Exception;

    List<Components> listComponentsByLimitOffset(Integer limit, Integer offset);

    int insertComponent(Components components) throws Exception;

    int updateComponent(Components components);

    int deleteComponent(String id);
}
