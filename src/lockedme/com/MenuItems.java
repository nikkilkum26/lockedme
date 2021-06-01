package lockedme.com;

public class MenuItems {

	public static void welcomeScreen() {
		String detials = String.format("========================================================\n"
				+ "|| Welcome to Lockedme.com 			      ||\n" + "|| This application was developed by Nikkil Kumar PC. ||\n"
				+ "========================================================\n");
		String features = "This app has builtin features for :-\n"
				+ "* Listing all files from \"store\" folder\n"
				+ "* Search, add, or delete files in \"store\" folder.\n";
				
		System.out.println(detials);

		System.out.println(features);
	}
	public static void menuList() {
		String options = "\n\n Select the option from below and press Enter \n\n"
				+ "1) Display all files inside \"store\" folder\n" + "2) Display menu for File operations\n"
				+ "3) Exit program\n";
		System.out.println(options);

	}
	public static void displayFileOptions() {
		String options = "\n\n Select the option from below and press Enter\n\n"
				+ "1) Add a file to \"store\" folder\n" + "2) Delete a file from \"store\" folder\n"
				+ "3) Search for a file from \"store\" folder\n" + "4) Show Previous Menu\n" + "5) Exit program\n";

		System.out.println(options);
	}
}
