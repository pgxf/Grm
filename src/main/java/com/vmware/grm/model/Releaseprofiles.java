package com.vmware.grm.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/19/2018
 * Time:5:48 PM
 **/
public class Releaseprofiles {

    String id;
    String source_language_name;
    List<String> target_languages_codes;
    String name;
    String description;
    String tms_configuration_id;
    Timestamp modified;
    Timestamp created;
    String product;
    List<String> i18n_quality_engineers;
    List<String> i18n_engineers;
    List<String> l10n_project_managers;
    String language_id;

    public Releaseprofiles(String id, String source_language_name, List<String> target_languages_codes, String name, String description, String tms_configuration_id, Timestamp modified, Timestamp created, String product, List<String> i18n_quality_engineers, List<String> i18n_engineers, List<String> l10n_project_managers, String language_id) {
        this.id = id;
        this.source_language_name = source_language_name;
        this.target_languages_codes = target_languages_codes;
        this.name = name;
        this.description = description;
        this.tms_configuration_id = tms_configuration_id;
        this.modified = modified;
        this.created = created;
        this.product = product;
        this.i18n_quality_engineers = i18n_quality_engineers;
        this.i18n_engineers = i18n_engineers;
        this.l10n_project_managers = l10n_project_managers;
        this.language_id = language_id;
    }

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public Releaseprofiles() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource_language_name() {
        return source_language_name;
    }

    public void setSource_language_name(String source_language_name) {
        this.source_language_name = source_language_name;
    }

    public List<String> getTarget_languages_codes() {
        return target_languages_codes;
    }

    public void setTarget_languages_codes(List<String> target_languages_codes) {
        this.target_languages_codes = target_languages_codes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTms_configuration_id() {
        return tms_configuration_id;
    }

    public void setTms_configuration_id(String tms_configuration_id) {
        this.tms_configuration_id = tms_configuration_id;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public List<String> getI18n_quality_engineers() {
        return i18n_quality_engineers;
    }

    public void setI18n_quality_engineers(List<String> i18n_quality_engineers) {
        this.i18n_quality_engineers = i18n_quality_engineers;
    }

    public List<String> getI18n_engineers() {
        return i18n_engineers;
    }

    public void setI18n_engineers(List<String> i18n_engineers) {
        this.i18n_engineers = i18n_engineers;
    }

    public List<String> getL10n_project_managers() {
        return l10n_project_managers;
    }

    public void setL10n_project_managers(List<String> l10n_project_managers) {
        this.l10n_project_managers = l10n_project_managers;
    }

    @Override
    public String toString() {
        return "Releaseprofiles{" +
                "id='" + id + '\'' +
                ", source_language_name='" + source_language_name + '\'' +
                ", target_languages_codes=" + target_languages_codes +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tms_configuration_id='" + tms_configuration_id + '\'' +
                ", modified='" + modified + '\'' +
                ", created='" + created + '\'' +
                ", product='" + product + '\'' +
                ", i18n_quality_engineers=" + i18n_quality_engineers +
                ", i18n_engineers=" + i18n_engineers +
                ", l10n_project_managers=" + l10n_project_managers +
                ", language_id='" + language_id + '\'' +
                '}';
    }
}
