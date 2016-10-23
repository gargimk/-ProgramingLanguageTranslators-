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
import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author campususer
 */
public class Part3 {

    public void converCodeToHLLang() {

        String line = null;
        String convertedCode = "#include <iostream> \n using namespace std; \n int main() \n { \n";
       
        try {
            System.out.println("********** PART 3 - Converting the code to High Level Language(C++) *********** ");
            //to read the file
            FileReader fileReader = new FileReader("//Users//campususer//Documents//finalv2.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            boolean flag = false;
            while ((line = bufferedReader.readLine()) != null) {

                if (line.contains("program") || line.contains("begin")) {
                    continue;
                }
                if (line.contains("var")) {
                    flag = true;
                    continue;
                }
                if (flag) {
                    convertedCode = convertedCode + convertCode(line, true);
                    flag = false;
                } else {
                    convertedCode += convertCode(line, false);
                    flag = false;
                }

            }
            
            bufferedReader.close();
            
            System.out.print("The code is successfully converted. The C++ version of code is: \n");
            System.out.println(" ");
            System.out.print(convertedCode);

        } catch (FileNotFoundException e) {
            System.out.println(
                    "Unable to open file 'data.txt'");
        } catch (IOException e) {
            Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //logic to convert code
    private String convertCode(String line, boolean flag) {
        String buffer = "";

        if (flag) {
            String[] tokens = line.split(":");
            if (tokens[1].trim().equalsIgnoreCase("INTEGER ;")) {
                buffer += "int ";
            }
            buffer += tokens[0] + ";";

        } else {
            if (line.contains("end")) {
                buffer = "return 0;\n}";
            } else if (line.contains("write")) {
                String[] tokens = line.split("\\(|\\)");
                buffer += "cout<<";
                String[] subtokens = tokens[1].split(",");
                if (subtokens.length > 1) {
                    buffer += subtokens[0] + "<<" + subtokens[1] + ";";
                } else {
                    buffer += subtokens[0] + ";";
                }

            } else {
                buffer += line;
            }
        }

        buffer += "\n";

        return buffer;
    }

}
