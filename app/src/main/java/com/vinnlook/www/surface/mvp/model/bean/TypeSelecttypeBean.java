package com.vinnlook.www.surface.mvp.model.bean;

import java.util.List;

public class TypeSelecttypeBean {
    String typeID;//第一层ID
    String id;//第二层ID
    List<String> list;//将颜色，规格，度数里边的具体数据的ID保存到一起

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
