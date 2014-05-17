/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ist.infinity.resources.search;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kareem
 */
public class SearchForm extends org.apache.struts.action.ActionForm {
    
   String search="";
   String txt_search_desc="";
   String typ_search="";
   List searchList=new ArrayList();

    public List getSearchList() {
        return searchList;
    }

    public void setSearchList(List searchList) {
        this.searchList = searchList;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getTxt_search_desc() {
        return txt_search_desc;
    }

    public void setTxt_search_desc(String txt_search_desc) {
        this.txt_search_desc = txt_search_desc;
    }

    public String getTyp_search() {
        return typ_search;
    }

    public void setTyp_search(String typ_search) {
        this.typ_search = typ_search;
    }
}
