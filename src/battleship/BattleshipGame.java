package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		
		
		String playAgain = "Y";
		Scanner input = new Scanner(System.in);
		Ocean ocean = new Ocean();

		while (playAgain == "Y") {
			ocean.placeAllShipsRandomly();
			
			ocean.print();
			

			while (!(ocean.isGameOver())) {
				System.out.println("Enter row,column: ");
				String rowAndCol = input.next();
				String[] arrayOfRowAndCol = rowAndCol.split(",");
				int x = Integer.parseInt(arrayOfRowAndCol[0]);
				int y = Integer.parseInt(arrayOfRowAndCol[1]);
				ocean.shootAt(x, y);
				ocean.print();
				System.out.println("You have " + ocean.getShotsFired() + " times shots.");
				System.out.println("You have " + ocean.getHitCount() + " times hits.");
//				if (!(ocean.isOccupied(x, y))) {
//					ocean.print();
//					System.out.println("You have " + ocean.getShotsFired() + " times shots.");
//					System.out.println("You have " + ocean.getHitCount() + " times hits.");
//				} 
//				else {
//					Ship ship = ocean.getShipArray()[x][y];	
//					if (!(ship.isSunk())) {
//						System.out.println("You have " + ocean.getShotsFired() + " times shots.");
//						System.out.println("You have " + ocean.getHitCount() + " times hits.");
//					}
//				}
//				input1.close();
			}
			System.out.println("Do you want to play again?(Y/N)");
			playAgain = input.nextLine();
			if (playAgain == "Y") {
				ocean.setupEmptyOcean();
			}
		// input2.close();
		}
		input.close();
		
	}

}
