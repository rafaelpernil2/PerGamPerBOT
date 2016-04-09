package robot;

import robocode.Robot;
import robot.AStarAlgorithm;

public class PerGamPerBot extends Robot {

	private void toLeft() {
		if (getHeading() == 270) {
			ahead(64);
		} else if (getHeading() == 0) {
			turnLeft(90);
			ahead(64);
		} else if (getHeading() == 90) {
			turnLeft(180);
			ahead(64);
		} else if (getHeading() == 180) {
			turnRight(90);
			ahead(64);
		}

	}

	private void toRight() {
		if (getHeading() == 90) {
			ahead(64);
		} else if (getHeading() == 0) {
			turnRight(90);
			ahead(64);
		} else if (getHeading() == 270) {
			turnRight(180);
			ahead(64);
		} else if (getHeading() == 180) {
			turnLeft(90);
			ahead(64);
		}

	}

	private void up() {
		if (getHeading() == 0) {
			ahead(64);
		} else if (getHeading() == 270) {
			turnRight(90);
			ahead(64);
		} else if (getHeading() == 180) {
			turnLeft(180);
			ahead(64);
		} else if (getHeading() == 90) {
			turnLeft(90);
			ahead(64);
		}

	}

	private void bottom() {
		if (getHeading() == 180) {
			ahead(64);
		} else if (getHeading() == 0) {
			turnRight(180);
			ahead(64);
		} else if (getHeading() == 90) {
			turnRight(90);
			ahead(64);
		} else if (getHeading() == 270) {
			turnLeft(90);
			ahead(64);
		}

	}

	/*
	 * private void followRoute(boolean[][] route) { // First, we locate the bot
	 * boolean[][] aux = route; int initX, initY; initX = ((int) (getX()) - 32)
	 * / 64; initY = ((int) (getY()) - 32) / 64; do { if (aux[initX + 1][initY])
	 * { aux[initX][initY] = false; toRight(); initX++; } else if
	 * (aux[initX][initY - 1]) { aux[initX][initY] = false; bottom(); initY--; }
	 * else if (aux[initX - 1][initY]) { aux[initX][initY] = false; toLeft();
	 * initX--; } else if (aux[initX][initY + 1]) { aux[initX][initY] = false;
	 * straight(); initY++; } } while (aux[initX + 1][initY] || aux[initX][initY
	 * - 1] || aux[initX - 1][initY] || aux[initX][initY + 1]);
	 * 
	 * }
	 * 
	 * (non-Javadoc)
	 * 
	 * @see robocode.Robot#run()
	 */
	private void followRoute(boolean[][] route) {
		// First, we locate the bot
		boolean[][] aux = route;
		int initX, initY;
		initX = ((int) (getX()) - 32) / 64;
		initY = ((int) (getY()) - 32) / 64;
		aux[initX][initY] = false;
		do {

			if (aux[initX][initY - 1]) { // We change + to - to
											// make it work with
											// robocode

				bottom();
				initY--;
				aux[initX][initY] = false;
			}
			
			else if (aux[initX + 1][initY]) {
				toRight();
				initX++;
				aux[initX][initY] = false;
			}else if (aux[initX - 1][initY]) {

				toLeft();
				initX--;
				aux[initX][initY] = false;
			}
			else if (aux[initX][initY + 1]) { // We change - to
				// + to make it
				// work with
				// robocode

				up();
				initY++;
				aux[initX][initY] = false;
			} 
		} while (true/*
						 * aux[initX + 1][initY] || aux[initX][initY - 1] ||
						 * aux[initX - 1][initY] || aux[initX][initY + 1]
						 */);

	}

	public void run() {
		boolean[][] Route2 = new boolean[13][10];
		AStarAlgorithm alg = new AStarAlgorithm();
		Route2 = alg.compute();

		boolean[][] Route = new boolean[13][10];
		Route[0][0] = false;
		Route[0][1] = false;
		Route[0][2] = false;
		Route[0][3] = false;
		Route[0][4] = false;
		Route[0][5] = false;
		Route[0][6] = false;
		Route[0][7] = false;
		Route[0][8] = false;
		Route[0][9] = false;

		Route[1][0] = true;
		Route[1][1] = true;
		Route[1][2] = true;
		Route[1][3] = true;
		Route[1][4] = true;
		Route[1][5] = true;
		Route[1][6] = true;
		Route[1][7] = true;
		Route[1][8] = true;
		Route[1][9] = true;

		Route[2][0] = false;
		Route[2][1] = false;
		Route[2][2] = false;
		Route[2][3] = false;
		Route[2][4] = false;
		Route[2][5] = false;
		Route[2][6] = false;
		Route[2][7] = false;
		Route[2][8] = false;
		Route[2][9] = true;

		Route[3][0] = true;
		Route[3][1] = true;
		Route[3][2] = true;
		Route[3][3] = true;
		Route[3][4] = true;
		Route[3][5] = true;
		Route[3][6] = true;
		Route[3][7] = true;
		Route[3][8] = true;
		Route[3][9] = true;

		Route[4][0] = true;
		Route[4][1] = false;
		Route[4][2] = false;
		Route[4][3] = false;
		Route[4][4] = false;
		Route[4][5] = false;
		Route[4][6] = false;
		Route[4][7] = false;
		Route[4][8] = false;
		Route[4][9] = false;

		Route[5][0] = true;
		Route[5][1] = false;
		Route[5][2] = false;
		Route[5][3] = false;
		Route[5][4] = false;
		Route[5][5] = true;
		Route[5][6] = true;
		Route[5][7] = true;
		Route[5][8] = true;
		Route[5][9] = true;

		Route[6][0] = true;
		Route[6][1] = false;
		Route[6][2] = false;
		Route[6][3] = false;
		Route[6][4] = false;
		Route[6][5] = false;
		Route[6][6] = false;
		Route[6][7] = false;
		Route[6][8] = false;
		Route[6][9] = true;

		Route[7][0] = true;
		Route[7][1] = false;
		Route[7][2] = false;
		Route[7][3] = false;
		Route[7][4] = false;
		Route[7][5] = false;
		Route[7][6] = false;
		Route[7][7] = false;
		Route[7][8] = true;
		Route[7][9] = true;

		Route[8][0] = true;
		Route[8][1] = true;
		Route[8][2] = true;
		Route[8][3] = true;
		Route[8][4] = true;
		Route[8][5] = false;
		Route[8][6] = false;
		Route[8][7] = false;
		Route[8][8] = true;
		Route[8][9] = false;

		Route[9][0] = false;
		Route[9][1] = false;
		Route[9][2] = false;
		Route[9][3] = false;
		Route[9][4] = true;
		Route[9][5] = false;
		Route[9][6] = false;
		Route[9][7] = false;
		Route[9][8] = true;
		Route[9][9] = false;

		Route[10][0] = false;
		Route[10][1] = false;
		Route[10][2] = false;
		Route[10][3] = false;
		Route[10][4] = true;
		Route[10][5] = true;
		Route[10][6] = true;
		Route[10][7] = false;
		Route[10][8] = true;
		Route[10][9] = false;

		Route[11][0] = false;
		Route[11][1] = false;
		Route[11][2] = false;
		Route[11][3] = false;
		Route[11][4] = false;
		Route[11][5] = false;
		Route[11][6] = true;
		Route[11][7] = true;
		Route[11][8] = true;
		Route[11][9] = false;

		Route[12][0] = false;
		Route[12][1] = false;
		Route[12][2] = false;
		Route[12][3] = false;
		Route[12][4] = false;
		Route[12][5] = false;
		Route[12][6] = false;
		Route[12][7] = false;
		Route[12][8] = false;
		Route[12][9] = false;

		followRoute(Route2);
	}
}
