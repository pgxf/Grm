package com.vmware.grm.dao;

import com.vmware.grm.model.Languages;

import java.util.List;
import java.util.UUID;


public interface LanguangesDao {

    List<Languages> listLanguagesByLimitOffsetNameCode(int limit, int offset,String name,String code);

    List<Languages> listLanguagesByLimitOffsetName(int limit, int offset,String name);

    List<Languages> listLanguagesByLimitOffsetCode(int limit, int offset,String code);

    List<Languages> listLanguagesByLimitOffset(int limit, int offset);

    Languages getLanguageByName(String name) throws Exception;

    Languages getLanguageByCode(String code) throws Exception;

    Languages getLanguageById(UUID uuid) throws Exception;

    Languages getLanguageByNameCode(Languages languages);

    int insertLanguage(Languages languages) throws Exception;

    Languages updateLanguage( Languages languages);

    int deleteLanguage(UUID uuid);

    int countLanguages();
}
