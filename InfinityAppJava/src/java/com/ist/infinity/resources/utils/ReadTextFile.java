/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author pavani
 */
public class ReadTextFile {

    /**
     * Fetch the entire contents of a text file, and return it in a String.
     * This style of implementation does not throw Exceptions to the caller.
     *
     * @param aFile is a file which already exists and can be read.
     */
    static public String getUsernamePasswordFromFlatFile(File passwordFile) {
        StringBuilder contents = new StringBuilder();

        try {
            BufferedReader input = new BufferedReader(new FileReader(passwordFile));
            try {
                String line = null; //not declared within while loop

                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(",");
                }
            } finally {
                input.close();
            }

           
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return contents.toString();
    }
}
