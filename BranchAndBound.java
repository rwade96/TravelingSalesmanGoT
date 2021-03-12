import java.util.ArrayList;
import java.util.HashSet;

/**
 * Solves the traveling salesman problem using Branch and Bound by utilizing Node's
 */
public class BranchAndBound {
	double[][] distances;
	double best_cost;
	int[] best_path;

	/**
	 * Constructs a new Solver and initializes distances array
	 *
	 * @param CM1 An ArrayList of City's
	 */
	public BranchAndBound(CoordinateManager CM1) {
		distances = new double[CM1.size()][CM1.size()];
		for(int i = 0; i < CM1.size(); i++) {
			for(int ii = 0; ii < CM1.size(); ii++)
				distances[i][ii] = distanceBetweenPoints(CM1.getPoint(i), CM1.getPoint(ii));
		}
	}
	
	public double distanceBetweenPoints(Coordinate point1, Coordinate point2) {
        double distX = Math.abs(point1.getX() - point2.getX());
        double distY = Math.abs(point1.getY() - point2.getY());
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }

	/**
	 * Calculates the shortest (non-repeating) path between a series of nodes
	 *
	 * @return An array with the locations of the best path
	 */
	public int[] calculate() {
		HashSet<Integer> location_set = new HashSet<Integer>(distances.length);
		for(int i = 0; i < distances.length; i++)
			location_set.add(i);

		best_cost = findGreedyCost(0, location_set, distances);

		int[] active_set = new int[distances.length];
		for(int i = 0; i < active_set.length; i++)
			active_set[i] = i;

		BnBNode root = new BnBNode(null, 0, distances, active_set, 0);
		traverse(root);
		
		return best_path;
	}

	/**
	 * Get current path cost
	 *
	 * @return The cost
	 */
	public double getCost() {
		return best_cost;
	}

	/**
	 * Find the greedy cost for a set of locations
	 *
	 * @param i The current location
	 * @param location_set Set of all remaining locations
	 * @param distances The 2D array containing point distances
	 * @return The greedy cost
	 */
	private double findGreedyCost(int i, HashSet<Integer> location_set, double[][] distances) {
		if(location_set.isEmpty())
			return distances[0][i];

		location_set.remove(i);

		double lowest = Double.MAX_VALUE;
		int closest = 0;
		for(int location : location_set) {
			double cost = distances[i][location];
			if(cost < lowest) {
				lowest = cost;
				closest = location;
			}
		}

		return lowest + findGreedyCost(closest, location_set, distances);
	}

	/**
	 * Recursive method to go through the tree finding and pruning paths
	 *
	 * @param parent The root/parent node
	 */
	private void traverse(BnBNode parent) {
		BnBNode[] children = parent.generateChildren();

		for(BnBNode child : children) {
			if(child.isTerminal()) {
				double cost = child.getPathCost();
				if(cost < best_cost) {
					best_cost = cost;
					best_path = child.getPath();
				}
			}
			else if(child.getLowerBound() <= best_cost) {
				traverse(child);
			}
		}
	}
}