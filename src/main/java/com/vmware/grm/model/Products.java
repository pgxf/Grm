package com.vmware.grm.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/13/2018
 * Time:10:27 AM
 **/
public class Products {

    String id;
    String name;
    String release_date;
    String version;
    String code_freeze_date;
    Timestamp modified;
    Timestamp created;
    List<String> supported_languages;

    public Products() {
    }

    public Products(String id, String name, String release_date, String version, String code_freeze_date, Timestamp modified, Timestamp created, List<String> supported_languages) {
        this.id = id;
        this.name = name;
        this.release_date = release_date;
        this.version = version;
        this.code_freeze_date = code_freeze_date;
        this.modified = modified;
        this.created = created;
        this.supported_languages = supported_languages;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getVersion() {
        return version;
    }

    public String getCode_freeze_date() {
        return code_freeze_date;
    }

    public Timestamp getModified() {
        return modified;
    }

    public Timestamp getCreated() {
        return created;
    }

    public List<String> getSupported_languages() {
        return supported_languages;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCode_freeze_date(String code_freeze_date) {
        this.code_freeze_date = code_freeze_date;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setSupported_languages(List<String> supported_languages) {
        this.supported_languages = supported_languages;
    }

    public void setSupported_languagesItem(String id){
        if(this.getSupported_languages()!=null){
            this.getSupported_languages().add(id);
        }else{
            this.supported_languages = new ArrayList<>();
            this.getSupported_languages().add(id);
        }
    }

    @Override
    public String toString() {
        return "Products{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", version='" + version + '\'' +
                ", code_freeze_date='" + code_freeze_date + '\'' +
                ", modified='" + modified + '\'' +
                ", created='" + created + '\'' +
                ", supported_languages=" + supported_languages +
                '}';
    }
}
