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
public class MasterPrefixesAction extends DispatchAction {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    public ActionForward addNewRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MasterPrefixesActionForm mpaf = (MasterPrefixesActionForm) form;
        String cod_prefix = mpaf.getCodPrefix();
        String txtPrefixDesc = mpaf.getTxtPrefixDesc();
        String column = mpaf.getColumn();
        String column1 = mpaf.getColumn1();
        HttpSession session = request.getSession();
        String[] arr=new String[2];
        arr[0]=column;
        arr[1]=column1;

       // String userid = session.getAttribute("userid").toString();
        String userid="murthy";

        Prefixes prefix = new Prefixes("mst_prefixes", "cod_prefix", "txt_prefix_desc", 'N',  userid,cod_prefix,txtPrefixDesc);



        //Here code for adding record

        return mapping.findForward(SUCCESS);
    }

    public ActionForward modifyRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("modify record");

      MasterPrefixesActionForm mpaf = (MasterPrefixesActionForm) form;
        String cod_prefix = mpaf.getCodPrefix();
        String txtPrefixDesc = mpaf.getTxtPrefixDesc();
        System.out.println("desc " + txtPrefixDesc);
        System.out.println("code " + cod_prefix);
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
      //  Prefixes prefix=new Prefixes("mst_prefixes", "cod_prefix", "txt_prefix_desc", 'M',userid,cod_prefix,txtPrefixDesc);

        return mapping.findForward(SUCCESS);
    }

    public ActionForward deleteRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        MasterPrefixesActionForm mpaf = (MasterPrefixesActionForm) form;
        String cod_prefix = mpaf.getCodPrefix();
        String txtPrefixDesc = mpaf.getTxtPrefixDesc();
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
      //  Prefixes prefix = new Prefixes("mst_prefixes", "cod_prefix", "txt_prefix_desc", 'C',  userid,cod_prefix,txtPrefixDesc);

        System.out.println("delete record");
        return mapping.findForward(SUCCESS);
    }

    public ActionForward authorizedRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MasterPrefixesActionForm mpaf = (MasterPrefixesActionForm) form;
        String cod_prefix = mpaf.getCodPrefix();
        String txtPrefixDesc = mpaf.getTxtPrefixDesc();
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
       // Prefixes prefix = new Prefixes("mst_prefixes", "cod_prefix", "txt_prefix_desc", 'A',  userid,cod_prefix,txtPrefixDesc);

        System.out.println("authorizes record");
        return mapping.findForward(SUCCESS);
    }

    public ActionForward reAuthorizedRec(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

       MasterPrefixesActionForm mpaf = (MasterPrefixesActionForm) form;
        String cod_prefix = mpaf.getCodPrefix();
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
        System.out.println(" user name is " + userid);

       // Prefixes prefix = new Prefixes("mst_prefixes", "cod_prefix", "txt_prefix_desc", 'R', userid,cod_prefix,"");

        System.out.println("reauthorised record");

        return mapping.findForward(SUCCESS);
    }
}
