package com.test.iview.dayin.entity.bean;

import java.util.List;

public class LoginBean {

    /**
     * uuid : f568ebd0-58d1-4f5c-abf8-0344cba5c39d
     * createDate : null
     * modifyDate : null
     * telephone : 18510255749
     * apiKey : 2af29cbd-a7a3-4e87-8cc4-55a91a8a9506
     * roles : []
     */

    private String uuid;
    private String createDate;
    private String modifyDate;
    private String telephone;
    private String apiKey;
    private List<?> roles;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<?> getRoles() {
        return roles;
    }

    public void setRoles(List<?> roles) {
        this.roles = roles;
    }
}
