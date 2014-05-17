/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.login;

import com.ist.infinity.resources.utils.CommonUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kareem
 */
public class LoginVerfication {

    /**
     *
     * @param session
     * @param userName
     * @return
     */
    public String loginVerification(HttpSession session, String userID) {

        PreparedStatement pst = null;
        String logintype = "";
        ResultSet rs = null;
        Connection con = null;

        try {

            con = CommonUtils.database_connect();

            System.out.println("con"+con);

            String sql="select txt_user_fname, txt_user_lname from sec_user_master where txt_login_id=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, userID);
            rs = pst.executeQuery();
            if (rs.next()) {
                logintype = "success";
             session.setAttribute("username", rs.getString ("txt_user_fname") + " " + rs.getString ("txt_user_lname"));
                
                session.setAttribute("userid", userID);
            } else {
                logintype = "fail";
            }

        } catch (Exception e) {
            System.out.println(" error is  "+pst);
            System.out.println(" error is  "+rs);

            e.printStackTrace();
            logintype = "fail";
        }
        return logintype;
    }

    /**
     *
     * @param userID
     * @param session
     * @return
     */
    public List getProcedureListData(String userID, HttpSession session) {

        Connection con = CommonUtils.database_connect();
        PreparedStatement pst = null;
        Statement st = null;
        ResultSet rs = null;



        List<LoginDisplayBean> parentlist = new ArrayList();
        OptionBean ob = null;
        OptionBean parent = null;
        LoginDisplayBean lb = null;
        int lastLevel = -1;

        try {
            st = con.createStatement();
            st.execute("SET @@GLOBAL.max_sp_recursion_depth = 8;");
            st.execute("SET @@session.max_sp_recursion_depth = 8;");
            pst = con.prepareCall("call sp_get_user_menus (?,?,?);");
            pst.setString(1, userID);
            pst.setString(2, "");
            pst.setInt(3, 0);
            rs = pst.executeQuery();
            while (rs.next()) {
                int level = rs.getInt("num_menu_level");

                {
                    lb = new LoginDisplayBean();
                    lb.setParent_id(rs.getString("cod_menu_option"));
                    lb.setParent_menu(rs.getString("txt_menu_desc"));
                    lb.setParent_param1(rs.getString("txt_menu_param_1"));
                    lb.setParent_param2(rs.getString("txt_menu_param_2"));
                    lb.setParent_param3(rs.getString("txt_menu_param_3"));
                    lb.setLevel(level);
                    lb.setLast_level(lastLevel);
                    parentlist.add(lb);
                    lastLevel = level;

                }
//                if (level == 1) {
//                    if (lb != null) {
//                       // lb.setParent_param1("#");
//                        ob = new OptionBean();
//                        ob.setChildid(rs.getString("cod_menu_option"));
//                        ob.setChildname(rs.getString("txt_menu_desc"));
//                        ob.setChildparam1(rs.getString("txt_menu_param_1"));
//                        ob.setChildparam2(rs.getString("txt_menu_param_2"));
//                        ob.setChildparam3(rs.getString("txt_menu_param_3"));
//                        List temp = lb.getChilds();
//                        temp.add(ob);
//                        parent = cloneIt(ob);
//                        lb.setChilds(temp);
//
//                    }
//                } else if (level > 1) {
//                    if (parent != null) {
//                       // lb.setParent_param1("#");
//                      //  ob.setChildparam1("#");
//                        parent.setHasMoreChildren(true);
//                        ob = new OptionBean();
//                        ob.setChildid(rs.getString("cod_menu_option"));
//                        ob.setChildname(rs.getString("txt_menu_desc"));
//                        ob.setChildparam1(rs.getString("txt_menu_param_1"));
//                        ob.setChildparam2(rs.getString("txt_menu_param_2"));
//                        ob.setChildparam3(rs.getString("txt_menu_param_3"));
//                        List temp = parent.getSubchild();
//                        temp.add(ob);
//                        ob.setSubchild(temp);
//                        parent = cloneIt(ob);
//
//
//                    }
//                }


            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return parentlist;
    }

    /**
     *
     * @param ob
     * @return
     */
    public OptionBean cloneIt(OptionBean ob) {
        OptionBean parent = new OptionBean();
        parent.setChildid(ob.getChildid());
        parent.setChildname(ob.getChildname());
        parent.setHasMoreChildren(ob.isHasMoreChildren());
        parent.setSubchild(ob.getSubchild());
        return parent;
    }
}