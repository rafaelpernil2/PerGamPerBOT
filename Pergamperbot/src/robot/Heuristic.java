package robot;
public class Heuristic {

	public int heuristic(int currentX, int currentY, int goalX, int goalY) {
		return (Math.abs(currentX - goalX) + Math.abs(currentY - goalY));
	}

	public int totalCost(int g, int currentX, int currentY, int goalX, int goalY) {
		int h;
		g++;

		h = heuristic(currentX, currentY, goalX, goalY);
		return g+h;
	}
}
