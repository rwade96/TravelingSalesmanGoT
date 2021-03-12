import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class DrawMap extends JPanel {

	IconFactory IconFactory;
	CoordinateManager CM;
	boolean isOriginalMap;
	
	public DrawMap(CoordinateManager newCM, Image map, int numberOfPoints, boolean isOriginalMap) {
		super();
		IconFactory = new IconFactory();
		CM = newCM;
		this.isOriginalMap = isOriginalMap;
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
	}

	public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.drawImage(IconFactory.getImage("GoTMap.jpg"), 0, 0, null);
        
        
        // Mark data points
        g2.setPaint(Color.green);
        final Image wildfire = IconFactory.getImage("wildfire.png");
        final Image castle = IconFactory.getImage("castle.png");
        g2.drawImage(castle, 112, 196, null);
        
        //Plots the wildfire images on the graph
        for(int i = 1; i < CM.size(); i++) {
            g2.drawImage(wildfire, CM.getPoint(i).getX()-11, CM.getPoint(i).getY()-11, null);
        }
        
        if (!isOriginalMap) {
        	//Draws lines between each point on the graph
            for (int i = 0; i < CM.size()-1; i++) {
            	g2.drawLine(CM.getPoint(i).getX(), CM.getPoint(i).getY(),
            			CM.getPoint(i+1).getX(), CM.getPoint(i+1).getY());
            	
            }
        }
    }
}
