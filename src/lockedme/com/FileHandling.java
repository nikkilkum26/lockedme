package lockedme.com;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileHandling {

	public static void createAFolder() {
		File myFile = new File("store");
		if (!myFile.exists()) {
			myFile.mkdirs();
		}
	}

	public static void showAllFiles() {
		FileHandling.createAFolder();
		System.out.println("Displaying all files in ascending order\n");
		List<String> listOfFiles = FileHandling.listAllFiles("store", 0, new ArrayList<String>());
		Collections.sort(listOfFiles);
//
		listOfFiles.stream().forEach(System.out::println);
	}

	public static List<String> listAllFiles(String path, int flag, List<String> fileList) {
		File myDir = new File(path);
		File[] myFiles = myDir.listFiles();
		List<File> filesList = Arrays.asList(myFiles);

		Collections.sort(filesList);

		if (myFiles != null && myFiles.length > 0) {
			for (File file : filesList) {

				System.out.print(" ".repeat(flag * 2));

				if (file.isDirectory()) {
					System.out.println("`== " + file.getName());

					fileList.add(file.getName());
					listAllFiles(file.getAbsolutePath(), flag + 1, fileList);
				} else {
					System.out.println("==> " + file.getName());
					fileList.add(file.getName());
				}
			}
		} else {
			System.out.print(" ".repeat(flag * 2));
			System.out.println("==> Empty Directory");
		}
		System.out.println();
		return fileList;
	}

	public static void createNewFiles(String fileName, Scanner sc) {
		FileHandling.createAFolder();
		Path pathToFile = Paths.get("./store/" + fileName);
		try {
			Files.createDirectories(pathToFile.getParent());
			Files.createFile(pathToFile);
			System.out.println(fileName + " created successfully");

			System.out.println("Do you like to add content to the file? (Y/N)");
			String flag = sc.next().toLowerCase();

			sc.nextLine();
			if (flag.equals("y")) {
				System.out.println("\n\nInput content and press enter\n");
				String content = sc.nextLine();
				Files.write(pathToFile, content.getBytes());
				System.out.println("\nContent written Successfully @ " + fileName);
			}

		} catch (IOException e) {
			System.out.println("File creation Failed " + fileName);
			System.out.println(e.getClass().getName());
		}
	}

	public static List<String> displayFileLocations(String fileName, String path) {
		List<String> fileListNames = new ArrayList<>();
		FileHandling.searchFiles(path, fileName, fileListNames);

		if (fileListNames.isEmpty()) {
			System.out.println("\n\n No File found with this name \"" + fileName + "\" \n\n");
		} else {
			System.out.println("\n\nFound file at below location(s):");

			List<String> files = IntStream.range(0, fileListNames.size())
					.mapToObj(index -> (index + 1) + ": " + fileListNames.get(index)).collect(Collectors.toList());

			files.forEach(System.out::println);
		}

		return fileListNames;
	}

	public static void searchFiles(String path, String fileName, List<String> fileListNames) {
		File myDir = new File(path);
		File[] myFiles = myDir.listFiles();
		List<File> myFileList = Arrays.asList(myFiles);

		if (myFiles != null && myFiles.length > 0) {
			for (File file : myFileList) {

				if (file.getName().startsWith(fileName)) {
					fileListNames.add(file.getAbsolutePath());
				}

				
				if (file.isDirectory()) {
					searchFiles(file.getAbsolutePath(), fileName, fileListNames);
				}
			}
		}
	}

	public static void deleteAllFiles(String path) {

		File myFiles = new File(path);
		File[] fileList = myFiles.listFiles();

		if (fileList != null && fileList.length > 0) {
			for (File file : fileList) {

				String fileName = file.getName() + " at " + file.getParent();
				if (file.isDirectory()) {
					deleteAllFiles(file.getAbsolutePath());
				}

				if (file.delete()) {
					System.out.println(fileName + "/nsuccessfully deleted");
				} else {
					System.out.println("Something went wrong " + fileName);
				}
			}
		}

		String file = myFiles.getName() + " at " + myFiles.getParent();
		if (myFiles.delete()) {
			System.out.println(file + "/nsuccessfully deleted");
		} else {
			System.out.println("Something went wrong" + file);
		}
	}
}
