
package lockedme.com;

public class MainProgram {

	public static void main(String[] args) {
		
		FileHandling.createAFolder();
		
		MenuItems.welcomeScreen();
		
		MenuFunctions.handleWelcomeScreenInput();
	}

	
}