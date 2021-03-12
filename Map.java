import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class Map extends JFrame implements ActionListener {

	private final int width = 850;
	private final int height = 850;
	private final static int numberOfPoints = 1000;
	private final static double pixelToMile = 13.884;
	double[] distanceBetweenPoints = new double[numberOfPoints];
	
	IconFactory IconFactory = new IconFactory();
	Scanner scanner = new Scanner(System.in);
	
	CoordinateManager currCM;
	CoordinateManager unsolvedCM;
	CoordinateManager christofidesCM;
	
	protected JFrame f1, f2, f3;
	
	
	protected DrawMap origMap, BnBMap;
	
    public Map(DrawMap newMap, String title) {
    	origMap = newMap;
    	
    	f1 = new JFrame();
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setTitle(title);
        f1.add(newMap);
        f1.setSize(850,850);
        f1.setLocation(300,0);
        f1.setVisible(true);
    }
    
    public static void main(String[] args) {
    	IconFactory IconFactory = new IconFactory();
    	Image mapPic = IconFactory.getImage("GoTMap.jpg");
    	CoordinateManager CMOriginal = CoordinateGenerator.GenerateCoordinates(mapPic, numberOfPoints, 850, 850);
    	
    	double startTime, endTime;
    	
    	/*
    	//Records current time, runs BnB solving algorithm, finds and displays elapsed time
    	startTime = System.currentTimeMillis();
    	System.out.println("Solving using Branch and Bound...");
    	BranchAndBound solver = new BranchAndBound(CMOriginal);
    	int[] BandBSol = solver.calculate();
    	endTime = System.currentTimeMillis()-startTime;
    	System.out.println("Time for Branch and Bound with "+numberOfPoints+" points took "+endTime/1000+" seconds to solve");
    	
    	
    	CoordinateManager BandBCM = new CoordinateManager(numberOfPoints);
    	for (int i = 0; i < numberOfPoints; i++) {
    		BandBCM.addPoint(CMOriginal.getPoint(BandBSol[i]));
    		
    	}
    	System.out.println("Branch and Bound solution distance: " + BandBCM.getDistance()*pixelToMile + " miles");
    	*/
    	
    	
    	//Records current time, runs Christofides appximation algorithm, findes and displays elapsed time
    	System.out.println("Finding approximate solution using Christofides algorithm...");
    	startTime = System.currentTimeMillis();
    	Christofides christofidesSolver = new Christofides(true);
    	int[] christofidesSol = christofidesSolver.solve(christofidesSolver.findDistanceMatrix(CMOriginal));
    	endTime = System.currentTimeMillis()-startTime;
    	System.out.println("Time for Christofides Algorithm with "+numberOfPoints+" points took "+endTime/1000+" seconds to approximate");
    	
    	CoordinateManager christofidesCM = new CoordinateManager(numberOfPoints);
    	for (int i = 0; i < numberOfPoints; i++) {
    		christofidesCM.addPoint(CMOriginal.getPoint(christofidesSol[i]));
    	}
    	System.out.println("Christofides solution distance: " + christofidesCM.getDistance()*pixelToMile + " miles");
    	
    			
    			
    			
    	//Creates new map and JFrame window in which the data will be drawn
    	DrawMap originalMap = new DrawMap(CMOriginal, mapPic, numberOfPoints, true);
    	//DrawMap BandBMap = new DrawMap(BandBCM, mapPic, numberOfPoints, false);
    	DrawMap christofidesMap = new DrawMap(christofidesCM, mapPic, numberOfPoints, false);
    	
    	Map origMap = new Map(originalMap, "Unsolved Map");
        //Map BnBMap = new Map(BandBMap, "Branch and Bound Solution");
        Map christMap = new Map(christofidesMap, "Christofides Algorithm Approximation");
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
