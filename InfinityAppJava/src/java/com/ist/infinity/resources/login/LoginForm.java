/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.login;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author kareem
 */
public class LoginForm extends org.apache.struts.action.ActionForm {

 

    private String userName = "";
    private String password = "";
    List parentList = new ArrayList();
    Date sdate=new Date();
    String sysdate = new SimpleDateFormat("E dd.MMM.yyyy hh:mm:ss a").format(sdate);

    int numMenuLevel;

    public int getNumMenuLevel() {
        return numMenuLevel;
    }

    public void setNumMenuLevel(int numMenuLevel) {
        this.numMenuLevel = numMenuLevel;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }
    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public List getParentList() {
        return parentList;
    }

    public void setParentList(List parentList) {
        this.parentList = parentList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LoginDisplayBean getLoginDisplayBean(int index) {
        // make sure that orderList is not null
        if (this.parentList == null) {
            this.parentList = new ArrayList();
        }

        // indexes do not come in order, populate empty spots
        while (index >= this.parentList.size()) {
            this.parentList.add(new LoginDisplayBean());
        }

        // return the requested item
        return (LoginDisplayBean) parentList.get(index);
    }

    /**
     *
     */
    public LoginForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getUserName() == null || getUserName().length() < 1) {
            errors.add("userName", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
