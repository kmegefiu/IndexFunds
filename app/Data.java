package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Data {
    
    private ArrayList<String> dates = new ArrayList<String>();
    private ArrayList<Double> sp500Data = new ArrayList<Double>();
    private ArrayList<Double> goldData = new ArrayList<Double>();
    private ArrayList<Double> liborData = new ArrayList<Double>();
            
    public Data(){
        String fileName = "src/data/Project1-Data.txt";

        String line = null;
        
        try {
            FileReader fileReader =  new FileReader(fileName);
            
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
           
            int lineCounter = 1;        
            String delims = "[,]";     
         
            while((line = bufferedReader.readLine()) != null) {

                if(lineCounter != 1){
                                   
                    String[] tokens = line.split(delims);
                    dates.add(tokens[0]);
                    sp500Data.add(Double.parseDouble(tokens[1]));
                    goldData.add(Double.parseDouble(tokens[2]));
                    liborData.add(Double.parseDouble(tokens[3]));  
                }
                
                lineCounter++;
            }// end while   
            bufferedReader.close(); 

           
        } catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        } catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
      
    }
    
    public ArrayList<String> getDates() {
        return dates;
    }
    
    public ArrayList<Double> getSp500Data() {
        return sp500Data;
    }
    
    public ArrayList<Double> getGoldData() {
        return goldData;
    }
    
    public ArrayList<Double> getLiborData() {
        return liborData;
    }
    
    public void dispayInfo(){
        
        System.out.println("==================================================");
        System.out.println("Data ");
        System.out.println("==================================================");
        System.out.printf("%-7s %-13s %-7s  %-8s %-10s \n", "Index",
                                                          "Dates",
                                                           "SP500",
                                                           "Gold",
                                                           "Libor");
        System.out.println("---------------------------------------------------");
        

        for(int i=0; i < dates.size(); i++){
            System.out.printf("%-7s %10s %10.2f  %7.2f %7.4f \n", i,
                                                                 dates.get(i),
                                                                 sp500Data.get(i),
                                                                 goldData.get(i),
                                                                 liborData.get(i));
            
        }
    }
    
}
