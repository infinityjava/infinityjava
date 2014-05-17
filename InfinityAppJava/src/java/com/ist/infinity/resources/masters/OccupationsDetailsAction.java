/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ist.infinity.resources.masters;

import com.ist.infinity.resources.DAO.MasterTable;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author istpl
 */
public class OccupationsDetailsAction extends org.apache.struts.action.Action {
    
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
            throws Exception {  System.out.println(" hai " + request.getParameter("status"));
        String code = request.getParameter("code").toString();
        System.out.println(" code is "+code);
        request.setAttribute("mode", request.getParameter("status"));
        //MasterPrefixesActionForm mpaf = (MasterPrefixesActionForm) form;
        //mpaf.setCodPrefix(code);
      try{
        HttpSession session = request.getSession();

        String userid = session.getAttribute("userid").toString();
        System.out.println(" user id is "+userid);

            // Prefixes prefix=new Prefixes(SUCCESS, SUCCESS, SUCCESS, 'G',mpaf,userid);
            MasterTable mt = new MasterTable("mst_occupations", "cod_occupation", "txt_occupation_desc", userid);
            
            String details[] = mt.GetRecord(code);

            System.out.println(" details length is " + details.length);
       /*  try{
            for (int i = 0; i < details.length; i++) {
                if(details[i]!=null|| !details[i].equals(""))
                System.out.println(" value is " + details[i]);
              }
          }catch(Exception e){e.printStackTrace();} */

        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("cache-control", "no-cache");
        String res = " <br>Prefix name <input type='text' name='txtOccupationDesc' value='" + details[0] + "'/>";
        out.write(res);
        // out.write("<tr><td><input type='submit' name='method' value='update'/> </td>  <td><input type='submit' name='method' value='delete'/> </td> </tr>");
        out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
        //return mapping.findForward(SUCCESS);
    }

}

