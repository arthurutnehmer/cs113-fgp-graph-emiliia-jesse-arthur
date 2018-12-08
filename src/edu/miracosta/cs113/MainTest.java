package edu.miracosta.cs113;

public class MainTest
{
    public static void main(String[]args)
    {
        ListGraph testGraph = new ListGraph(12, false);
        testGraph.insert(new Edge(0, 1, 0.6));
        testGraph.insert(new Edge(0, 6, 0.2));
        testGraph.insert(new Edge(0, 5, 0.1));
        testGraph.insert(new Edge(1, 2, 0.4));
        testGraph.insert(new Edge(2, 6, 0.7));
        testGraph.insert(new Edge(2, 8, 0.3));
        testGraph.insert(new Edge(2, 3, 0.2));
        testGraph.insert(new Edge(3, 9, 0.9));
        testGraph.insert(new Edge(3, 8, 0.2));
        testGraph.insert(new Edge(4, 5, 0.1));
        testGraph.insert(new Edge(6, 7, 0.2));
        testGraph.insert(new Edge(7, 8, 0.2));
    }

}
