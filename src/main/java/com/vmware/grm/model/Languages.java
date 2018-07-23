package com.vmware.grm.model;

public class Languages {

    private String id;
    private String name;
    private String code;
    private String description;
    private String code_aliases;

    public Languages() {
    }

    public Languages(String id, String name, String code, String description, String code_aliases) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.code_aliases = code_aliases;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getCode_aliases() {
        return code_aliases;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode_aliases(String code_aliases) {
        this.code_aliases = code_aliases;
    }

    @Override
    public String toString() {
        return "Languages{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", code_aliases='" + code_aliases + '\'' +
                '}';
    }
}
