/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.homepage;

import com.ist.infinity.resources.homepage.HomePageBean;
import com.ist.infinity.resources.utils.CommonUtils;
import com.ist.infinity.resources.utils.SessionUtil;
import com.mysql.jdbc.Connection;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.ist.infinity.resources.utils.CommonUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pavani
 */
public class HomePageDAO {

    public HomePageDAO() {
    }

    /**
     * This function gets dashboards from database
     * @param userid
     * @return
     */
    public JSONArray getDashobards(String userid) {
        Connection con = (Connection) CommonUtils.database_connect();
        PreparedStatement pst = null;

        JSONArray jsonDashBoardObjectsArray = new JSONArray();
        try {

            String getDashBoardsQuery = "select s.txt_metrics_name "
                    + "    , s.txt_metrics_desc,"
                    + " s.txt_metrics_units_desc,"
                    + " s.txt_nam_metrics_line_1,"
                    + " sum(amt_metrics_line_1),"
                    + " txt_nam_metrics_line_2,"
                    + " sum(amt_metrics_line_2),"
                    + " txt_nam_metrics_line_3,"
                    + " sum(amt_metrics_line_3),"
                    + " sum(amt_metrics_plan),"
                    + " num_display_seq,"
                    + " enu_display_format"
                    + "  from infinity.sum_dashboard_metrics_setup s,"
                    + " infinity.sum_loan_dashboard_metrics m,"
                    + " infinity.sum_user_dashboard u  "
                    + "where s.txt_metrics_name = m.txt_metrics_name "
                    + "   and m.txt_metrics_name = u.txt_metrics_name "
                    + "   and u.txt_login_id = ? "
                    + "group by s.txt_metrics_name,"
                    + " txt_metrics_desc,"
                    + " txt_nam_metrics_line_1,"
                    + " txt_nam_metrics_line_2,"
                    + " txt_nam_metrics_line_3,"
                    + " txt_metrics_units_desc,"
                    + " num_display_seq,"
                    + " enu_display_format "
                    + "order by num_display_seq";
            pst = (PreparedStatement) con.prepareStatement(getDashBoardsQuery);
            pst.setString(1, userid);
            ResultSet rs = pst.executeQuery();
            String name = "name";
            String value = "value";
            int iterator = 1;
            while (rs.next()) {

                JSONArray chartArray = new JSONArray();

                JSONObject object1 = new JSONObject();
                object1.put(name, "Current Month");
                object1.put(value, rs.getString("sum(amt_metrics_line_1)"));

                JSONObject object2 = new JSONObject();
                object2.put(name, "Last 30 days");
                object2.put(value, rs.getString("sum(amt_metrics_line_2)"));

                JSONObject object3 = new JSONObject();
                object3.put(name, "Last Year");
                object3.put(value, rs.getString("sum(amt_metrics_line_3)"));

                JSONObject object4 = new JSONObject();
                object4.put(name, "Plan");
                object4.put(value, rs.getString("sum(amt_metrics_plan)"));

                JSONObject object5 = new JSONObject();

                String graph = "graph" + iterator;
                object5.put(graph, rs.getString("txt_metrics_desc"));
                chartArray.add(object1);
                chartArray.add(object2);
                chartArray.add(object3);
                chartArray.add(object4);



                jsonDashBoardObjectsArray.add(chartArray);
                jsonDashBoardObjectsArray.add(object5);
                iterator++;
            }

            System.out.println("JSON STRING " + jsonDashBoardObjectsArray.toJSONString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonDashBoardObjectsArray;
    }

    /**
     * This function gets sys_global_vars from database
     * @return global vars
     */
    public String getGlobalVars() {
        String global_vars = "";
        Connection con = (Connection) CommonUtils.database_connect();
        PreparedStatement pst = null;

        try {

            String getDashBoardsQuery = "select txt_bank_name,DATE_FORMAT(dat_system_asof,'%d-%m,%Y') as dat_system_asof from sys_global_vars";
            pst = (PreparedStatement) con.prepareStatement(getDashBoardsQuery);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
                global_vars = rs.getString("dat_system_asof") + "@@" + String.valueOf(rs.getString("txt_bank_name"));

            }
            System.out.println("GLOBAL VARS " + global_vars);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return global_vars;

    }

    /**
     * This function returns sys install modules from database
     * @param bankname
     * @return modules
     */
    public List getSysInstallModules(String bankname) {
        List<HomePageBean> modules = new ArrayList<HomePageBean>();
        Connection con = (Connection) CommonUtils.database_connect();
        PreparedStatement pst = null;

        ResultSet rs = null;

        try {
            pst = (PreparedStatement) con.prepareStatement("select * from sys_install_modules where txt_bank_name=?");
            pst.setString(1, bankname);
            rs = pst.executeQuery();
            HomePageBean hpb = null;
            while (rs.next()) {
                hpb = new HomePageBean();
                hpb.setModulename(rs.getString("txt_module_name"));
                hpb.setBankname(rs.getString("txt_bank_name"));
                hpb.setRedirectedto(rs.getString("txt_module_param_1"));
                modules.add(hpb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modules;
    }
}