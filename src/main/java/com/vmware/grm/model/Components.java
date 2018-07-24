package com.vmware.grm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Author:fxing@vmware.com
 * Date:7/18/2018
 * Time:10:38 AM
 **/
public class Components {

    private String id;
    private String name;
    private String branch_name;
    private String l10n_definition_file;
    private String scm_path;
    private String l10n_mode;
    private Timestamp modified;
    private Timestamp created;

    private String product_id;
    private String status;

    public Components(String id, String name, String branch_name, String l10n_definition_file, String scm_path, String l10n_mode, Timestamp modified, Timestamp created, String product_id, String status) {
        this.id = id;
        this.name = name;
        this.branch_name = branch_name;
        this.l10n_definition_file = l10n_definition_file;
        this.scm_path = scm_path;
        this.l10n_mode = l10n_mode;
        this.modified = modified;
        this.created = created;
        this.product_id = product_id;
        this.status = status;
    }

    public Components() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public String getL10n_definition_file() {
        return l10n_definition_file;
    }

    public String getScm_path() {
        return scm_path;
    }

    public String getL10n_mode() {
        return l10n_mode;
    }

    public Timestamp getModified() {
        return modified;
    }

    public Timestamp getCreated() {
        return created;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public void setL10n_definition_file(String l10n_definition_file) {
        this.l10n_definition_file = l10n_definition_file;
    }

    public void setScm_path(String scm_path) {
        this.scm_path = scm_path;
    }

    public void setL10n_mode(String l10n_mode) {
        this.l10n_mode = l10n_mode;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Components{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", branch_name='" + branch_name + '\'' +
                ", l10n_definition_file='" + l10n_definition_file + '\'' +
                ", scm_path='" + scm_path + '\'' +
                ", l10n_mode='" + l10n_mode + '\'' +
                ", modified='" + modified + '\'' +
                ", created='" + created + '\'' +
                ", product_id='" + product_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
