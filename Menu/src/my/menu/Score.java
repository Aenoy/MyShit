/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.menu;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aenoy
 */
public class Score {
    
    String player;
    int  score;
    
    public Score(String name){
        
        this.player = name;
    }
    
    public void setScore(int score){
        
        this.score = score;
    }
    
    public int getScore(){
        
        return this.score;
    }
    
    public String getPlayer(){
        
        return this.player;
    }
    
    //Returns an Arraylist of all the socres in the CSV file
    public static ArrayList<Score> getHighScores(){
        
        ArrayList<Score> highScores = new ArrayList<Score>();
        Score score;
        
        try{
        
        FileReader Reader = new FileReader("UsersInfo.txt");
        
        Scanner scan = new Scanner(Reader);
        
        while(scan.hasNextLine()){
           
           String [] userInfo;
            
           userInfo = scan.nextLine().split(",\\s+");
           
           //Creates a score object and sets its player field
           score = new Score(userInfo[0]);
           
           //This adds every scores of the line to the arraylist
           for(int i=2; i< userInfo.length; i++){
               
              score.setScore(Integer.parseInt(userInfo[i]));
               
              highScores.add(score);
               
           }
        }
        Reader.close();
        
        } catch(IOException e){
            
            System.out.println("Error reading from UserInfo" + e);            
            
        }
        
        return highScores;
        
    }
    
    //This returns a sorted list from smaller to bigger scores
    public static ArrayList<Score> mergeSortList(ArrayList<Score> scores){
        
        int middle;
        ArrayList<Score> left = new ArrayList<Score>();
        ArrayList<Score> right = new ArrayList<Score>();
            
        if (scores.size() == 1)
            return scores;
        
        middle = (scores.size()-1)/2;
        
        for(int i=0; i<= middle; i++){
            left.add(scores.get(i));
        }
        
        for(int j = middle+1; j< scores.size(); j++){
            
            right.add(scores.get(j));
        }
        
        left = mergeSortList(left);
        right= mergeSortList(right);
        
        return merge(left,right);
    }
    
   //This merges two lists
   public static ArrayList<Score> merge(ArrayList<Score> left, ArrayList<Score> right){
        
        ArrayList<Score> sortedList= new ArrayList<Score>();
        
        while(!left.isEmpty() && !right.isEmpty()){
                
                if(left.get(0).getScore() < right.get(0).getScore())
                    sortedList.add(left.remove(0));
               
                else
                    sortedList.add(right.remove(0));
            }
        
        while(!left.isEmpty()){
        	sortedList.add(left.remove(0));
        }
        
        while(!right.isEmpty()){
        	
        	sortedList.add(right.remove(0));
        }
        
        return sortedList;
    
   }
}
