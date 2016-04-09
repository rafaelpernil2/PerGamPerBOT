package robot;

public class Main {
	public static void main(String[] args) {
		boolean[][] toprint;
		AStarAlgorithm alg = new AStarAlgorithm();
		toprint = alg.compute();

		for (int i = 0; i < toprint.length; i++) {
			for (int j = 0; j < toprint[0].length; j++) {
				if (toprint[i][j]) {
					System.out.print("1");
				} else {
					System.out.print("0");
				}
			}
			System.out.println();
		}

	}
}
