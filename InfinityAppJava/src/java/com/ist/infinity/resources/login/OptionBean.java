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
public class OptionBean {

    String childname = "";
    String childid = "";
    String childparam1 = "";
    String childparam2 = "";
    String childparam3 = "";
    boolean hasMoreChildren=false;
    List subchild = new ArrayList();

    public String getChildparam1() {
        return childparam1;
    }

    public void setChildparam1(String childparam1) {
        this.childparam1 = childparam1;
    }

    public String getChildparam2() {
        return childparam2;
    }

    public void setChildparam2(String childparam2) {
        this.childparam2 = childparam2;
    }

    public String getChildparam3() {
        return childparam3;
    }

    public void setChildparam3(String childparam3) {
        this.childparam3 = childparam3;
    }

    public String getChildid() {
        return childid;
    }

    public boolean isHasMoreChildren() {
        return hasMoreChildren;
    }

    public void setHasMoreChildren(boolean hasMoreChildren) {
        this.hasMoreChildren = hasMoreChildren;
    }


    public void setChildid(String childid) {
        this.childid = childid;
    }

   

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    public List getSubchild() {
        return subchild;
    }

    public void setSubchild(List subchild) {
        this.subchild = subchild;
    }




    public OptionBean getOptionBean(int index) {
        // make sure that orderList is not null
        if (this.subchild == null) {
            this.subchild = new ArrayList();
        }

        // indexes do not come in order, populate empty spots
        while (index >= this.subchild.size()) {
            this.subchild.add(new OptionBean());
        }

        // return the requested item
        return (OptionBean) subchild.get(index);
    }
}
