/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.utils;

/**
 *
 * @author kareem
 */
public class SessionVariables {

    public static int priority = 0;
    public static String username = "";
    public static String currentdate = "";
    public static String sysdate = "";
    public static String password = "";

    public static String getCurrentdate() {
        return currentdate;
    }

    public static void setCurrentdate(String currentdate) {
        SessionVariables.currentdate = currentdate;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SessionVariables.password = password;
    }

   

    public static String getCurrentDate() {
        return currentdate;
    }

    public static void setCurrentDate(String currentdate) {
        SessionVariables.currentdate = currentdate;
    }

    public static int getPriority() {
        return priority;
    }

    public static void setPriority(int priority) {
        SessionVariables.priority = priority;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionVariables.username = username;
    }

    public static String getSysdate() {
        return sysdate;
    }

    public static void setSysdate(String sysdate) {
        SessionVariables.sysdate = sysdate;
    }
}
