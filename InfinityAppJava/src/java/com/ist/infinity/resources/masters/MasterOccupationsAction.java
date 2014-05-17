/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.masters;

import com.ist.infinity.resources.DAO.Prefixes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author istpl
 */
public class MasterOccupationsAction extends DispatchAction {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    public ActionForward addNewRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MasterOccupationsActionForm moaf = (MasterOccupationsActionForm) form;
        String codOccupation = moaf.getCodOccupation();
        String txtOccupationDesc = moaf.getTxtOccupationDesc();

        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();

       // Prefixes prefix = new Prefixes("mst_occupations", "cod_occupation", "txt_occupation_desc", 'N', userid, codOccupation, txtOccupationDesc);



        //Here code for adding record

        return mapping.findForward(SUCCESS);
    }

    public ActionForward modifyRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("modify record");

        MasterOccupationsActionForm moaf = (MasterOccupationsActionForm) form;
        String codOccupation = moaf.getCodOccupation();
        String txtOccupationDesc = moaf.getTxtOccupationDesc();
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
       // Prefixes prefix = new Prefixes("mst_occupations", "cod_occupation", "txt_occupation_desc", 'M',  userid, codOccupation, txtOccupationDesc);

        return mapping.findForward(SUCCESS);
    }

    public ActionForward deleteRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        MasterOccupationsActionForm moaf = (MasterOccupationsActionForm) form;
        String codOccupation = moaf.getCodOccupation();
        String txtOccupationDesc = moaf.getTxtOccupationDesc();
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
       // Prefixes prefix = new Prefixes("mst_occupations", "cod_occupation", "txt_occupation_desc", 'C',  userid, codOccupation, txtOccupationDesc);

        System.out.println("delete record");
        return mapping.findForward(SUCCESS);
    }

    public ActionForward authorizedRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MasterOccupationsActionForm moaf = (MasterOccupationsActionForm) form;
        String codOccupation = moaf.getCodOccupation();
        String txtOccupationDesc = moaf.getTxtOccupationDesc();
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
       // Prefixes prefix = new Prefixes("mst_occupations", "cod_occupation", "txt_occupation_desc", 'A',  userid, codOccupation, txtOccupationDesc);

        System.out.println("authorizes record");
        return mapping.findForward(SUCCESS);
    }

    public ActionForward reAuthorizedRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        MasterOccupationsActionForm moaf = (MasterOccupationsActionForm) form;
        String codOccupation = moaf.getCodOccupation();
        String txtOccupationDesc = moaf.getTxtOccupationDesc();
         HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
        System.out.println(" user name is " + userid);

        //Prefixes prefix = new Prefixes("mst_occupations", "cod_occupation", "txt_occupation_desc", 'R', userid, codOccupation, txtOccupationDesc);

        System.out.println("reauthorised record");

        return mapping.findForward(SUCCESS);
    }
}
