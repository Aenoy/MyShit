/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.menu;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.lang.Integer;

/**
 *
 * @author Aenoy
 */
public class User {
    
    //Checks if the username is already taken or not by scanning every line of the CSV file 
    //and comparing it to the usernamer the player typed in.
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
    
    //Checks if the username string matches the password string stored in the
    //CSV file. This returns 1 if the player type in a correct username with its coresponding password, 0 if the password
    //does not corespond to the username, -1 a wrong username has been typed.
    public static int isValidUser(String username, String password){
        
        String [] userInfo;
        
         try{
        
        FileReader Reader = new FileReader("UsersInfo.txt");
        
        Scanner scan = new Scanner(Reader);
        while(scan.hasNextLine()){
            
            //This tokenizes the line of strings according to ", " and stores the tokens
            //in an array. The first and second element of the array will be the username and the password repectively.
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
    
    //This checks if the username has already taken and that the username and password only contains 
    //alphanumeric charaters
    public static boolean isValidRegistration(String username, String password){
               
          if(!isValidAccount(username) && Pattern.matches("[a-zA-Z_0-9]+",username) && 
                  Pattern.matches("[a-zA-Z_0-9]+",password) )
              return true;
          
          else   
        return false;
    }
    
    //This appends the newly created username and passwrd to the CSV file
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