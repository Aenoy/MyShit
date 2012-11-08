/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.menu;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author Aenoy
 */
public class User {
    
    String name;
    int [] scores;
    
    public User(String name){
        
        this.name = name;
    }
    
    public static boolean isValidAccount(String username){
        
        try{
        
        FileReader Reader = new FileReader("UsersInfo.txt");
        
        Scanner scan = new Scanner(Reader);
        while(scan.hasNextLine()){
            if(scan.hasNext(username + ","))
                return true;
            else
                scan.nextLine();
        }
        Reader.close();
        
        } catch(IOException e){
            
            System.out.println("Error reading from UsersInfo" + e);            
            
        }
        
        return false;
    }
    
    public static int isValidUser(String username, String password){
        
        String [] userInfo;
        
         try{
        
        FileReader Reader = new FileReader("UsersInfo.txt");
        
        Scanner scan = new Scanner(Reader);
        while(scan.hasNextLine()){
             userInfo = scan.nextLine().split(",\\s+");
            if(username.equals(userInfo[0])){         
                if(password.equals(userInfo[1]))
                return 1;
                
                else  
                   return 0;
            }                   
            else
                continue;
        }
        Reader.close();
        
        } catch(IOException e){
            
            System.out.println("Error reading from UserInfo" + e);            
            
        }
        
        return -1;
    }
    
    public static boolean isValidRegistration(String username, String password){
               
          if(!isValidAccount(username) && Pattern.matches("[a-zA-Z_0-9]+",username) && 
                  Pattern.matches("[a-zA-Z_0-9]+",password) )
              return true;
          
          else   
        return false;
    }
    
    public static void addUser(String username, String password){
        
        try{
            
        Writer output;
        output = new BufferedWriter(new FileWriter("UsersInfo.txt", true));
        output.append("\r\n" + username + ", " + password );
        
        output.close();
        
        } catch(IOException e){
                   
             System.out.println("Error reading from UserInfo" + e);
            
        }
    }
}