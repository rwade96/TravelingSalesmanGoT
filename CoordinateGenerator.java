import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CoordinateGenerator {
	
	final static int width = 850;
	final static int height = 850;
	
	public static CoordinateManager GenerateCoordinates(Image map, int numberOfPoints, int height, int width) {
		CoordinateManager CM = new CoordinateManager(numberOfPoints);
		CM.addPoint(new Coordinate(125, 209));
		for (int i = 1; i < numberOfPoints; i++) {
        	int[] generatedCoordinates = randomCoordinateGenerator();
        	Coordinate candidate = new Coordinate(generatedCoordinates[0], generatedCoordinates[1]);
        	while (!isOnLand(map, candidate)) {
        		int[] generatedCoordinates1 = randomCoordinateGenerator();
	        	candidate.setX(generatedCoordinates1[0]);
	        	candidate.setY(generatedCoordinates1[1]);
        	}
        	CM.addPoint(candidate);
        }
		return CM;
	}
	
	public static boolean isOnLand(Image image, Coordinate candidate) {
    	int redColor = (toBufferedImage(image).getRaster().getSample(candidate.getX(), candidate.getY(), 0));
    	int greenColor = (toBufferedImage(image).getRaster().getSample(candidate.getX(), candidate.getY(), 1));
    	
    	if (redColor < 105 && greenColor < 105) {
    		return false;
    	}
    	int xCoor = candidate.getX();
    	int yCoor = candidate.getY();
    	if (xCoor > 540 && yCoor < 350)
    		return false;
    	else if (yCoor > 720)
    		return false;
    	else if (xCoor < 245 && yCoor < 150)
    		return false;
    	else if (xCoor > 360 && xCoor < 430 && yCoor > 190 && yCoor < 270)
    		return false;
    	else if (xCoor > 190 && xCoor < 260 && yCoor > 600 && yCoor < 690)
    		return false;
    	return true;
    }
	
	public static int[] randomCoordinateGenerator() {
    	Random r = new Random();
    	int[] coordinates = new int[2];
    	coordinates[0] = r.nextInt(width);
    	coordinates[1] = r.nextInt(height);
    	return coordinates;
    }
	
	public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
            return (BufferedImage) img;

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

}
