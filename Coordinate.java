
public class Coordinate {
	
	
	private int x;
    private int y;
 
    public Coordinate(int xNew,int yNew) {
        this.x = xNew;
        this.y = yNew;
    }
 
    public double distanceToCity(Coordinate point) {
        int x = Math.abs(getX() - point.getX());
        int y = Math.abs(getY() - point.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

	public int getY() {
		return this.y;
	}

	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
}
