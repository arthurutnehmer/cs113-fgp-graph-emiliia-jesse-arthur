package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import static org.junit.Assert.assertEquals;

/**
 * Cities Graph Tester. Various J Unit tests to test Cities Graph Methods.
 */
public class InfectionGraphTest
{
    /**
     * An object of Cities Graph class.
     */
    private InfectionGraph infectionGraphTest;

    /**
     * Dijkstra's Outcomes on our default files.
     **/
    private static final int[][] PREDECESSORS = {
            {0,0,6,8,5,0,0,6,7,3},{1,0,1,2,5,0,0,8,2,3},{6,2,1,2,5,0,2,8,2,3},
            {6,2,3,2,5,0,7,8,3,3},{5,0,6,8,5,4,0,6,7,3},{5,0,6,8,5,4,0,6,7,3},
            {6,0,6,8,5,0,0,6,7,3},{6,2,8,8,5,0,7,6,7,3},{6,2,8,8,5,0,7,8,7,3}};

    private static final double[][] DISTANCES = {
            {0.00,0.60,0.90,0.80,0.20,0.10,0.20,0.40,0.60,1.70}, {0.60,0.60,0.40,0.60,0.80,0.70,0.80,0.90,0.70,1.50},
            {0.90,0.40,0.40,0.20,1.10,1.00,0.70,0.50,0.30,1.10}, {0.80,0.60,0.20,0.20,1.00,0.90,0.60,0.40,0.20,0.90},
            {0.20,0.80,1.10,1.00,1.00,0.10,0.40,0.60,0.80,1.90}, {0.10,0.70,1.00,0.90,0.10,0.10,0.30,0.50,0.70,1.80},
            {0.20,0.80,0.70,0.60,0.40,0.30,0.30,0.20,0.40,1.50}, {0.40,0.90,0.50,0.40,0.60,0.50,0.20,0.20,0.20,1.30},
            {0.60,0.70,0.30,0.20,0.80,0.70,0.40,0.20,0.20,1.10}, {1.70,1.50,1.10,0.90,1.90,1.80,1.50,1.30,1.10,1.10}};

    /**
     * This function is executed every single time before each test runs.
     */
    @Before
    public void setup()
    {
        infectionGraphTest = new InfectionGraph();
    }

    /**
     * Test to see if dijsktras is working on our predefined file names.
     * Note: Had to format the doubles in order to use assertEquals.
     */
    @Test
    public void testDijsktrasOutcomes()
    {
        NumberFormat formatter = new DecimalFormat("#0.00");
        int[] testPredecessors;
        double[] testDistances;
        for(int row = 0; row < PREDECESSORS.length;row++)
        {
            infectionGraphTest.runDijkstras(row);
            testPredecessors = infectionGraphTest.getPredecessors();
            testDistances = infectionGraphTest.getDistances();
            int[] expectedPredecessors = PREDECESSORS[row];
            double[] expectedDistances = DISTANCES[row];
            for(int p = 0; p < testPredecessors.length; p++)
            {
                assertEquals("Expected Predecessors do not match.", testPredecessors[p], expectedPredecessors[p]);
            }
            //Had to get fancy with assertEquals for a double. Checking two doubles is not supported so formatted it before checking equality.
            for(int d = 0; d < testDistances.length; d++)
            {
                assertEquals("Expected Destination does not match.", formatter.format(testDistances[d]),formatter.format(expectedDistances[d]));
            }
        }
    }
}
