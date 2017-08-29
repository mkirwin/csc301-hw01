import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {
	/**
	 * @CITATIONS:
	 * I worked in a group with Kathryn and Zachary, until we talked to Anya and decided we would be better suited
	 * working alone.
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set points = new HashSet<Point>();
		points.add(new Point(10,10));
		points.add(new Point(0,0));
		points.add(new Point(10,0));
		points.add(new Point(0, 10));
		points.add(new Point(-1, -1));
		List<Point> pathList = diagonalPathFinder(points);
		for(Point point: pathList){
			point.print();
		}
		
	}
	
	/**
	 * Problem 2 Part 1: Closest-Point-Next
	 * @param pointsToVisit: the set of points in the problem
	 * @return a list containing points organized by the closest path algorithm
	 */
	public static List<Point> closestPathFinder(Set<Point> pointsToVisit){
		List<Point> path = new ArrayList<Point>();
		Random rn = new Random();
		Point startPoint = (Point) pointsToVisit.toArray()[rn.nextInt(pointsToVisit.size())];
		path.add(startPoint);
		pointsToVisit.remove(startPoint);
		Point prevPoint = startPoint;
		while(!pointsToVisit.isEmpty()){
			Point minPoint = null;
			for(Point point : pointsToVisit){
				Double dist = point.distanceTo(prevPoint);
				if(minPoint == null || dist < minPoint.distanceTo(prevPoint)){
					minPoint = point;
				}
			}
			path.add(minPoint);
			pointsToVisit.remove(minPoint);
			prevPoint = minPoint;
		}
		return path;
	}
	
	/**
	 * Problem 2 Part 2: Diagonal Sweep method
	 * @param pointsToVisit: the set of points in the problem
	 * @return a list containing points organized by the diagonal path algorithm
	 */
	public static List<Point> diagonalPathFinder(Set<Point> pointsToVisit){
		List<Point> path = new ArrayList<Point>(pointsToVisit);
		path.sort(new Point(0,0));
		return path;
	}
	
	/**
	 * Problem 2 Part 3: Select points in random order
	 * @param pointsToVisit: the set of points in the problem
	 * @return a list containing points organized by the random path algorithm
	 */
	public static List<Point> randomPathFinder(Set<Point> pointsToVisit){
		List<Point> path = new ArrayList<Point>();
		List<Point> pointsToVisitList = new ArrayList<Point>(pointsToVisit);
		Random rn = new Random();
		while(!pointsToVisitList.isEmpty()){
			int index = rn.nextInt(pointsToVisitList.size());
			Point nextPoint = pointsToVisitList.get(index);
			path.add(nextPoint);
			pointsToVisitList.remove(index);
		}
		return path;
	}

	/**
	 * Problem 2 Part 4: A "Maddie" method
	 */
	public static List<Point> maddiePathFinder(Set<Point> pointsToVisit) {
		List<Point> path = new ArrayList<~>();

	}
}
