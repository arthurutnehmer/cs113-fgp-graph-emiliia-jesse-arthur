package edu.miracosta.cs113;

public class MainTest
{
    public static void main(String[]args)
    {
        ListGraph testGraph = new ListGraph(3, false);
        testGraph.insert(new Edge(0, 1, 0.6));
        testGraph.insert(new Edge(0, 6, 0.2));
        testGraph.insert(new Edge(0, 5, 0.1));
        testGraph.insert(new Edge(1, 2, 0.4));
        testGraph.insert(new Edge(2, 6, 0.7));
    }

}
