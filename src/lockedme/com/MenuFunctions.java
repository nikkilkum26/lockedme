package lockedme.com;

import java.util.List;
import java.util.Scanner;

public class MenuFunctions {
	public static void handleWelcomeScreenInput() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				MenuItems.menuList();
				int input = sc.nextInt();

				switch (input) {
				case 1:
					FileHandling.showAllFiles();
					break;
				case 2:
					MenuFunctions.handleFileMenuOptions();
					break;
				case 3:
					System.out.println("Program terminated successfully.");
					flag = false;
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Please select a valid option.");
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleWelcomeScreenInput();
			} 
		} while (flag == true);
	}
	
	public static void handleFileMenuOptions() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				MenuItems.displayFileOptions();
				FileHandling.createAFolder();
				
				int input = sc.nextInt();
				switch (input) {
				case 1:
				
					System.out.println("Enter the name of the file to be added to the \"store\" folder");
					String fileToAdd = sc.next();
					
					FileHandling.createNewFiles(fileToAdd, sc);
					
					break;
				case 2:
					
					System.out.println("Enter the name of the file to be deleted from \"store\" folder");
					String fileToDelete = sc.next();
					
					FileHandling.createAFolder();
					List<String> filesToDelete = FileHandling.displayFileLocations(fileToDelete, "store");
					
					String deletionPrompt = "\nSelect index of which file to delete?"
							+ "\n(Enter 0 if you want to delete all elements)";
					System.out.println(deletionPrompt);
				
					int data = sc.nextInt();
					
					if (data != 0) {
						
						FileHandling.deleteAllFiles(filesToDelete.get(data - 1));
					} else {
						
					
						for (String path : filesToDelete) {
							FileHandling.deleteAllFiles(path);
						}
					}
					

					break;
				case 3:
					
					System.out.println("Enter the name of the file to be searched from \"store\" folder");
					String fileName = sc.next();
					
					FileHandling.createAFolder();
					FileHandling.displayFileLocations(fileName, "store");

					
					break;
				case 4:
				
					return;
				case 5:
					
					System.out.println("Program successfully terminated.");
					flag = false;
					sc.close();
					System.exit(0);
				default:
					System.out.println("Please select a valid option from above.");
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleFileMenuOptions();
			}
		} while (flag == true);
	}
}

