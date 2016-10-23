/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import CPSC323FinalProject.*;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author campususer
 */
public class Part2 {
    
//input
   public String input="";//"i*i$"
    private int indexOfInput=-1;
    private static String repeat;
    
    //Stack
    Stack <String> stack=new Stack<String>();
    //Table of rules
    String [][] table=
    {
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"pI;vBfGe",null,null}
            ,
        {"OX","OX","OX","OX",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {"OX","OX","OX","OX","MX","MX","MX","MX","MX","MX","MX","MX","MX","MX","","","","",null,null,"","","",null,null,null,null,null,"",null,null,""}
            ,
        {"D:C;","D:C;","D:C;","D:C;",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {"IQ","IQ","IQ","IQ",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,",D",null,"",null,null,null,null,null,null,null,null,null}
            ,
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"i",null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {"SR","SR","SR","SR",null,null,null,null,null,null,null,null,null,null,null,null,null,null,"SR",null,null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {"G","G","G","G",null,null,null,null,null,null,null,null,null,null,null,null,null,null,"G",null,null,null,null,null,null,null,"",null,null,null,null,null}
            ,
        {"A","A","A","A",null,null,null,null,null,null,null,null,null,null,null,null,null,null,"W",null,null,null,null,null,null,null,null,null,null,null,null,null}
            , 
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"w(HI);",null,null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {"","","","",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"q,",null,null,null,null,null,null,null,null}
            ,
        {"I=J;","I=J;","I=J;","I=J;",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {"KE","KE","KE","KE","KE","KE","KE","KE","KE","KE","KE","KE","KE","KE","KE","KE",null,null,null,null,null,null,null,null,null,null,null,"KE",null,null,null,null}
            ,
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,"+J","-J",null,null,null,null,null,"",null,null,null,null,null,null,"",null,null,null}
            ,
        {"FZ","FZ","FZ","FZ","FZ","FZ","FZ","FZ","FZ","FZ","FZ","FZ","FZ","FZ","FZ","FZ",null,null,null,null,null,null,null,null,null,null,null,"FZ",null,null,null,null}
            ,
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,"","","*FZ","/FZ",null,null,null,"",null,null,null,null,null,null,"",null,null,null}
            ,
        {"I","I","I","I","N","N","N","N","N","N","N","N","N","N","N","N",null,null,null,null,null,null,null,null,null,null,null,"(J)",null,null,null,null}
            ,
        {null,null,null,null,"LMY","LMY","LMY","LMY","LMY","LMY","LMY","LMY","LMY","LMY","LMY","LMY",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {null,null,null,null,"MY","MY","MY","MY","MY","MY","MY","MY","MY","MY","","","","",null,null,null,"",null,null,null,null,null,null,"",null,null,null}
            ,
        {null,null,null,null,"","","","","","","","","","","+","-",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {null,null,null,null,"0","1","2","3","4","5","6","7","8","9 ",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
            ,
        {"a","b","c","d",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
        
    };
String [] nonTers={"P","I","X","B","D","Q","C","G","R","S","W","H","A","J","E","K","Z","F","N","Y","L","M","O"};
String [] terminals={"a","b","c","d","0","1","2","3","4","5","6","7","8","9","+","-","*","/","w","i",",",";",":","q","\"","f","e","(",")","p","v","="};


public Part2(String in)
{
this.input=in;
}

private  void pushRule(String rule)
{
for(int i=rule.length()-1;i>=0;i--)
{
char ch=rule.charAt(i);
String str=String.valueOf(ch);
push(str);
}
}

    //algorithm
private boolean algorithm()
{
    boolean accepted = false;
    push(this.input.charAt(0)+"");//
    push("P");
    //Read one token from input
    
    String token=read();
    String top=null;
    
    do
    {
        top=this.pop();
        //if top is non-terminal
        if(isNonTerminal(top))
        {
        String rule=this.getRule(top, token);
        this.pushRule(rule);
        }
        else if(isTerminal(top))
        {
        if(!top.equals(token))
{
error("this token is not correct , By Grammer rule . Token : ("+token+")" , top);
}
else
{
    System.out.println("");
    System.out.println("Matching: Terminal :( "+token+" )");
    System.out.println("Stack contents:" + this.stack.toString());
    System.out.println("");
    
token =read();
if(token.equals("\""))
    {
    do
    {
     token = read(); 
    }while(!token.equals("\""));
    token="q";
 }
//top=pop();

}
        }
        else
        {
        error("Never Happens , Because top : ( "+top+" )" , top);
        }
        if(token.equals("e"))
        {
        break;
        }
        //if top is terminal
    
    }while(true);//out of the loop when $
    
    //accept
    if(token.equals("e"))
        {
         System.out.println("Input is ACCEPTED by Parser"); 
         System.out.println(" ");
         accepted = true;
        }
    else
    {
     System.out.println("Input is REJECTED by Parser");   
    }
    
    return accepted;
}

    private boolean isTerminal(String s) {
               for(int i=0;i<this.terminals.length;i++)
        {
        if(s.equals(this.terminals[i]))
        {
        return true;
        }

        }
              return false;
    }

    private boolean isNonTerminal(String s) {
        for(int i=0;i<this.nonTers.length;i++)
        {
        if(s.equals(this.nonTers[i]))
        {
        return true;
        }

        }
              return false;
    }

    private String read() {
        indexOfInput++;
        char ch=this.input.charAt(indexOfInput);
String str=String.valueOf(ch);

        return str;
    }

    private void push(String s) {
     this.stack.push(s);   
    }
        private String pop() {
   return this.stack.pop();   
    }

    private void error(String message, String token) {
        System.out.println(message);
        System.out.println("Input is REJECTED by the Parser");
        String element = "";
        for(int i = this.stack.size(); i>0;i--){
            element = this.stack.elementAt(i-1);
            if(isTerminal(token))
            {
                System.out.println(token + "is missing");
              break;
            }
            else if(isTerminal(element))
            {
              System.out.println(element + "is missing");
              break;
            }
            
        }
        throw new RuntimeException(message);
    }
    
    private String prePocessingForQuotes(String token){
        
        
        
        return "";
    }
    private String getRule(String non,String term)
    {
        
    int row=getnonTermIndex(non);
    int column=getTermIndex(term);
    String rule=this.table[row][column];
    if(rule==null)
    {
    error("There is no Rule by this , Non-Terminal("+non+") ,Terminal("+term+") " , "");
    }
    return rule;
    }

    private int getnonTermIndex(String non) {
       for(int i=0;i<this.nonTers.length;i++)
       {
       if(non.equals(this.nonTers[i]))
       {
       return i;
       }
       }
       error(non +" is not NonTerminal" , non);
       return -1;
    }

    private int getTermIndex(String term) {
              for(int i=0;i<this.terminals.length;i++)
       {
       if(term.equals(this.terminals[i]))
       {
       return i;
       }
       }
       error(term +" is not Terminal" , term);
       return -1;
    }
    
    public static void main(String args[]){
        
        System.out.println("********** PART 2 - Parsing the input code by LL1 Parser *********** ");
        boolean accepted;
        System.out.println("Expession:");
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        Part2 parser=new Part2(expression);
        accepted = parser.algorithm();
        
        //return accepted;
    }

}
/*
 
 */

