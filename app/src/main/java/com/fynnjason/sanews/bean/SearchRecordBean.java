package com.fynnjason.sanews.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class SearchRecordBean {

    @Id
    private String searchText;

    @Generated(hash = 37699371)
    public SearchRecordBean(String searchText) {
        this.searchText = searchText;
    }

    @Generated(hash = 1260263942)
    public SearchRecordBean() {
    }

    public String getSearchText() {
        return this.searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }


}
