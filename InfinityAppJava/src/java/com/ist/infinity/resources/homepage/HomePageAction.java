 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.homepage;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;

/**
 *
 * @author pavani
 */
public class HomePageAction extends org.apache.struts.action.Action {
    /* forward name="success" path="" */

    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        HomePageActionForm hpaf = (HomePageActionForm) form;

        HomePageDAO hpd = new HomePageDAO();
        JSONArray jsonDashBoardObjectsArray = hpd.getDashobards(String.valueOf(session.getAttribute("userid")));
        String jsonarray = jsonDashBoardObjectsArray.toJSONString();
        request.setAttribute("jsonarray", jsonarray);
        String globalVars = hpd.getGlobalVars();
        session.setAttribute("sysdate", (globalVars.split("@@"))[0]);
        session.setAttribute("bankname", (globalVars.split("@@"))[1]);
        List<HomePageBean> modules=hpd.getSysInstallModules(String.valueOf(session.getAttribute("bankname")));
        hpaf.setModulesList(modules);
        session.setAttribute("modules",modules);
        return mapping.findForward(SUCCESS);
    }
}