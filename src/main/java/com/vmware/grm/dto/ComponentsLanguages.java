package com.vmware.grm.dto;

import com.vmware.grm.model.Components;
import com.vmware.grm.model.Products;

import java.sql.Timestamp;
import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/18/2018
 * Time:4:52 PM
 **/
public class ComponentsLanguages {

    private String id;
    private String product_version;
    private String product_name;
    private List<String> languageName;
    private String name;
    private String branch_name;
    private String l10n_definition_file;
    private String scm_path;
    private String l10n_mode;
    private String status;
    private Timestamp modified;
    private Timestamp created;



    public ComponentsLanguages(String id, String name, String branch_name, String l10n_definition_file, String scm_path, String l10n_mode, Timestamp modified, Timestamp created, List<String> languageName, String status) {
        this.id = id;
        this.name = name;
        this.branch_name = branch_name;
        this.l10n_definition_file = l10n_definition_file;
        this.scm_path = scm_path;
        this.l10n_mode = l10n_mode;
        this.modified = modified;
        this.created = created;
        this.languageName = languageName;
        this.status = status;
    }

    public ComponentsLanguages() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getL10n_definition_file() {
        return l10n_definition_file;
    }

    public void setL10n_definition_file(String l10n_definition_file) {
        this.l10n_definition_file = l10n_definition_file;
    }

    public String getScm_path() {
        return scm_path;
    }

    public void setScm_path(String scm_path) {
        this.scm_path = scm_path;
    }

    public String getL10n_mode() {
        return l10n_mode;
    }

    public void setL10n_mode(String l10n_mode) {
        this.l10n_mode = l10n_mode;
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

    public List<String> getLanguageName() {
        return languageName;
    }

    public void setLanguageName(List<String> languageName) {
        this.languageName = languageName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct_version() {
        return product_version;
    }

    public void setProduct_version(String product_version) {
        this.product_version = product_version;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    @Override
    public String toString() {
        return "ComponentsLanguages{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", branch_name='" + branch_name + '\'' +
                ", l10n_definition_file='" + l10n_definition_file + '\'' +
                ", scm_path='" + scm_path + '\'' +
                ", l10n_mode='" + l10n_mode + '\'' +
                ", modified='" + modified + '\'' +
                ", created='" + created + '\'' +
                ", languageName=" + languageName +
                ", status='" + status + '\'' +
                ", product_version='" + product_version + '\'' +
                ", product_name='" + product_name + '\'' +
                '}';
    }

    public ComponentsLanguages(Components components, List<String> list, Products products){
        this.id = components.getId();
        this.name = components.getName();
        this.branch_name = components.getBranch_name();
        this.l10n_definition_file = components.getL10n_definition_file();
        this.scm_path = components.getScm_path();
        this.l10n_mode = components.getL10n_mode();
        this.modified = components.getModified();
        this.created = components.getCreated();
        this.languageName = list;
        this.status = components.getStatus();
        this.product_name = products.getName();
        this.product_version = products.getVersion();
    }
}
