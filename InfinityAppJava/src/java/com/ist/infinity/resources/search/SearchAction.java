/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ist.infinity.resources.search;

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
public class SearchAction extends org.apache.struts.action.Action {
    
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
        HttpSession session=request.getSession();
        SearchForm sf=(SearchForm)form;
        SearchDAO sDao=new SearchDAO();
        String username=(String) session.getAttribute("username");
        String search=sf.getSearch();
        List searchListOption=sDao.getSearchList(username,search);
        sf.setSearchList(searchListOption);
        request.setAttribute("searchList", searchListOption);
        for(int i=0;i<searchListOption.size();i++ ){
            System.out.println("SearchList:"+searchListOption.get(i));
        }
        
        
        
        return mapping.findForward("search");
    }
}
