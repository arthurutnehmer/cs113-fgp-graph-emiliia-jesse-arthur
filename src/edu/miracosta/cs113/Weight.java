package edu.miracosta.cs113;

/**
 * Weight class for use as a variable that can be controlled. In this case an infection rate that has an effect on the weight
 * between two Nodes.
 *
 * @author Jesse Wolf
 * @author Arthur Utnehmer
 * @auther Emiliia Dyrenkova
 */
public class Weight {
    protected static double infectionRate;

    /**
     * Method used to set the infection rate.
     * @param newRate double passed in from user input within Controller class.
     */
    public static void setInfectionRate(double newRate) {
        infectionRate = newRate;
    }

    /**
     * Method used to add a multiplier to our current weight dependent upon what range it current is.
     * @param currentWeight double representing the current weight of the Edge.
     * @return double corresponding to the new weight
     */
    public static double calculateNewWeight(double currentWeight) {
        if(currentWeight < .25) {
            return ((infectionRate/2)*currentWeight)/10;
        }
        else if(currentWeight < .5) {
            return ((infectionRate/3)*currentWeight)/10;
        }
        else if(currentWeight < .75) {
            return ((infectionRate/4)*currentWeight)/10;
        }
        else {
            return ((infectionRate/5)*currentWeight)/10;
        }
    }
}
