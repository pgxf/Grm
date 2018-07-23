package com.vmware.grm.service.implement;

import com.vmware.grm.dao.ComponentsDao;
import com.vmware.grm.dao.ProductLanguageDao;
import com.vmware.grm.model.Components;
import com.vmware.grm.service.ComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Author:fxing@vmware.com
 * Date:7/18/2018
 * Time:11:30 AM
 **/
@Service
public class ComponentsServiceImpl implements ComponentsService {

    @Autowired
    private ComponentsDao componentsDao;

    @Autowired
    private ProductLanguageDao productLanguageDao;

    @Override
    public Components getComponents(String id) {
        Components component = null;
        try {
            component = componentsDao.getComponentByID(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return component;
    }

    @Override
    public List<Components> listComponents(Integer limit, Integer offset) {
        return componentsDao.listComponentsByLimitOffset(limit, offset);
    }

    @Override
    public List<String> listLanguageNamesById(String id) {
        return productLanguageDao.listLanguageNamesByProductId(id);
    }

    @Override
    public Components insertLanguage(Components components) {
        components.setId(UUID.randomUUID().toString());
        components.setModified(new Timestamp(System.currentTimeMillis()).toString());
        components.setCreated(new Timestamp(System.currentTimeMillis()).toString());
        int update = 0;
        try {
            update = componentsDao.insertComponent(components);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return components;
    }

    @Override
    public int updateComponent(Components components) {
        int update = componentsDao.updateComponent(components);
        return update;
    }

    @Override
    public int deleteComponent(String id) {
        return componentsDao.deleteComponent(id);
    }
}
