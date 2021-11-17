package app;

import java.util.ArrayList;
import java.lang.Math;


public class Analyzer {
    
    public static double average(ArrayList<Double> data){
        double average = 0.0;
        
        double sum = 0.0;
        
        for(double index: data)
        {
            sum  +=index;
        }   
       average = sum/data.size();
        
        return average;
    }
    
    
    public static double variance(ArrayList<Double> data){
        
      
        double average = average(data);
        double sum_initial = 0.0;
        double sum = 0.0;
        double variance = 0.0;
        
        
        for(double value: data){
         sum += Math.pow(value-average, 2);   
        }
        variance = sum/(data.size()-1);    
     
        return variance;
    }
    
    public static double standardDeviation(ArrayList<Double> data){
        
        double stdDev = 0.0;
        
       stdDev = Math.sqrt(variance(data));
          
        return stdDev;
        
    }

    
    public static double correlation(ArrayList<Double> data1, ArrayList<Double> data2){
    
        double correlation = 0.0 ;
        double avgData1 = average(data1);
        double avgData2 = average(data2);
   
        double sum = 0.0;
        int index = 0;
        int numberOfPrices = data1.size();
        
        //SP500 correlation
        
        for(index = 0; index< numberOfPrices; index++)
        {
          sum = sum + ((data1.get(index)-avgData1)/standardDeviation(data1))*((data2.get(index)-avgData2)/standardDeviation(data2));   
        }

         correlation = (1/((double)numberOfPrices-1))*sum;
         
         return correlation;
       
    
}
    
    
    
    public static int findLargeMovements(ArrayList<Double> data, double tolarance){
        
        int counter = 0;
        for(int index = 1; index<data.size(); index++)
        {
            if(tolarance<Math.abs(data.get(index)-data.get(index-1)))
            {
                counter++;
            }
 
    }
                return counter;

    } 
    
    public static void tradeTracker(Data data, 
                                   double tolaranceSP500, 
                                   double tolaranceGold, 
                                   double tolaranceLibor
                                   ){
        
        int counter = 0;
        
        ArrayList<Integer> sp500Matches = new ArrayList<>();
        ArrayList<Integer> goldMatches = new ArrayList<>();
        ArrayList<Integer> liborMatches = new ArrayList<>();
        
        
        for(int i=1; i< data.getDates().size(); i++){
            
            if(tolaranceSP500 <= (Math.abs( data.getSp500Data().get(i-1) - data.getSp500Data().get(i)  ))){
               sp500Matches.add(i);
               
            }
            
            if(tolaranceGold <= (Math.abs( data.getGoldData().get(i-1) - data.getGoldData().get(i)  ))){
               goldMatches.add(i);
            } 
            
            if(tolaranceLibor <= (Math.abs( data.getLiborData().get(i-1) - data.getLiborData().get(i)  ))){
               liborMatches.add(i);
            } 
        }
        

        
        
        displayMatchInfo(sp500Matches, "SP500");
        displayMatchInfo(goldMatches, "Gold");
        displayMatchInfo(liborMatches, "Libor");
        
        displayTradeExecutions(data,sp500Matches,goldMatches,liborMatches);
        
        
       
    }
    
    private static void displayMatchInfo(ArrayList<Integer> matches, String header){
        System.out.println("===================================================");
        System.out.println(header);
        System.out.println("===================================================");
        System.out.printf("Number of matches: %d \n", matches.size());
        
        for(int i=0; i<matches.size(); i++){
            if(i < matches.size() -1){
                System.out.print(matches.get(i) +", ");
            }else{
                System.out.print(matches.get(i) +"\n");
            }
        }
        System.out.println("");
        
    }
    
    private static void displayTradeExecutions(Data data,
                                               ArrayList<Integer> sp500Matches,
                                               ArrayList<Integer> goldMatches,
                                               ArrayList<Integer> liborMatches){
        
        int counter = 0;
        
        System.out.println("===================================================");
        System.out.println("Trade Executions");
        System.out.println("===================================================");
        System.out.printf("%-5s \t %-10s \t %-10s \t %-10s \t %-10s \n", "Index", "Date", "SP500", "Gold", "Libor");
        
        
        for(int i = 0; i<sp500Matches.size(); i++){
            int index = sp500Matches.get(i);
    
            if(goldMatches.contains(index) && liborMatches.contains(index)){
                System.out.printf("%-5d \t %-10s\t %-10.2f \t %-10.2f \t %-10.2f \n", index,
                                                                               data.getDates().get(index), 
                                                                               data.getSp500Data().get(index),
                                                                               data.getGoldData().get(index),
                                                                               data.getLiborData().get(index)   );
                counter++;
            }//end if
        }//end for
        
        System.out.printf("Number of trade executions: %d \n", counter);
        
    
}
            
    
}//
