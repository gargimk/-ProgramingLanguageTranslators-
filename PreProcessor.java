/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CPSC323FinalProject;

import CPSC323FinalProject.Part1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author campususer
 */
public class PreProcessor {

    static List<String> reservewords = new ArrayList<String>();

    public String preProcessor() {

        String line = null;
        String verifyStr = "";
        String formattedString = "";
        try {
            System.out.println("********** Pre-processing the code for parser input *********** ");
        
            //to read the file
            FileReader fileReader = new FileReader("//Users//campususer//Documents//finalv2.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
             
            while ((line = bufferedReader.readLine()) != null) {
                verifyStr+=line;
     
                String newLine = formatString(line);
                formattedString = formattedString+newLine;
                formattedString = formattedString.replaceAll(" ", "");
                
            }
            verifyCode(verifyStr);
            bufferedReader.close();
            System.out.println("Code successfully pre-processed.");
            System.out.println(" ");
            
        } catch (FileNotFoundException e) {
            System.out.println(
                    "Unable to open file 'data.txt'");
        } catch (IOException e) {
            Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return formattedString;
    }

    //logic to format the input string
    private String formatString(String line) {
        String buffer = " ";
         ArrayList<String> inputStatmentList = new ArrayList<String>(Arrays.asList(line.split(" ")));
         intializeReserveWords();
         for(String token: inputStatmentList)
         {
             token = token.trim();
             if(reservewords.contains(token.toLowerCase()))
        {
            if(token.equals("begin"))
            {
                buffer = buffer+"f";
            }
            else
            {
                buffer= buffer+String.valueOf(token.charAt(0)).toLowerCase();
            }
            
        }
         else if(token.matches("[A-Za-z0-9+\\-*/<=>]"))
        {
            buffer = buffer+token;
        }
        else if(token.matches("([a-z][a-z\\d]*\\.)*[a-z][a-z\\d]*") && !(reservewords.contains(token)))
            {
                 buffer = buffer+token;
            }
             else if(token.matches("[^A-Za-z0-9+\\-*/<=>.]"))
            {
               buffer = buffer+token;
            }
         }
        
        return buffer;
    }
    
    private void intializeReserveWords()
    {
        reservewords.add("integer");
        reservewords.add("end");
        reservewords.add("begin");
        reservewords.add("write");
        reservewords.add("program");
        reservewords.add("var");    
    }
    
    private void verifyCode(String inStr)
    {
        for(String reserveWord:reservewords)
        {
            if(!inStr.toLowerCase().contains(reserveWord))
            {
                System.out.println(reserveWord + " is expected");
                break;
            }
        }
        
    }
}


