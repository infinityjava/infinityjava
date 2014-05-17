/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.login;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kareem
 */
public class Login extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
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

        LoginForm lf = (LoginForm) form;
        HttpSession session = request.getSession();
        if (session == null) {
            return mapping.findForward("logout");
        }
        if (session.isNew()) {
            if (session != null) {
                return mapping.findForward("login");
            }
        }
        String userName = lf.getUserName();
        lf.setUserName("");
        //String password = lf.getPassword();
        String sysdate = lf.getSysdate();
        if (userName != null && !userName.trim().equalsIgnoreCase("")) {
            LoginVerfication lv = new LoginVerfication();
            System.err.print("userName"+userName);
            String result = lv.loginVerification(session, userName);
           
            if (!result.trim().equalsIgnoreCase("fail")) {

                session.setAttribute("sysdate", sysdate);
                if (result.equalsIgnoreCase("success")) {

                    List bmList = lv.getProcedureListData(userName, session);
                    request.setAttribute("parentList", bmList);
                    for (int i = 0; i < bmList.size(); i++) {
                        LoginDisplayBean lb = (LoginDisplayBean) bmList.get(i);
                        if(!lb.getChilds().isEmpty()){
                            List temp=lb.getChilds();
                            printRecursiveChildren(temp);
                        }

                    }
                    if (bmList != null && !bmList.isEmpty()) {
                        lf.setParentList(bmList);
                    } else {
                        request.setAttribute("errorFound", "No data present. Please change the dates and see.");
                    }

                    return mapping.findForward("success");
                }

                return mapping.findForward("login");

            } else {
                request.setAttribute("errorFound", "Username and password combination wrong");
                return mapping.getInputForward();
            }
        } else {
            request.setAttribute("errorFound", "Username and password combination wrong");
            return mapping.getInputForward();
        }
    }

    private void printRecursiveChildren(List temp) {
        for(int i=0;i<temp.size();i++){
            OptionBean ob=(OptionBean)temp.get(i);
            if(ob.isHasMoreChildren()){
                printRecursiveChildren(ob.getSubchild());             
            }
        }
    }
}
