/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ist.infinity.resources.DAO;

import com.ist.infinity.resources.masters.MasterPrefixesActionForm;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author istpl
 */
public class Prefixes extends MasterTable {

    // the MountainBike subclass adds one field

    // the MountainBike subclass has one constructor
    public Prefixes(String TableName, String KeyColName, String DescrColName, char Mode,String username,String code,String desc) {
        super(TableName, KeyColName, DescrColName,username);
      /*  MasterPrefixesActionForm mpaf = (MasterPrefixesActionForm) form;
        String cod_prefix = mpaf.getCodPrefix();
        String txtPrefixDesc = mpaf.getTxtPrefixDesc();
        System.out.println(" text prefix desc"+txtPrefixDesc); */
        switch (Mode) {
          case 'N': AddNew (code, desc); break;
          case 'M': Modify (code, desc); break;
          case 'C': Close (code); break;
          case 'R': Reopen (code); break;
          case 'A': Authorize (code); break;
          //case 'G': GetRecord (cod_prefix); break;
        }
    }

  private int Validate() {
    return (1);
  }
}