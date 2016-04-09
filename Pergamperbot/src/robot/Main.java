package robot;

public class Main {
	public static void main(String[] args) {
		boolean[][] toprint;
		AStarAlgorithm alg = new AStarAlgorithm();
		toprint = alg.compute();
		//toprint = alg.compute();
		for (int j = 0; j < toprint[0].length; j++) {
			for (int i = 0; i < toprint.length; i++) {
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
