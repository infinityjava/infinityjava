/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.homepage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pavani
 */
public class HomePageActionForm extends org.apache.struts.action.ActionForm {

    public List<String> countrieslist;
    public List<String> areaslist;
    public List<String> regionslist;
    public List<String> brancheslist;
    public String country = "";
    public String area = "";
    public String branch = "";
    public String region = "";
    public List<HomePageBean> modulesList = new ArrayList();

    public List getModulesList() {
        return modulesList;
    }

    public void setModulesList(List modulesList) {
        this.modulesList = modulesList;
    }

    public HomePageActionForm() {
        this.brancheslist = new ArrayList<String>();
        this.regionslist = new ArrayList<String>();
        this.areaslist = new ArrayList<String>();
        this.countrieslist = new ArrayList<String>();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<String> getAreaslist() {
        return areaslist;
    }

    public void setAreaslist(List<String> areaslist) {
        this.areaslist = areaslist;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public List<String> getBrancheslist() {
        return brancheslist;
    }

    public void setBrancheslist(List<String> brancheslist) {
        this.brancheslist = brancheslist;
    }

    public List<String> getCountrieslist() {
        return countrieslist;
    }

    public void setCountrieslist(List<String> countrieslist) {
        this.countrieslist = countrieslist;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getRegionslist() {
        return regionslist;
    }

    public void setRegionslist(List<String> regionslist) {
        this.regionslist = regionslist;
    }

    public HomePageBean getHomePageBean(int index) {
        // make sure that orderList is not null
        if (this.modulesList == null) {
            this.modulesList = new ArrayList();
        }

        // indexes do not come in order, populate empty spots
        while (index >= this.modulesList.size()) {
            this.modulesList.add(new HomePageBean());
        }

        // return the requested item
        return (HomePageBean) modulesList.get(index);
    }
}
