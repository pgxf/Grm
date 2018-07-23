package com.vmware.grm.dao;

import com.vmware.grm.model.Releaseprofiles;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/20/2018
 * Time:1:51 PM
 **/
public interface ReleaseprofilesDao {

    Releaseprofiles getReleaseprofileById(String id) throws Exception;

    List<String> getTargetLanguagesCode(String id);

    List<String> getI18n_quality_engineers(String id);

    List<String> getI18n_engineers(String id);

    List<String> getL10n_project_managers(String id);

    List<Releaseprofiles> listLanguagesByLimitOffset(Integer limit, Integer offset);

    List<Releaseprofiles> listLanguagesByLimitOffsetName(Integer limit, Integer offset, String name) throws Exception;

    int insertI18n_quality_engineers(String id, String s);

    int insertI18n_engineers(String id, String s);

    int insertL10n_project_managers(String id, String s);

    int insertTarget_languages(String id, String language_id);

    int insertReleaseprofiles(Releaseprofiles releaseprofile);

    int deleteI18n_quality_engineers(String id);

    int deleteI18n_engineers(String id);

    int deleteL10n_project_managers(String id);

    int deleteTarget_languages(String id);

    int deleteReleaseprofile(String id);
}
