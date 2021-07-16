import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {

		String[] step = null;
		File file = new File("input.txt");

		try {
			ArrayList<String> alPath = new ArrayList<>();
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				alPath.add(scanner.next());
			}
			step = alPath.toArray(new String[alPath.size()]);
		} catch (IOException e) {
			System.out.println("Error");
		}
		// deleting "," at the end of the string
		for (int i = step.length - 2; i >= 0; i--) {
			step[i] = step[i].substring(0, step[i].length() - 1);
		}
		// print map on the screen
		// for (String i : step) {
		///// String x = i;
		// System.out.println(x);
		// }

		int direction = 0; // 0 north, 1 east, 2 south, 3 west
		int x = 0; // N+ S-
		int y = 0; // E+ W-
		String[] coordinates = new String[step.length];
		for (int i = 0; i < step.length; i++) {
			int stepLength = Integer.parseInt(step[i].substring(1, step[i].length()));
			if (step[i].charAt(0) == 'R') {
				if (direction < 3) {
					direction++;
					if (direction == 1) {
						y = y + stepLength;
						//System.out.println(step[i] +" " + direction);
					} else if (direction == 2) {
						x = x - stepLength;
						//System.out.println(step[i] +" " + direction);
					} else {
						y = y - stepLength;
					//	System.out.println(step[i] +" " + direction);
					}
					coordinates[i] = String.valueOf(x) + "," + String.valueOf(y);
				} else {
					direction = 0;
					if (direction == 0) {
						x = x + stepLength;
						//System.out.println(step[i] +" " + direction);
					}
					coordinates[i] = String.valueOf(x) + "," + String.valueOf(y);
				}
			} else {

				if (direction > 0) {
					direction--;
					if (direction == 0) {
						x = x + stepLength;
					//	System.out.println(step[i] +" " + direction);
					} else if (direction == 1) {
						y = y + stepLength;
						//System.out.println(step[i] +" " + direction);
					} else if (direction == 2) {
						x = x - stepLength;
						//System.out.println(step[i] +" " + direction);

					}
					coordinates[i] = String.valueOf(x) + "," + String.valueOf(y);
				} else {
					direction = 3;
					if (direction == 3) {
						y = y - stepLength;

					//	System.out.println(step[i] +" " + direction);
					}
					coordinates[i] = String.valueOf(x) + "," + String.valueOf(y);
				}

			}
			System.out.println(coordinates[i]);
			
		}outerloop:
		for (int i = 0; i < coordinates.length; i++) {
			
			for (int j = i + 1; j < coordinates.length; j++) {
				if (coordinates[i].equals(coordinates[j])) {
					System.out.println("Dubel " + coordinates[j]);
					break outerloop;
				}
			}
		}
		
		System.out.println(-3%2);
		//System.out.println("coordinates of the end " + x + " " + y);
	}
}
