package com.vmware.grm.service.implement;

import com.vmware.grm.dao.LanguangesDao;
import com.vmware.grm.model.Languages;
import com.vmware.grm.service.LanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Author:fxing@vmware.com
 * Date:7/10/2018
 * Time:2:11 PM
 **/
@Service
public class LanguagesServiceImpl implements LanguagesService {

    @Autowired
    private LanguangesDao languangesDao;

    @Override
    public List<Languages> listLanguages(int limit, int offset,String name,String code) {
        if(name==null&&code==null)
            return languangesDao.listLanguagesByLimitOffset(limit,offset);
        if(name!=null&&code!=null)
            return languangesDao.listLanguagesByLimitOffsetNameCode(limit,offset,name,code);
        if(name==null)
            return languangesDao.listLanguagesByLimitOffsetCode(limit,offset,code);
        if(code==null)
            return languangesDao.listLanguagesByLimitOffsetName(limit,offset,name);
        return null;
    }

    @Override
    public int countLanguages(String name, String code) {
        if(name==null&&code==null)
            return languangesDao.countLanguages();
        return 1;
    }

    @Override
    public Languages insertLanguage(Languages language) {
        int update = 0;
        language.setId((UUID.randomUUID().toString()));
        try {
            update = languangesDao.insertLanguage(language);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        } catch (Exception e){

        }
        if(update==1)
            return language;
        return null;
    }

    @Override
    public Languages getLanguage(String id) {
        Languages language = null;
        try {
            language = languangesDao.getLanguageById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return language;

    }

    @Override
    public Languages getLanguageByName(String name) {
        Languages language = null;
        try {
            language = languangesDao.getLanguageByName(name);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return language;
    }

    @Override
    public Languages getLanguageByCode(String code) {
        Languages language = null;
        try {
            language = languangesDao.getLanguageByCode(code);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return language;
    }

    @Override
    public Languages updateLanguage(Languages languages, Languages lan) {
        return languages.getDescription()==null&&languages.getCode_aliases()==null?lan:languangesDao.updateLanguage(languages);
    }

    @Override
    public String deleteLanguage(String id) {
        int update = languangesDao.deleteLanguage(UUID.fromString(id));
        return update==1?"success":"error";
    }

}
