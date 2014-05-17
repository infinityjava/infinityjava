/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.DAO;

import com.ist.infinity.resources.utils.CommonUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MasterTable {

    String ltxt_table_name, ltxt_key_col_name, ltxt_descr_col_name, ltxt_key_col_value, ltxt_descr_col_value;
    String[] larr_othercol_names = new String[64];
    String[] larr_othercol_types = new String[64];
    String[] larr_othercol_values = new String[64];
    private int li_num_othercols = 0, li_ctr = 0;
    String gtxt_curr_user_id;
    Integer lint_auto_auth = 0;
    Integer lint_valid_record = 1;
    String ltxt_SQL_stmt;
    private Connection lo_connect = null;
    private Statement lo_statement = null;
    private PreparedStatement lo_preparedStatement = null;
    private ResultSet lo_resultSet = null;

    public MasterTable(String TableName, String KeyColName, String DescrColName, String username) {
        String ltxt_colname = null, ltxt_coltype = null;
        ltxt_table_name = TableName;
        ltxt_key_col_name = KeyColName;
        ltxt_descr_col_name = DescrColName;

        System.out.println("ltxt_descr_col_name" + ltxt_descr_col_name);
        final String UserName = "root";
        final String Password = "root";
        final String DatabaseURL = "jdbc:mysql://localhost:3306/kycapp";
        final String DriverName = "com.mysql.jdbc.Driver";

        String databaseURL = DatabaseURL;

        try {
            lo_connect = DriverManager.getConnection(databaseURL, "root", "root");
           // lo_connect = CommonUtils.database_connect();
        } catch (Exception e) {
            System.out.println("Error getConnection:" + databaseURL + " " + e);

        }

        /*    connect = DBUtils.database_connect(); */

        ltxt_SQL_stmt = " select txt_table_name from sec_auto_auth_tables where txt_table_name = '" + ltxt_table_name + "' and cod_rec_status = 'A';";
        System.out.println(ltxt_SQL_stmt);
        lint_auto_auth = -1;
        /*
         ** If record found then the table is on "Auto-Auth"
         */
        try {

            lo_statement = lo_connect.createStatement();
            // Result set get the result of the SQL query
            lo_resultSet = lo_statement.executeQuery(ltxt_SQL_stmt);
            while (lo_resultSet.next()) {
                lint_auto_auth = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
// select txt_table_name from sec_auto_auth_tables where txt_table_name = ' mst_prefixes' and cod_rec_status = 'A';";
        gtxt_curr_user_id = username;

        ltxt_SQL_stmt = " SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '" + ltxt_table_name + "' ;";
        System.out.println(ltxt_SQL_stmt);
        try {

            lo_statement = lo_connect.createStatement();
            lo_resultSet = lo_statement.executeQuery(ltxt_SQL_stmt);
            while (lo_resultSet.next()) {

                ltxt_colname = lo_resultSet.getString("COLUMN_NAME");
                ltxt_coltype = lo_resultSet.getString("DATA_TYPE");
                if (!ltxt_colname.equals(ltxt_key_col_name)
                        && !ltxt_colname.equals(ltxt_descr_col_name)
                        && !ltxt_colname.equals("cod_rec_status")
                        && !ltxt_colname.equals("txt_last_maker_id")
                        && !ltxt_colname.equals("dat_last_maker")
                        && !ltxt_colname.equals("txt_last_checker_id")
                        && !ltxt_colname.equals("dat_last_checker")) {
                    larr_othercol_names[li_num_othercols] = ltxt_colname;
                    larr_othercol_types[li_num_othercols] = ltxt_coltype;
                    li_num_othercols++;
                    System.out.println(ltxt_colname + " " + ltxt_coltype);
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }// Constructor

    public String getTableName() {
        return ltxt_table_name;
    }

    public void setTableName(String ptxt_table_name) {
        ltxt_table_name = ptxt_table_name;
    }

    public String getKeyColValue() {
        return (ltxt_key_col_value);
    }

    public void setKeyColValue(String ptxt_key_col_value) {
        ltxt_key_col_value = ptxt_key_col_value;
    }

    public Integer getValidRecord() {
        return lint_valid_record;
    }

    public void setValidRecord(Integer pint_valid_record) {
        lint_valid_record = pint_valid_record;
    }

    public String getDescrColValue() {
        return (ltxt_descr_col_value);
    }

    public void setDescrColValue(String ptxt_descr_col_value) {
        ltxt_descr_col_value = ptxt_descr_col_value;
    }

    public String[] GetRecord(String ptxt_key_col_value) {
        String ltxt_colname = null;
        String ltxt_coltype = null;

        ltxt_SQL_stmt = "select * from " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "';";

        /*
         ** We know we will always have a description column. Save the value for
         ** that column separately and then get to the other columns.
         */
        System.out.println(ltxt_SQL_stmt);
        try {

            lo_statement = lo_connect.createStatement();
            lo_resultSet = lo_statement.executeQuery(ltxt_SQL_stmt);
            if (lo_resultSet.next()) {
                lo_resultSet.previous();
                while (lo_resultSet.next()) {
                    li_ctr = 0;
                    while (li_ctr < li_num_othercols
                            && (larr_othercol_names[li_ctr] != null && !larr_othercol_names[li_ctr].equals(""))) {

                        if (larr_othercol_types[li_ctr].equals("char")
                                || larr_othercol_types[li_ctr].equals("varchar")
                                || larr_othercol_types[li_ctr].equals("enum")) {
                            larr_othercol_values[li_ctr] = lo_resultSet.getString(larr_othercol_names[li_ctr]);
                        } else if (larr_othercol_types[li_ctr].equals("date")); else if (larr_othercol_types[li_ctr].equals("int"));

                        System.out.println(larr_othercol_names[li_ctr] + " " + larr_othercol_values[li_ctr]);
                        li_ctr++;
                    }
                    // added extra code here

                    larr_othercol_values[li_ctr] = lo_resultSet.getString(ltxt_descr_col_name);


                }

            } else {

                System.out.println("No record found try again...");
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        ltxt_descr_col_value = ltxt_descr_col_value;

        return (larr_othercol_values);

    }

    public int AddNew(String ptxt_key_col_value, String ptxt_descr_col_value) {

        if (lint_valid_record == 0) {
            return (-1);
        }

        ltxt_SQL_stmt = "select * from " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "';";

        System.out.println(ltxt_SQL_stmt);
        /*
         ** If no rows found, then continue with insert, else return error.
         */
        try {

            lo_statement = lo_connect.createStatement();
            lo_resultSet = lo_statement.executeQuery(ltxt_SQL_stmt);
            while (lo_resultSet.next()) {
                System.out.println("Record already exists " + ptxt_key_col_value);
                return (-1);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        ltxt_SQL_stmt = "insert into " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "(" + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "," + ltxt_descr_col_name;



        
        ltxt_SQL_stmt = ltxt_SQL_stmt + ", cod_rec_status, txt_last_maker_id, dat_last_maker, txt_last_checker_id, dat_last_checker) VALUES (";
        ltxt_SQL_stmt = ltxt_SQL_stmt + " '" + ptxt_key_col_value;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "', '" + ptxt_descr_col_value;



        if (lint_auto_auth != 1) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + "','N','" + gtxt_curr_user_id;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "',DATE_FORMAT(NOW(),'%Y-%m-%d'),null, null);";
        } else {
            ltxt_SQL_stmt = ltxt_SQL_stmt + "','A','" + gtxt_curr_user_id;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "',DATE_FORMAT(NOW(),'%Y-%m-%d'), 'AUTO', DATE_FORMAT(NOW(),'%Y-%m-%d'));";
        }



       /* li_ctr = 0;
        while (arr[li_ctr++] != null && !arr[li_ctr].equals("")) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + ", " + arr[li_ctr];
        }*/



        System.out.println(ltxt_SQL_stmt);
        try {

            lo_statement = lo_connect.createStatement();
            lo_statement.executeUpdate(ltxt_SQL_stmt);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        ltxt_key_col_value = ptxt_key_col_value;
        ltxt_descr_col_value = ptxt_descr_col_value;

        if (lint_auto_auth == 1) {
            CreateAuditTrail();
        }

        return (0);
    }

    public int Modify(String ptxt_key_col_value, String ptxt_descr_col_value) {

        if (lint_valid_record == 0) {
            return (-1);
        }

        ltxt_SQL_stmt = "select cod_rec_status from " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "'; ";

        System.out.println(ltxt_SQL_stmt);
        try {

            lo_statement = lo_connect.createStatement();
            lo_resultSet = lo_statement.executeQuery(ltxt_SQL_stmt);
            System.out.println("lo resultset is" + lo_resultSet);
            while (lo_resultSet.next()) {
                /*
                 ** If cod_rec_status is NOT 'A' then return error
                 */
                if (!lo_resultSet.getString("cod_rec_status").equals("A")) {
                    return (-1);
                }
                li_ctr++;
            }
            /*
             ** If no rows found, then return error. www.doratrust.infinity.istpl.co
             */
            System.out.println(" number is li ctr is " + li_ctr);
            if (li_ctr == 0) {
                return (-1);
            } /*
             ** If more than one record found, then return error
             */ else if (li_ctr > 1) {
                return (-1);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        /*
         ** Now, insert an "M" record.
         */
        System.out.println(" lint_auto_auth" + lint_auto_auth);

        if (lint_auto_auth == 1) {
            ltxt_SQL_stmt = "delete " + ltxt_table_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "' ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + " and cod_rec_status = 'A'; ";

            System.out.println(ltxt_SQL_stmt);
            try {
                lo_statement = lo_connect.createStatement();
                lo_statement.executeUpdate(ltxt_SQL_stmt);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }

        ltxt_SQL_stmt = "insert into " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "(" + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "," + ltxt_descr_col_name;
        li_ctr = 0;
        while (larr_othercol_names[li_ctr++] != null && !larr_othercol_names[li_ctr].equals("")) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + ", " + larr_othercol_names[li_ctr];
        }
        ltxt_SQL_stmt = ltxt_SQL_stmt + ", cod_rec_status, txt_last_maker_id, dat_last_maker, txt_last_checker_id, dat_last_checker) VALUES (";
        ltxt_SQL_stmt = ltxt_SQL_stmt + " '" + ptxt_key_col_value;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "', '" + ptxt_descr_col_value;
        li_ctr = 0;
        while (larr_othercol_names[li_ctr++] != null && !larr_othercol_names[li_ctr].equals("")) {
            if (larr_othercol_types[li_ctr].equals("char")
                    || larr_othercol_types[li_ctr].equals("varchar")) {
                ltxt_SQL_stmt = ltxt_SQL_stmt + ", '" + larr_othercol_values[li_ctr] + "'";
            } else {
                ltxt_SQL_stmt = ltxt_SQL_stmt + ", " + larr_othercol_values[li_ctr];
            }
        }

      if (lint_auto_auth != 1) {
        ltxt_SQL_stmt = ltxt_SQL_stmt + "','M','" + gtxt_curr_user_id;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "',DATE_FORMAT(NOW(),'%Y-%m-%d'),null, null);";
        } else {
        ltxt_SQL_stmt = ltxt_SQL_stmt + "','A','" + gtxt_curr_user_id;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "',DATE_FORMAT(NOW(),'%Y-%m-%d'), 'AUTO', DATE_FORMAT(NOW(),'%Y-%m-%d'));";
        } 

/*
        if (lint_auto_auth == 0) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + "','A','" + gtxt_curr_user_id;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "',DATE_FORMAT(NOW(),'%Y-%m-%d'), 'AUTO', DATE_FORMAT(NOW(),'%Y-%m-%d'));";

        } else {
            ltxt_SQL_stmt = ltxt_SQL_stmt + "','M','" + gtxt_curr_user_id;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "',DATE_FORMAT(NOW(),'%Y-%m-%d'),null, null);";
        } 
 */




        System.out.println(ltxt_SQL_stmt);
        try {
            lo_statement = lo_connect.createStatement();
            lo_statement.executeUpdate(ltxt_SQL_stmt);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        ltxt_key_col_value = ptxt_key_col_value;
        ltxt_descr_col_value = ptxt_descr_col_value;

        if (lint_auto_auth == 1) {
            CreateAuditTrail();
        }

        return (0);
    }

    public int Close(String ptxt_key_col_value) {
        ltxt_SQL_stmt = "select cod_rec_status from " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "';";

        System.out.println(ltxt_SQL_stmt);
        try {
            lo_statement = lo_connect.createStatement();
            lo_resultSet = lo_statement.executeQuery(ltxt_SQL_stmt);
            /*
             ** If no rows found, then return error.
             */
            while (lo_resultSet.next()) {

                /*
                 ** If cod_rec_status is NOT 'A' then return error
                 */
                if (!lo_resultSet.getString("cod_rec_status").equals("A")) {
                    return (-1);
                }
                li_ctr++;
            }
            /*
             ** If no rows found, then return error.
             */
            if (li_ctr == 0) {
                return (-1);
            } /*
             ** If more than one record found, then return error
             */ else if (li_ctr > 1) {
                return (-1);
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        /*
         ** Now, insert a "X" record.
         */

        if (lint_auto_auth == 1) {
            ltxt_SQL_stmt = "update " + ltxt_table_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + " set cod_rec_status = 'C' ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "     txt_last_maker_id = '" + gtxt_curr_user_id;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   , dat_last_maker = DATE_FORMAT(NOW(),'%Y-%m-%d') ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "     txt_last_checker_id = 'AUTO'";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   , dat_last_checker = DATE_FORMAT(NOW(),'%Y-%m-%d') ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "' ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   and cod_rec_status = 'A';";

        } else {

            ltxt_SQL_stmt = "insert into " + ltxt_table_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "(" + ltxt_key_col_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "," + ltxt_descr_col_name;
            li_ctr = 0;
            while (larr_othercol_names[li_ctr] != null && !larr_othercol_names[li_ctr].equals("")) {
                ltxt_SQL_stmt = ltxt_SQL_stmt + ", " + larr_othercol_names[li_ctr++];
            }
            ltxt_SQL_stmt = ltxt_SQL_stmt + ", cod_rec_status, txt_last_maker_id, dat_last_maker, txt_last_checker_id, dat_last_checker) ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + " SELECT " + ltxt_key_col_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + ", " + ltxt_descr_col_name;
            li_ctr = 0;
            while (larr_othercol_names[li_ctr] != null && !larr_othercol_names[li_ctr].equals("")) {
                ltxt_SQL_stmt = ltxt_SQL_stmt + ", " + larr_othercol_names[li_ctr++];
            }
            ltxt_SQL_stmt = ltxt_SQL_stmt + ",'X','" + gtxt_curr_user_id;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "',DATE_FORMAT(NOW(),'%Y-%m-%d'),null, null ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   from " + ltxt_table_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "  where " + ltxt_key_col_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "' ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "    and cod_rec_status = 'A';";
        }

        ltxt_key_col_value = ptxt_key_col_value;

        System.out.println(ltxt_SQL_stmt);
        try {
            lo_statement = lo_connect.createStatement();
            lo_statement.executeUpdate(ltxt_SQL_stmt);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        return (0);
    }

    public int Reopen(String ptxt_key_col_value) {
        ltxt_SQL_stmt = "select cod_rec_status from " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "';";

        System.out.println(ltxt_SQL_stmt);
        try {
            li_ctr = 0;
            lo_statement = lo_connect.createStatement();
            lo_resultSet = lo_statement.executeQuery(ltxt_SQL_stmt);
            /*
             ** If cod_rec_status is NOT 'C' then return error
             */
            while (lo_resultSet.next()) {
                if (!lo_resultSet.getString("cod_rec_status").equals("C")) {
                    return (-1);
                }
                li_ctr++;
            }
            /*1
             ** If no rows found, then return error.
             */
            if (li_ctr == 0) {
                return (-1);
            } /*
             ** If more than one record found, then return error
             */ else if (li_ctr > 1) {
                return (-1);
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        /*
         ** Now, insert a "R" record.
         */
        if (lint_auto_auth == 1) {
            ltxt_SQL_stmt = "update " + ltxt_table_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + " set cod_rec_status = 'A' ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "     txt_last_maker_id = '" + gtxt_curr_user_id;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   , dat_last_maker = DATE_FORMAT(NOW(),'%Y-%m-%d') ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "     txt_last_checker_id = 'AUTO'";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   , dat_last_checker = DATE_FORMAT(NOW(),'%Y-%m-%d') ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "' ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   and cod_rec_status = 'C';";
        } else {
            ltxt_SQL_stmt = "insert into " + ltxt_table_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "(" + ltxt_key_col_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "," + ltxt_descr_col_name;
            li_ctr = 0;
            while (larr_othercol_names[li_ctr] != null && !larr_othercol_names[li_ctr].equals("")) {
                ltxt_SQL_stmt = ltxt_SQL_stmt + ", " + larr_othercol_names[li_ctr++];
            }
            ltxt_SQL_stmt = ltxt_SQL_stmt + ", cod_rec_status, txt_last_maker_id, dat_last_maker, txt_last_checker_id, dat_last_checker) ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + " SELECT " + ltxt_key_col_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + ", " + ltxt_descr_col_name;
            li_ctr = 0;
            while (larr_othercol_names[li_ctr] != null && !larr_othercol_names[li_ctr].equals("")) {
                ltxt_SQL_stmt = ltxt_SQL_stmt + ", " + larr_othercol_names[li_ctr++];
            }
            ltxt_SQL_stmt = ltxt_SQL_stmt + ",'R','" + gtxt_curr_user_id;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "',DATE_FORMAT(NOW(),'%Y-%m-%d'),null, null";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   from " + ltxt_table_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + "  where " + ltxt_key_col_name;
            ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "' ";
            ltxt_SQL_stmt = ltxt_SQL_stmt + "    and cod_rec_status = 'C';";
        }

        System.out.println(ltxt_SQL_stmt);
        try {
            lo_statement = lo_connect.createStatement();
            lo_statement.executeUpdate(ltxt_SQL_stmt);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        ltxt_key_col_value = ptxt_key_col_value;

        if (lint_auto_auth == 1) {
            CreateAuditTrail();
        }

        return (0);
    }

    public int Authorize(String ptxt_key_col_value) {
        String ltxt_cod_rec_status = null, ltxt_maker_id = null;

        ltxt_SQL_stmt = "select cod_rec_status, txt_last_maker_id from " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "'";
        ltxt_SQL_stmt = ltxt_SQL_stmt + " and cod_rec_status not in ('A', 'C');";

        System.out.println(ltxt_SQL_stmt);
        try {
            li_ctr = 0;
            lo_statement = lo_connect.createStatement();
            lo_resultSet = lo_statement.executeQuery(ltxt_SQL_stmt);

            while (lo_resultSet.next()) {
                ltxt_cod_rec_status = lo_resultSet.getString("cod_rec_status");
                ltxt_maker_id = lo_resultSet.getString("txt_last_maker_id");
                li_ctr++;
            }
         /*   System.out.println("code status is "+ltxt_cod_rec_status);
            System.out.println("maker id is "+ltxt_maker_id);
            System.out.println("maker id is "+gtxt_curr_user_id);
            /*
             ** If no rows found, then return error.
             */
            if (li_ctr == 0) {
                return (-1);
            }
            /*
             ** If more than one record found, then return error
             */
            if (li_ctr > 1) {
                return (-1);
            }

            /*
             ** If txt_last_maker_id = gtxt_curr_user_id then return error
             ** The maker and checker cannot be the same on any record
             */
            if (ltxt_maker_id.equals(gtxt_curr_user_id)) {
                return (-3);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        /*
         ** If cod_rec_status is 'N', this is a new record
         ** If cod_rec_status is 'R', this is a closed record to be re-opened
         ** If cod_rec_status is 'X', this is a closure request
         ** If cod_rec_status is 'M', this is a modify request - we need to delete the old 'A' record.
         */

        ltxt_SQL_stmt = " delete from " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "'";
        ltxt_SQL_stmt = ltxt_SQL_stmt + " and cod_rec_status in ('A', 'C');";
        System.out.println(" delete record query is "+ltxt_SQL_stmt);
        try {
            lo_statement = lo_connect.createStatement();
            lo_statement.executeUpdate(ltxt_SQL_stmt);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Status: " + ltxt_cod_rec_status);

        ltxt_SQL_stmt = "update " + ltxt_table_name;
        if (ltxt_cod_rec_status.equals("N") || ltxt_cod_rec_status.equals("R") || ltxt_cod_rec_status.equals("M")) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + " set cod_rec_status = 'A' ";
        } else if (ltxt_cod_rec_status.equals("X")) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + " set cod_rec_status = 'C' ";
        }
        ltxt_SQL_stmt = ltxt_SQL_stmt + "   , txt_last_checker_id = '" + gtxt_curr_user_id;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "'  , dat_last_checker = DATE_FORMAT(NOW(),'%Y-%m-%d') ";
        ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ptxt_key_col_value + "'";

        if (ltxt_cod_rec_status.equals("N")) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   and cod_rec_status = 'N';";
        } else if (ltxt_cod_rec_status.equals("R")) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   and cod_rec_status = 'R';";
        } else if (ltxt_cod_rec_status.equals("X")) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   and cod_rec_status = 'X';";
        } else if (ltxt_cod_rec_status.equals("M")) {
            ltxt_SQL_stmt = ltxt_SQL_stmt + "   and cod_rec_status = 'M';";
        }

        System.out.println("Update query is   "+ltxt_SQL_stmt);
        try {
            lo_statement = lo_connect.createStatement();
            lo_statement.executeUpdate(ltxt_SQL_stmt);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        ltxt_key_col_value = ptxt_key_col_value;

        CreateAuditTrail();

        return (0);
    }

    private int CreateAuditTrail() {

        ltxt_SQL_stmt = " insert into mst_master_table_audit_trail ";
        ltxt_SQL_stmt = ltxt_SQL_stmt + " (cod_master_code, txt_master_table_name, txt_last_maker_id, dat_last_maker, txt_last_checker_id, dat_last_checker) ";
        ltxt_SQL_stmt = ltxt_SQL_stmt + " SELECT '" + ltxt_key_col_value;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "'     , '" + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "'     , txt_last_maker_id ";
        ltxt_SQL_stmt = ltxt_SQL_stmt + "      , dat_last_maker ";
        ltxt_SQL_stmt = ltxt_SQL_stmt + "      , txt_last_checker_id ";
        ltxt_SQL_stmt = ltxt_SQL_stmt + "      , dat_last_checker ";
        ltxt_SQL_stmt = ltxt_SQL_stmt + "  from  " + ltxt_table_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " where " + ltxt_key_col_name;
        ltxt_SQL_stmt = ltxt_SQL_stmt + " = '" + ltxt_key_col_value;
        ltxt_SQL_stmt = ltxt_SQL_stmt + "' and cod_rec_status in ('A','C'); ";
        ;

        System.out.println(ltxt_SQL_stmt);
        try {

            lo_statement = lo_connect.createStatement();
            lo_statement.executeUpdate(ltxt_SQL_stmt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (0);
    }
}
