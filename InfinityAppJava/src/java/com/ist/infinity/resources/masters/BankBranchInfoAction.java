/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ist.infinity.resources.masters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author murthy
 */
public class BankBranchInfoAction extends DispatchAction {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

  
    public ActionForward addNewRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("add new record");
        
        //Here code for adding record 
        
        return mapping.findForward(SUCCESS);
    }

   
    public ActionForward modifyRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
          System.out.println("modify record");
          
          // Here code for modify record
          
        return mapping.findForward(SUCCESS);
    }
    
    
    public ActionForward deleteRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
      // Here code for delete record
        
          System.out.println("delete record");
        return mapping.findForward(SUCCESS);
    }
    
    
    public ActionForward authorizedRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
           // Here code for authorized record
        
        System.out.println("authorizes record");
        return mapping.findForward(SUCCESS);
    }
    
    
    
    
    public ActionForward reauthorizedrec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         // Here code for  reauthorised  record
        
        System.out.println("reauthorised record");
        
        return mapping.findForward(SUCCESS);
    }
  
}
