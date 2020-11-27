package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		
		
		String playAgain = "Y";
		Scanner input = new Scanner(System.in);
		Ocean ocean = new Ocean();

		while (playAgain.toUpperCase().equals("Y")) {
			ocean.placeAllShipsRandomly();
			
			ocean.print();
			

			while (!(ocean.isGameOver())) {
				int x = -1;
				int y = -1;
				// handle invalid user inputs.
				while (!ocean.isValidLocationInOcean(x, y)) {
					System.out.println("Enter row,column: ");
					String rowAndCol = input.nextLine();
					String[] arrayOfRowAndCol = rowAndCol.split(",");
					try {
						x = Integer.parseInt(arrayOfRowAndCol[0].strip());
						y = Integer.parseInt(arrayOfRowAndCol[1].strip());
					} catch (Exception e) {
					}
					finally {
						if (!ocean.isValidLocationInOcean(x, y)) {
							System.out.println("Invalid row or column, please try again!");
						}
					}

				}
				ocean.shootAt(x, y);
				ocean.print();
				System.out.println("You have " + ocean.getShotsFired() + " times shots.");
				System.out.println("You have " + ocean.getHitCount() + " times hits.");
			}
			System.out.println("Do you want to play again?(Y/N)");
			playAgain = input.nextLine();
			if (playAgain.toUpperCase().equals("Y")) {
				ocean.setupEmptyOcean();
			}
		}
		input.close();
		
	}

}
