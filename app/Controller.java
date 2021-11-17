/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import java.util.ArrayList;

/**
 *
 * @author Ken
 */

public class Controller {

  
    public static void main(String[] args) {
       
       Data data = new Data();


         data.dispayInfo();
       //To create name
       
       String First_Name = "Susan";
       String Last_name = "Holiday"; 
       
      Portfolio portfolio = new Portfolio(First_Name,Last_name,100000);
       
       
       portfolio.displayInfo();
   
        
        
       System.out.println("");
       System.out.println("===========================================");
       System.out.println("Data Stats");
       System.out.println("===========================================");
      
        
        
       double Aver =Analyzer.average(data.getSp500Data());

        System.out.printf("Average SP500:\t %-10.2f \n", Aver);
        
        // SP500 value
        
        double Var = Analyzer.variance(data.getSp500Data());
        
        System.out.printf("Variance SP500:\t %-10.2f \n", Var);

        
 
        // calculate the variance  of the data's 
        // SP500 values
        double stdDev = Analyzer.standardDeviation(data.getSp500Data());
       System.out.printf("Std Dev SP 500:\t %-10.2f \n",stdDev); 

 
        
        //Gold exchnage data
        
        double Aver_gold =Analyzer.average(data.getGoldData());

        System.out.printf("Average Gold:\t %-10.2f \n", Aver_gold);
        
        
       double Var_gold = Analyzer.variance(data.getGoldData());
        
        System.out.printf("Variance Gold:\t %-10.2f \n", Var_gold);
        
        double stdDev_gold = Analyzer.standardDeviation(data.getGoldData());
       System.out.printf("Std Dev Gold:\t %-10.2f \n",stdDev_gold); 
       
       //Libor exchange data
       
       double Aver_libor =Analyzer.average(data.getLiborData());

        System.out.printf("Average Libor:\t %-10.2f \n", Aver_libor);
        
        
       double Var_libor = Analyzer.variance(data.getLiborData());
        
        System.out.printf("Variance Libor:\t %-10.2f \n", Var_libor);
        
        double stdDev_libor = Analyzer.standardDeviation(data.getLiborData());
       System.out.printf("Std Dev Libor:\t %-10.2f \n",stdDev_libor);
       
     

          System.out.println("");
          System.out.println("===========================================");
          System.out.println("Correlations Table");
          System.out.println("===========================================");
          System.out.println("\t SP500 \t Gold \t Libor");
      
   
        //calculate the correlation
        double SP500_Correlation =    Analyzer.correlation(data.getSp500Data(), data.getSp500Data());
        
     System.out.printf("SP500: %6.2f \n",SP500_Correlation); 
   
     double Gold_Correlation =    Analyzer.correlation(data.getGoldData(), data.getSp500Data());
     double Gold_Correlation2 =    Analyzer.correlation(data.getGoldData(), data.getGoldData());
        
     System.out.printf("Gold: %7.2f %7.2f \n",Gold_Correlation, Gold_Correlation2);
     
     double Libor_Correlation =    Analyzer.correlation(data.getLiborData(), data.getSp500Data());
     double Libor_Correlation2 =    Analyzer.correlation(data.getLiborData(), data.getGoldData());
     double Libor_Correlation3 =    Analyzer.correlation(data.getLiborData(), data.getLiborData());
        
     System.out.printf("Libor: %6.2f %7.2f %7.2f \n ",Libor_Correlation,Libor_Correlation2,Libor_Correlation3);

           System.out.println("");
           System.out.println("==============================================");
           System.out.println("Calibrate Multiplier ");
           System.out.println("==============================================");
      
        
        double multiplierSP500 = calibrateMultiplier(data.getDates(), data.getSp500Data(), 45, 50);
        
        double multiplierGold = calibrateMultiplier(data.getDates(), data.getGoldData(), 30, 40);
   
        
      double multiplierLibor = calibrateMultiplier(data.getDates(), data.getLiborData(), 20, 40);
      
      
System.out.printf("Multiplier500:\t %-10.4f \nMultiplierGold:\t %-10.4f \nMultiplierLibor: %-10.4f ",multiplierSP500,multiplierGold, multiplierLibor);

    System.out.println("");
    System.out.println("==============================================");
    System.out.println("Trade Tricker ");
    System.out.println("==============================================");      
   double tolaranceSP500 = Analyzer.standardDeviation(data.getSp500Data()) * multiplierSP500;
   double tolaranceGold = Analyzer.standardDeviation(data.getGoldData()) * multiplierGold;
   double tolaranceLibor = Analyzer.standardDeviation(data.getLiborData()) * multiplierLibor;
   
       // data
       // tolaranceSP500
       // tolaranceGold
       // tolaranceLibor
       Analyzer.tradeTracker(data, tolaranceSP500, tolaranceGold, tolaranceLibor);
        
    }
    
    
    public static double calibrateMultiplier(ArrayList<String> dates, ArrayList<Double> values, int lowCount, int highCount){
        
        double multiplier = 1.0;
        boolean finished = false;
      
        
        while(finished == false)
        {  
            double tolerance =  Analyzer.standardDeviation(values)*multiplier;
            int matches = Analyzer.findLargeMovements(values,tolerance);
             
           if(matches<=highCount && matches>=lowCount)
           {
              finished  = true;  
               
           }
           if(matches<lowCount)
           {
               multiplier *= 0.5;
               
           }
           if(matches>highCount)
           {
               multiplier *= 1.5;
               
           } 
        }
       
        return multiplier;
          
    }
    
}
