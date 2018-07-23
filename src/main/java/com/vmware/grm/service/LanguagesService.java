package com.vmware.grm.service;

import com.vmware.grm.model.Languages;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/10/2018
 * Time:2:00 PM
 **/
public interface LanguagesService {

    List<Languages> listLanguages(int limit,int offset,String name,String code);

    int countLanguages(String name, String code);

    Languages insertLanguage(Languages language);

    Languages getLanguage(String id);

    Languages getLanguageByName(String name);

    Languages getLanguageByCode(String code);

    Languages updateLanguage(Languages languages, Languages lan);

    String deleteLanguage(String id);
}
