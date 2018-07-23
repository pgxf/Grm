package com.vmware.grm.service;

import com.vmware.grm.model.Components;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/18/2018
 * Time:10:27 AM
 **/
public interface ComponentsService {

    Components getComponents(String id);

    List<Components> listComponents(Integer limit, Integer offset);

    List<String> listLanguageNamesById(String id);

    Components insertLanguage(Components components);

    int updateComponent(Components components);

    int deleteComponent(String id);
}
