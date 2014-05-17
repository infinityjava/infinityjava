/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ist.infinity.resources.masters;

/**
 *
 * @author istpl
 */
public class MasterPrefixesActionForm extends org.apache.struts.action.ActionForm {
    
    private String codPrefix;
    private String txtPrefixDesc;
    private String column;
    private String column1;

    


    public String getCodPrefix() {
        return codPrefix;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public void setCodPrefix(String codPrefix) {
        this.codPrefix = codPrefix;
    }

    public String getTxtPrefixDesc() {
        return txtPrefixDesc;
    }

    public void setTxtPrefixDesc(String txtPrefixDesc) {
        this.txtPrefixDesc = txtPrefixDesc;
    }

 
    public MasterPrefixesActionForm() {
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
        if (getCodPrefix() == null || getCodPrefix().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    } */
}
