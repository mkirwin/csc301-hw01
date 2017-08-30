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
        testLastPart();
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
     * Problem 2 Part 4: Make your own robot path finder.
     * Finds the point furthest from the current point to construct a path.
     * @param pointsToVisit: the set of points in the problem.
     * @return a list of points sorted in "furthest path" order.
     */
    public static List<Point> furthestPathFinder(Set<Point> pointsToVisit){
        List<Point> path = new ArrayList<Point>();
        Random rn = new Random();
        Point startPoint = (Point) pointsToVisit.toArray()[rn.nextInt(pointsToVisit.size())];
        path.add(startPoint);
        pointsToVisit.remove(startPoint);
        Point prevPoint = startPoint;
        while(!pointsToVisit.isEmpty()){
            Point maxPoint = null;
            for(Point point : pointsToVisit){
                Double dist = point.distanceTo(prevPoint);
                if(maxPoint == null || dist > maxPoint.distanceTo(prevPoint)){
                    maxPoint = point;
                }
            }
            path.add(maxPoint);
            pointsToVisit.remove(maxPoint);
            prevPoint = maxPoint;
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
     * Generates a set of random points (no greater than 100 points long).
     */
    public static Set<Point> makeRandomPointSet() {
        Set<Point> points = new HashSet<Point>();
        Random rn = new Random();
        for (int i = 0; i < 10; i++) {
            points.add(new Point(rn.nextInt(100), rn.nextInt(100)));
        }
        return points;
    }

    /**
     * Returns the distance between a set of points that have already been ordered by order visited.
     * @param sortedPoints
     * @return the distance between a set of points that have already been ordered by order visited.
     */
    public static int totalDistanceTraveled(List<Point> sortedPoints) {
        int totalDist = 0;
        int iters = 0;
        Point prev = null;
        for (Point point : sortedPoints) {
            if (iters != 0) {
                totalDist += point.distanceTo(prev);
                prev = point;
                iters++;
            } else {
                prev = point;
                iters++;
            }
        }
        return totalDist;
    }

    public static void testLastPart() {
        List<Set<Point>> pointSets = new ArrayList<Set<Point>>();
        // Add random point sets to a set of point sets.
        for (int i = 0; i < 10; i++) {
            pointSets.add(makeRandomPointSet());
        }

        int i = 1;
        // For each pointSet, print its points.
        for (Set<Point> pointSet : pointSets) {
            for (Point point : pointSet) {
                point.print();
            }
            // Because the PathFinders use for-each loops, they consume the pointSet, so we have to make copies.
            Set<Point> pointSetCopy = new HashSet<Point>(pointSet);
            Set<Point> pointSetCopy1 = new HashSet<Point>(pointSet);
            Set<Point> pointSetCopy2 = new HashSet<Point>(pointSet);
            System.out.println("===FOR PATH " + i++ + " ===");
            System.out.println("Distance, closest: " + totalDistanceTraveled(closestPathFinder(pointSet)));
            System.out.println("Distance, furthest: " + totalDistanceTraveled(furthestPathFinder(pointSetCopy)));
            System.out.println("Distance, diagonal: " + totalDistanceTraveled(randomPathFinder(pointSetCopy1)));
            System.out.println("Distance, random: " + totalDistanceTraveled(diagonalPathFinder(pointSetCopy2)));
            System.out.println("=======\n");
        }
    }
}
