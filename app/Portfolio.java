
package app;

import java.util.ArrayList;

public class Portfolio {
    
    private static int counter = 1000;
    
    private String firstName;
    private String lastName;
    private String id;
    
    ArrayList positions;
    
    public Portfolio(String firstName, String lastName, double cashAmount){
        positions = new ArrayList<>();
        
        this.firstName = firstName;
        this.lastName = lastName;
        
            Cash cash = new Cash(cashAmount);
        
        positions.add(cash);
       
        id = firstName.substring(0,2) + "-" + lastName.substring(0,2).toUpperCase()+ "-" + counter;
        counter++;
                
    }
    
    
    public void displayInfo(){
        System.out.println("");
        System.out.println("==================================================");
        System.out.println("Portfolio Information");
        System.out.println("==================================================");
        System.out.printf("Name:\t %s, %s \n", firstName, lastName);
        System.out.printf("ID:\t %s \n", id);
        
    
        for(Object obj:positions){
            if(obj instanceof Cash){
                System.out.printf("Cash:\t %-10.2f \n", ((Cash) obj).getValue() );
            }//end if
        }//end for
        
    }
    
}
