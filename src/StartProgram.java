/**
 * @author Gabriel Woodburn - gwoodburn
 * CIS175 - Spring 2021
 * 2/09/21
 */
import java.util.List;
import java.util.Scanner;

import controller.FlowerItemHelper;
import model.Flower;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static FlowerItemHelper fih = new FlowerItemHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter a type: ");
			String type = in.nextLine();
			System.out.print("Enter a color: ");
			String color = in.nextLine();
			System.out.print("Enter a quantity: ");
			int quantity = in.nextInt();
			
			Flower toAdd = new Flower(type, color, quantity);
			fih.insertItem(toAdd);
		}

		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the type to delete: ");
			String type = in.nextLine();
			System.out.print("Enter the color to delete: ");
			String color = in.nextLine();
			System.out.print("Enter the quantity to delete: ");
			int quantity = in.nextInt();
			Flower toDelete = new Flower(type, color, quantity);
			fih.deleteItem(toDelete);
		}

		private static void editAnItem() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Type");
			System.out.println("2 : Search by Color");
			int searchBy = in.nextInt();
			in.nextLine();
			List<Flower> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the flower type: ");
				String flowerType = in.nextLine();
				foundItems = fih.searchForItemByType(flowerType);
			} else {
				System.out.print("Enter the color: ");
				String flowerColor = in.nextLine();
				foundItems = fih.searchForItemByItem(flowerColor);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (Flower l : foundItems) {
					System.out.println(l.getId() + " : " + l.returnItemDetails());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				Flower toEdit = fih.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getColor() + " from " + toEdit.getType());
				System.out.println("1 : Update Type");
				System.out.println("2 : Update Color");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Type: ");
					String newType = in.nextLine();
					toEdit.setType(newType);
				} else if (update == 2) {
					System.out.print("New Color: ");
					String newColor = in.nextLine();
					toEdit.setColor(newColor);
				}

				fih.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to the flower list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add a flower");
				System.out.println("*  2 -- Edit a flower");
				System.out.println("*  3 -- Delete a flower");
				System.out.println("*  4 -- View the list of flowers");
				System.out.println("*  5 -- Exit the program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					fih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			List<Flower> allItems = fih.showAllItems();
			for(Flower singleItem : allItems){
				System.out.println(singleItem.returnItemDetails());
			}
		}
}

	