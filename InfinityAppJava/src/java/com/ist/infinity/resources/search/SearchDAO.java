/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ist.infinity.resources.search;

import com.ist.infinity.resources.utils.CommonUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kareem
 */
public class SearchDAO {

    List getSearchList(String username, String search) {
        List SearchList=new ArrayList();
        
        Connection con = (Connection) CommonUtils.database_connect();
        PreparedStatement pst = null;
        Statement st=null;
        ResultSet rs = null;
        SearchForm sf=null;
         try {
            st=(Statement) con.createStatement();
            st.execute("SET @@GLOBAL.max_sp_recursion_depth = 8;");
            st.execute("SET @@session.max_sp_recursion_depth = 8;");
            pst = (PreparedStatement) con.prepareCall("call sp_get_search_results(?,?);");
            pst.setString(1, username);
            pst.setString(2, search);
            rs = pst.executeQuery();
            while (rs.next()) {
                sf=new SearchForm();
                String txt_search_desc=rs.getString("txt_search_desc");
                String typ_search=rs.getString("typ_search");
                sf.setTxt_search_desc(rs.getString("txt_search_desc"));
                sf.setTyp_search(rs.getString("typ_search"));
                SearchList.add(sf);
                System.out.println("txt_search_desc:"+txt_search_desc);
                System.out.println("typ_search:"+typ_search);
                
            }
               
        } catch (Exception e) {

            e.printStackTrace();
        }


        return SearchList;

    }


}
