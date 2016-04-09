package robot;

import robot.point;
import java.util.*;

/*
 * Done by Eduardo Pertierra Puche for Intelligent System Class ©.
 * Group Mates: Rafael Pernil Bronchalo, Francisco Gambero Salinas
 * PergamPerBot 
 * Tested: Not tested yet. 
 * 
 */

public class AStarAlgorithm {

	public AStarAlgorithm() {

	}
	/*
	 * Francisco Defined Functions
	 */

	public int heuristic(int currentX, int currentY, int goalX, int goalY) {
		return (Math.abs(currentX - goalX) + Math.abs(currentY - goalY));
	}

	public int totalCost(int g, int currentX, int currentY, int goalX, int goalY) {
		int h;
		g++;

		h = heuristic(currentX, currentY, goalX, goalY);
		return g + h;
	}

	public int[][] matrixh(int numrows, int numcols, int goalX, int goalY) {
		int[][] res = new int[numrows][numcols];
		for (int i = 0; i < numrows; i++) {
			for (int j = 0; j < numcols; j++) {
				res[i][j] = heuristic(i, j, goalX, goalY);
			}
		}

		return res;
	}

	// it was originally a boolean
	public boolean[][] compute() {
		/*
		 * Initializating number of rows and columns to work with everything
		 */
		int numrows = 13;
		int numcols = 10;
		/*
		 * Initializating matrices of booleans, res and obstacles to work with
		 * it
		 */
		boolean[][] res = new boolean[numrows][numcols];
		boolean[][] obstacles = createobstacles(numrows, numcols);

		int[][] g = new int[numrows][numcols]; // Matrix with G for every point.
		// Francisco's work
		/*
		 * Initializating sets to store our results
		 */
		HashSet<point> openset = new HashSet<>();
		HashSet<point> closedset = new HashSet<>();
		HashMap<point, point> parent = new HashMap<>();
		// HashMap<point, point> parent = new HashMap<>();
		/*
		 * Initializating goal and starting points. Also adding them to proper
		 * sets.
		 */
		point goal = new point(2, 0);// Where do we wanna get with the robot
		// int[][] heuristic = matrixh(numrows, numcols, goal.getX(),
		// goal.getY()); TODO May be useful in the future

		point start = new point(5, 5);
		point current = start;// Starting point of the robot
		point p1, p2, p3, p4;
		openset.add(current);
		g[current.getX()][current.getY()] = 0;
		// Now following the pseudocode in slides
		boolean finished = false;
		while (!openset.isEmpty() && !finished) {
			System.out.println("Loop");
			current = current(goal, g, openset, obstacles, numrows, numcols);

			if (current.equals(goal)) {
				finished = true;
				System.out.println("Reached final State");
				res = reconstruct_path(parent, start, numrows, numcols, goal); // TODO
																				// reconstruct
				// path
			}

			/*
			 * Adding current node to set.
			 */
			closedset.add(current);
			openset.remove(current);

			// Al possible points we will compute
			p1 = new point(current.getX() + 1, current.getY()); // 1 point to
																// the right
			p2 = new point(current.getX() - 1, current.getY()); // 1 point to
																// the left
			p3 = new point(current.getX(), current.getY() + 1); // 1 point up
			p4 = new point(current.getX(), current.getY() - 1); // 1 point down
			/*
			 * Creating a neighbors set and initalizating it to work wwith the
			 * for each
			 */
			HashSet<point> neighbors = new HashSet<>();
			neighbors.add(p1);
			neighbors.add(p2);
			neighbors.add(p3);
			neighbors.add(p4);
			/*
			 * For each function. Code is exactly the same as in slides
			 */
			for (point i : neighbors) {
				int tempx, tempy;
				tempx = i.getX();
				tempy = i.getY();
				boolean condition = (tempx < numrows) && (tempy < numcols) && (tempx >= 0) && (tempy >= 0);
				if (!closedset.contains(i) && condition) {
					int tentative_g = g[current.getX()][current.getY()] + 1;
					if (!openset.contains(i) || tentative_g < g[i.getX()][i.getY()]) {

						parent.put(i, current); // Adding points to the
												// dictionary. TODO
						System.out
								.println("Added point" + +current.getX() + current.getY() + "->" + i.getX() + i.getY());// TODO
						g[i.getX()][i.getY()] = tentative_g;
						if (!openset.contains(i) && !obstacles[i.getX()][i.getY()]) {
							openset.add(i);
						}
					}
				}
			}

		}
		return res;
	}

	/*
	 * Function which given a Map, the starting, goal point, and the number of
	 * rows and columns returns a matrix with the path made with booleans If the
	 * path is good, returns 1. It's always only a path.
	 */
	public boolean[][] reconstruct_path(HashMap<point, point> parent, point start, int numrows, int numcols,
			point goal) {
		boolean[][] res = new boolean[numrows][numcols];
		point compare = goal;
		while (/*!compare.equals(goal) && */compare != null) {
			res[compare.getX()][compare.getY()] = true;
			compare = parent.get(compare);
		}
		return res;
	}

	public point current(point goal, int[][] g, HashSet<point> openset, boolean[][] obstacles, int numrows,
			int numcols) {
		/*
		 * Returns the node in openset with the lowest value
		 */
		point res, i;
		Iterator<point> it = openset.iterator();
		res = it.next();
		int x, y, resx, resy;
		while (it.hasNext()) {
			i = it.next();
			x = i.getX();
			y = i.getY();
			resy = res.getX();
			resx = res.getY();
			boolean condition = (x < numrows) && (y < numcols) && (resy >= 0) && (resx >= 0) && (resx < numrows)
					&& (resy < numcols) && (y >= 0) && (x >= 0);
			if (condition) {
				if (!obstacles[x][y] && (heuristic(x, y, goal.getX(), goal.getY())
						+ g[x][y]) < (heuristic(resx, resy, goal.getX(), goal.getY()) + g[resx][resy])) {
					res = i;
				}
			}
		}
		return res;

	}

	public boolean[][] createobstacles(int obstaclerow, int obstaclecol) {
		boolean[][] ObsPosition = new boolean[obstaclerow][obstaclecol]; // This
																			// is
																			// our
																			// map
																			// test,
																			// we
																			// can
																			// change
																			// it
																			// .
																			// We
																			// also
																			// gotta
																			// change
																			// it
																			// in
																			// the
		ObsPosition[0][0] = false;
		ObsPosition[0][1] = false;
		ObsPosition[0][2] = false;
		ObsPosition[0][3] = false;
		ObsPosition[0][4] = false;
		ObsPosition[0][5] = false;
		ObsPosition[0][6] = false;
		ObsPosition[0][7] = false;
		ObsPosition[0][8] = false;
		ObsPosition[0][9] = false;

		ObsPosition[1][0] = false;
		ObsPosition[1][1] = false;
		ObsPosition[1][2] = false;
		ObsPosition[1][3] = false;
		ObsPosition[1][4] = false;
		ObsPosition[1][5] = false;
		ObsPosition[1][6] = false;
		ObsPosition[1][7] = false;
		ObsPosition[1][8] = false;
		ObsPosition[1][9] = false;

		ObsPosition[2][0] = false;
		ObsPosition[2][1] = true;
		ObsPosition[2][2] = true;
		ObsPosition[2][3] = true;
		ObsPosition[2][4] = true;
		ObsPosition[2][5] = true;
		ObsPosition[2][6] = true;
		ObsPosition[2][7] = true;
		ObsPosition[2][8] = true;
		ObsPosition[2][9] = false;

		ObsPosition[3][0] = false;
		ObsPosition[3][1] = false;
		ObsPosition[3][2] = false;
		ObsPosition[3][3] = false;
		ObsPosition[3][4] = false;
		ObsPosition[3][5] = false;
		ObsPosition[3][6] = false;
		ObsPosition[3][7] = false;
		ObsPosition[3][8] = false;
		ObsPosition[3][9] = false;

		ObsPosition[4][0] = false;
		ObsPosition[4][1] = false;
		ObsPosition[4][2] = true;
		ObsPosition[4][3] = true;
		ObsPosition[4][4] = true;
		ObsPosition[4][5] = true;
		ObsPosition[4][6] = true;
		ObsPosition[4][7] = true;
		ObsPosition[4][8] = true;
		ObsPosition[4][9] = true;

		ObsPosition[5][0] = false;
		ObsPosition[5][1] = false;
		ObsPosition[5][2] = true;
		ObsPosition[5][3] = false;
		ObsPosition[5][4] = false;
		ObsPosition[5][5] = false;
		ObsPosition[5][6] = false;
		ObsPosition[5][7] = false;
		ObsPosition[5][8] = false;
		ObsPosition[5][9] = false;

		ObsPosition[6][0] = false;
		ObsPosition[6][1] = false;
		ObsPosition[6][2] = true;
		ObsPosition[6][3] = false;
		ObsPosition[6][4] = false;
		ObsPosition[6][5] = true;
		ObsPosition[6][6] = true;
		ObsPosition[6][7] = true;
		ObsPosition[6][8] = true;
		ObsPosition[6][9] = false;

		ObsPosition[7][0] = false;
		ObsPosition[7][1] = false;
		ObsPosition[7][2] = true;
		ObsPosition[7][3] = true;
		ObsPosition[7][4] = true;
		ObsPosition[7][5] = true;
		ObsPosition[7][6] = false;
		ObsPosition[7][7] = true;
		ObsPosition[7][8] = false;
		ObsPosition[7][9] = false;

		ObsPosition[8][0] = false;
		ObsPosition[8][1] = false;
		ObsPosition[8][2] = false;
		ObsPosition[8][3] = false;
		ObsPosition[8][4] = false;
		ObsPosition[8][5] = false;
		ObsPosition[8][6] = false;
		ObsPosition[8][7] = true;
		ObsPosition[8][8] = false;
		ObsPosition[8][9] = false;

		ObsPosition[9][0] = false;
		ObsPosition[9][1] = false;
		ObsPosition[9][2] = false;
		ObsPosition[9][3] = false;
		ObsPosition[9][4] = false;
		ObsPosition[9][5] = true;
		ObsPosition[9][6] = true;
		ObsPosition[9][7] = true;
		ObsPosition[9][8] = false;
		ObsPosition[9][9] = false;

		ObsPosition[10][0] = false;
		ObsPosition[10][1] = false;
		ObsPosition[10][2] = false;
		ObsPosition[10][3] = false;
		ObsPosition[10][4] = false;
		ObsPosition[10][5] = false;
		ObsPosition[10][6] = false;
		ObsPosition[10][7] = true;
		ObsPosition[10][8] = false;
		ObsPosition[10][9] = false;

		ObsPosition[11][0] = false;
		ObsPosition[11][1] = false;
		ObsPosition[11][2] = false;
		ObsPosition[11][3] = false;
		ObsPosition[11][4] = false;
		ObsPosition[11][5] = false;
		ObsPosition[11][6] = false;
		ObsPosition[11][7] = false;
		ObsPosition[11][8] = false;
		ObsPosition[11][9] = false;

		ObsPosition[12][0] = false;
		ObsPosition[12][1] = false;
		ObsPosition[12][2] = false;
		ObsPosition[12][3] = false;
		ObsPosition[12][4] = false;
		ObsPosition[12][5] = false;
		ObsPosition[12][6] = false;
		ObsPosition[12][7] = false;
		ObsPosition[12][8] = false;
		ObsPosition[12][9] = false;

		return ObsPosition;
	}
}