import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VirtualKeyRepositoriesApp {

    public final static String VIRTUAL_REPOSITORY_FOLDER="VirtualRepository";
    public final static String MENU_ALL_FILES = "1";
    public final static String MENU_FILE_HANDLING = "2";
    public final static String MENU_EXIT = "3";

    public final static String SUB_MENU_FILE_CREATE = "21";
    public final static String SUB_MENU_FILE_DELETE = "22";
    public final static String SUB_MENU_FILE_SEARCH = "23";
    public final static String SUB_MENU_FILE_EXIT = "24";

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String menuOption;

        boolean showSubMenu = false;
        String chosenOption="";

        VirtualKeyRepository virtualKeyRepository = new VirtualKeyRepository(VIRTUAL_REPOSITORY_FOLDER);
        do {
            printMenu(showSubMenu);
            chosenOption = chooseMenuOption(chosenOption, scan);
            showSubMenu = chosenOption.substring(0,1).equals(MENU_FILE_HANDLING) &&
                        !chosenOption.equals(SUB_MENU_FILE_EXIT);

            switch (chosenOption) {
                case MENU_ALL_FILES:
                    ArrayList<File> files = virtualKeyRepository.getAllFileInAscendingOrder();
                    printFiles(files);
                    chosenOption="";
                    break;
                case MENU_FILE_HANDLING:
                    continue;
                case SUB_MENU_FILE_CREATE:
                    addFile(virtualKeyRepository, scan);
                    break;
                case SUB_MENU_FILE_DELETE:
                    deleteFile();
                    break;
                case SUB_MENU_FILE_SEARCH:
                    searchFile();
                    break;
                case SUB_MENU_FILE_EXIT:
                    chosenOption="";
                    break;
                default:
            }

        } while (!chosenOption.equals(MENU_EXIT));

        scan.close();
        scan = null;
    }


    public static String chooseMenuOption(String subMenu, Scanner scan) {
        String chosenOption = "";

        do {
            System.out.print("Choose option: ");
            if(scan.hasNextLine()){
                chosenOption = scan.nextLine();
            }
        } while ( !(
            (chosenOption.equals(MENU_ALL_FILES) && subMenu.equals("")) ||
            (chosenOption.equals(MENU_FILE_HANDLING) && subMenu.equals(""))||
            (chosenOption.equals(MENU_EXIT) && subMenu.equals("")) ||
            chosenOption.equals(SUB_MENU_FILE_CREATE) ||
            chosenOption.equals(SUB_MENU_FILE_DELETE) ||
            chosenOption.equals(SUB_MENU_FILE_SEARCH) ||
            chosenOption.equals(SUB_MENU_FILE_EXIT)
        )
        ); // no need for == true
        System.out.println("");

        return chosenOption;
    }


    public static void printMenu(boolean subMenu){
        System.out.println("*** Welcome to Virtual Key for Your Repositories");
        System.out.println("*** Author: Cleber Miranda Barbosa\n");
        System.out.println("*** Options Menu");
        System.out.println("1: All file in Ascending Order");
        System.out.println("2: File Handling Options");
        if(subMenu) {
            System.out.println("    21: Add a file");
            System.out.println("    22: Delete file");
            System.out.println("    23: Search file");
            System.out.println("    24: Exit from sub option");
        }
        System.out.println("3: Exit main option");

    }

    public static void printFiles(ArrayList<File> files) {
        System.out.println("\nList of files:");
        files.forEach(file -> System.out.println(file.getPath()));
        System.out.println("");
    }


    public static void addFile(VirtualKeyRepository virtualKeyRepository, Scanner scan) {
        System.out.println("\n*Add File");

        System.out.print("Choose a file at current folder: ");
        String fileName = scan.nextLine();

        System.out.println("\n**Directory list: ");
        virtualKeyRepository.getFoldersList().forEach(System.out::println);

        System.out.print("\nType one of the directories above: ");
        String folder = scan.nextLine();

        virtualKeyRepository.addFile(fileName, folder);
    }

    public static void deleteFile() {
        System.out.println("Delete File");

        System.out.println("");
    }

    public static void searchFile() {
        System.out.println("Search File");

        System.out.println("");
    }




}
