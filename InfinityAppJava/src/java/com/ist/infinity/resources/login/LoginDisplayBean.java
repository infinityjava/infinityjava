/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.login;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kareem
 */
public class LoginDisplayBean {
    String parent_id="";
    String parent_menu="";
    String parent_param1="";
    String parent_param2="";
    String parent_param3="";
    int level;

    public int getLast_level() {
        return last_level;
    }

    public void setLast_level(int last_level) {
        this.last_level = last_level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    int last_level;
    List childs=new ArrayList();

    public String getParent_param1() {
        return parent_param1;
    }

    public void setParent_param1(String parent_param1) {
        this.parent_param1 = parent_param1;
    }

    public String getParent_param2() {
        return parent_param2;
    }

    public void setParent_param2(String parent_param2) {
        this.parent_param2 = parent_param2;
    }

    public String getParent_param3() {
        return parent_param3;
    }

    public void setParent_param3(String parent_param3) {
        this.parent_param3 = parent_param3;
    }

    

    public List getChilds() {
        return childs;
    }

    public void setChilds(List childs) {
        this.childs = childs;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_menu() {
        return parent_menu;
    }

    public void setParent_menu(String parent_menu) {
        this.parent_menu = parent_menu;
    }

       public OptionBean getOptionBean(int index) {
        // make sure that orderList is not null
        if (this.childs == null) {
            this.childs = new ArrayList();
        }

        // indexes do not come in order, populate empty spots
        while (index >= this.childs.size()) {
            this.childs.add(new OptionBean());
        }

        // return the requested item
        return (OptionBean) childs.get(index);
    }
}

