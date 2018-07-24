package com.vmware.grm.service.implement;

import com.vmware.grm.dao.LanguangesDao;
import com.vmware.grm.dao.ProductsDao;
import com.vmware.grm.dao.ReleaseprofilesDao;
import com.vmware.grm.model.Languages;
import com.vmware.grm.model.Products;
import com.vmware.grm.model.Releaseprofiles;
import com.vmware.grm.service.ReleaseprofilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Author:fxing@vmware.com
 * Date:7/20/2018
 * Time:1:50 PM
 **/
@Service
public class ReleaseprofilesServiceImpl implements ReleaseprofilesService {

    @Autowired
    ReleaseprofilesDao releaseprofilesDao;

    @Autowired
    LanguangesDao languangesDao;

    @Autowired
    ProductsDao productsDao;

    @Override
    public Releaseprofiles getReleaseprofile(String id){
        Releaseprofiles releaseprofiles = null;
        try {
            releaseprofiles = releaseprofilesDao.getReleaseprofileById(id);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(releaseprofiles!=null){
            releaseprofiles.setTarget_languages_codes(releaseprofilesDao.getTargetLanguagesCode(releaseprofiles.getId()));
            releaseprofiles.setI18n_quality_engineers(releaseprofilesDao.getI18n_quality_engineers(releaseprofiles.getId()));
            releaseprofiles.setI18n_engineers(releaseprofilesDao.getI18n_engineers(releaseprofiles.getId()));
            releaseprofiles.setL10n_project_managers(releaseprofilesDao.getL10n_project_managers(releaseprofiles.getId()));
            Languages languages = null;
            try {
                languages = languangesDao.getLanguageById(UUID.fromString(releaseprofiles.getLanguage_id()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            releaseprofiles.setSource_language_name(languages.getName());
        }
        return releaseprofiles;
    }

    @Override
    public List<Releaseprofiles> listReleaseprofiles(Integer limit, Integer offset, String name) {
        List<Releaseprofiles> releaseprofiles = null;
        if(name==null){
            releaseprofiles = releaseprofilesDao.listLanguagesByLimitOffset(limit, offset);
        }else {
            try {
                releaseprofiles = releaseprofilesDao.listLanguagesByLimitOffsetName(limit,offset,name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i =0;i<releaseprofiles.size();i++){
            releaseprofiles.get(i).setTarget_languages_codes(releaseprofilesDao.getTargetLanguagesCode(releaseprofiles.get(i).getId()));
            releaseprofiles.get(i).setI18n_quality_engineers(releaseprofilesDao.getI18n_quality_engineers(releaseprofiles.get(i).getId()));
            releaseprofiles.get(i).setI18n_engineers(releaseprofilesDao.getI18n_engineers(releaseprofiles.get(i).getId()));
            releaseprofiles.get(i).setL10n_project_managers(releaseprofilesDao.getL10n_project_managers(releaseprofiles.get(i).getId()));
            Languages languages = null;
            try {
                languages = languangesDao.getLanguageById(UUID.fromString(releaseprofiles.get(i).getLanguage_id()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            releaseprofiles.get(i).setSource_language_name(languages.getName());
        }
        return releaseprofiles;
    }

    @Transactional
    @Override
    public Object insertReleaseprofile(Releaseprofiles releaseprofile) {
        UUID uuid = UUID.randomUUID();
        releaseprofile.setId(uuid.toString());

        List<String> i18n_quality_engineers = releaseprofile.getI18n_quality_engineers();
        List<String> i18n_engineers = releaseprofile.getI18n_engineers();
        List<String> l10n_project_managers = releaseprofile.getL10n_project_managers();
        List<String> target_languages_id = releaseprofile.getTarget_languages_codes();

        for (int i =0;i<i18n_quality_engineers.size();i++){
            String s = i18n_quality_engineers.get(i);
            int update = releaseprofilesDao.insertI18n_quality_engineers(releaseprofile.getId(),s);
            if (update==1) continue;
            else return "i18n_quality_engineers error";
        }
        for (int i =0;i<i18n_engineers.size();i++){
            String s = i18n_engineers.get(i);
            int update = releaseprofilesDao.insertI18n_engineers(releaseprofile.getId(),s);
            if (update==1) continue;
            else return "i18n_engineers error";
        }
        for (int i =0;i<l10n_project_managers.size();i++){
            String s = i18n_quality_engineers.get(i);
            int update = releaseprofilesDao.insertL10n_project_managers(releaseprofile.getId(),s);
            if (update==1) continue;
            else return "l10n_project_managers error";
        }

        for (int i =0;i<target_languages_id.size();i++){
            String language_id = target_languages_id.get(i);
            try {
                Languages languageById = languangesDao.getLanguageById(UUID.fromString(language_id));
            } catch (Exception e) {
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return "language_id error";
            }
            int update = releaseprofilesDao.insertTarget_languages(releaseprofile.getId(),language_id);
            if (update==1) continue;
            else return "Target_languages error";
        }

        String product_id = releaseprofile.getProduct();
        try {
            Products productById = productsDao.getProductById(product_id);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "product_id error";
        }

        String language_id = releaseprofile.getLanguage_id();
        try {
            Languages languageById = languangesDao.getLanguageById(UUID.fromString(language_id));
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "language_id error";
        }

        releaseprofile.setModified(new Timestamp(System.currentTimeMillis()));
        releaseprofile.setCreated(new Timestamp(System.currentTimeMillis()));

        int update = releaseprofilesDao.insertReleaseprofiles(releaseprofile);
        if (update==1) return getReleaseprofile(releaseprofile.getId());
        return "releaseprofile error";
    }

    @Transactional
    @Override
    public Object updateReleaseprofile(Releaseprofiles releaseprofile) {
        deleteReleaseprofile(releaseprofile.getId());
        releaseprofile.setModified(new Timestamp(System.currentTimeMillis()));
        Object o = insertReleaseprofile(releaseprofile);
        return o;
    }

    @Transactional
    @Override
    public boolean deleteReleaseprofile(String id) {
        int update1 = releaseprofilesDao.deleteI18n_quality_engineers(id);
        int update2 = releaseprofilesDao.deleteI18n_engineers(id);
        int update3 = releaseprofilesDao.deleteL10n_project_managers(id);
        int update4 = releaseprofilesDao.deleteTarget_languages(id);
        int update5 = releaseprofilesDao.deleteReleaseprofile(id);
        return update1>=0&&update2>=0&&update3>=0&&update4>=0&&update5>=0;
    }
}
