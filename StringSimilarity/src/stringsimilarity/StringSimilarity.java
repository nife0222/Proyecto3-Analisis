/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stringsimilarity;

/**
 *
 * @author Nicolas
 */
public class StringSimilarity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int distance = LevenshteinDistance.Distance("Ricardo Arjona", "333Ricafsadfardo ARJ");
        int distance2 = LevenshteinDistance.Distance("Carlos Vives", "333Ricafsadfardo ARJ");
        int distance3 = LevenshteinDistance.Distance("Enrique Iglesias", "333Ricafsadfardo ARJ");
        int distance4 = LevenshteinDistance.Distance("Calle 13", "333Ricafsadfardo ARJ");
        
        System.out.println(distance +" "+ distance2 +" "+ distance3 +" "+ distance4);
    }
    
}
