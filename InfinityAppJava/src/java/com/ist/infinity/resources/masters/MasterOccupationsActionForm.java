/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ist.infinity.resources.masters;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author istpl
 */
public class MasterOccupationsActionForm extends org.apache.struts.action.ActionForm {
    
    private String codOccupation;
    private String txtOccupationDesc;

    public String getCodOccupation() {
        return codOccupation;
    }

    public void setCodOccupation(String codOccupation) {
        this.codOccupation = codOccupation;
    }

    public String getTxtOccupationDesc() {
        return txtOccupationDesc;
    }

    public void setTxtOccupationDesc(String txtOccupationDesc) {
        this.txtOccupationDesc = txtOccupationDesc;
    }

  
    public MasterOccupationsActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
  /*  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    } */
}
