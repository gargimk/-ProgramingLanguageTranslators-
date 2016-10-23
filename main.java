/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CPSC323FinalProject;

/**
 *
 * @author campususer
 */
public class main {
    static Part1 formatFile;
    static PreProcessor preProcess;
    static Part2 parser;
    static Part3 convertCode;
    static boolean accepted; 
    
    public static void main(String args[])
    {
       formatFile = new Part1();
       formatFile.formatCode("//Users//campususer//Documents//finalv1.txt");
       
       preProcess = new PreProcessor();
       String proceesedInput = preProcess.preProcessor();
       System.out.println(proceesedInput);
       parser = new Part2("");
       accepted = parser.LL_Parser(proceesedInput);
       
       convertCode = new Part3();
       if(accepted)
       {
           convertCode.converCodeToHLLang();
       }
       else
       {
           
       }
       
       
    }
    
}
