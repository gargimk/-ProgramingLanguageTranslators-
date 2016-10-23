/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CPSC323FinalProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author campususer
 */
public class Part1 {

    public void formatCode(String inputFile) {

        String line = null;

        try {
            System.out.println("********** PART 1 - Converting input code file to new version *********** ");
            //to read the file
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //to create new file 
            File file = new File("//Users//campususer//Documents//finalv2.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            //to write into new file
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //formatting input file
            while ((line = bufferedReader.readLine()) != null) {
                String newLine = formatString(line);

                if ((!newLine.isEmpty()) && (!newLine.equals(" "))) {
                    boolean f = false;
                    f = newLine.contains(";");
                    String[] token = newLine.split(";");
                    
                    for (String t : token) {

                        if (!t.trim().isEmpty()) {
                            bufferedWriter.write(t, 0, t.length());
                            if (f) {
                                bufferedWriter.write(";");
                            }
                            bufferedWriter.write("\n");
                        }

                    }

                } else {
                    continue;
                }
            }

            bufferedReader.close();
            bufferedWriter.close();
            System.out.println("Input file formatted successfully.");
            System.out.println(" ");
            
        } catch (FileNotFoundException e) {
            System.out.println(
                    "Unable to open file 'data.txt'");
        } catch (IOException e) {
            Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //logic to format the input string
    private String formatString(String line) {
        String newLine = " ";
        boolean sameLine = false;

        line = removeComments(line, sameLine);
        line = line.trim();

        ArrayList<String> splitArray = new ArrayList<String>(Arrays.asList(line.split(" ")));
        ArrayList<String> strArray = new ArrayList<String>();
        for (String token : splitArray) {
            if (!(token.equals(""))) {
                token = token.trim();

                String tempToken = token;
                String buffer = token;
                String tempStr1 = "";
                String tempStr2 = "";

                do {

                    for (int i = 0; i < tempToken.length(); i++) {

                        if ((tempToken.charAt(i) >= 33 && tempToken.charAt(i) <= 47) || (tempToken.charAt(i) >= 58 && tempToken.charAt(i) <= 64)) {
                            tempStr1 = tempToken.substring(0, i);
                            strArray.add(tempStr1);
                            tempStr2 = tempToken.substring(i, i + 1);
                            strArray.add(tempStr2);
                            tempToken = tempToken.substring(i + 1, tempToken.length());
                            buffer = tempToken;
                            i = -1;
                        }

                    }

                    if (tempToken.length() == buffer.length()) {
                        if (containsSpecialChar(tempToken)) {
                            continue;
                        } else {
                            break;
                        }
                    }

                } while (tempToken.length() != 0);
                strArray.add(tempToken);
            }

        }
        for (String str : strArray) {
            if (!(str.isEmpty())) {
                newLine = newLine.concat(str + " ");
                if ((str.equals(";"))) {

                }
            }
        }
        newLine.replaceAll(";", ";\n");
        return newLine;
    }

    private boolean containsSpecialChar(String input) {
        boolean flag = false;
        for (int i = 0; i < input.length(); i++) {
            String ch = String.valueOf(input.charAt(i));
            if (ch.matches("[^A-Za-z0-9+\\-*/<=>]")) {
                flag = true;
            }
        }
        return flag;

    }

   //logic to remove comments
    private String removeComments(String sCurrentLine, boolean sameLine) {

        boolean commentStarted = false;

        //int commentLines = 0, codeLines =0;
        if (sCurrentLine.contains("(*")) {
            commentStarted = true;
            if (sCurrentLine.contains("*)")) {
                commentStarted = false;
                String bufferStr = sCurrentLine;
                String beforeComment = sCurrentLine.substring(0, sCurrentLine.indexOf("(*")).trim();
                String afterComment = bufferStr.substring(sCurrentLine.indexOf("*)") + 2).trim();
                sCurrentLine = beforeComment + afterComment;
            } else {
                sCurrentLine = sCurrentLine.substring(0, sCurrentLine.indexOf("(*")).trim();
            }

        } else if (sCurrentLine.contains("*)")) {
            commentStarted = false;
            if (sCurrentLine.endsWith("*)")) {
                sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("*)") + 2).trim();
            } else {
                sCurrentLine = sCurrentLine.substring(0, sCurrentLine.indexOf("*)") + 2).trim();
            }
        } else {

            commentStarted = false;

        }

        return sCurrentLine;
    }

}
