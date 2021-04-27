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

    public static void main(String[] args) {

        String menuOption;

        boolean showSubMenu = false;
        String chosenOption="";

        VirtualKeyRepository virtualKeyRepository = new VirtualKeyRepository(VIRTUAL_REPOSITORY_FOLDER);
        do {
            printMenu(showSubMenu);
            chosenOption = chooseMenuOption("");
            showSubMenu = chosenOption.substring(0,1).equals(MENU_FILE_HANDLING) &&
            !chosenOption.equals(SUB_MENU_FILE_EXIT);

            switch (chosenOption) {
                case MENU_ALL_FILES:
                    System.out.println("Hey");
                    virtualKeyRepository.getAllFileInAscendingOrder();
                    break;
                case MENU_FILE_HANDLING:
                    continue;
                default:
            }

        } while (!chosenOption.equals(MENU_EXIT));

    }


    public static String chooseMenuOption(String subMenu) {
        String chosenOption = "";
        do {
            System.out.print("Choose option: ");
            Scanner scan = new Scanner(System.in);
            if(scan.hasNextLine()){
                chosenOption = scan.nextLine();
            }
        } while ( !(
            chosenOption.equals(MENU_ALL_FILES) ||
            chosenOption.equals(MENU_FILE_HANDLING) ||
            chosenOption.equals(MENU_EXIT) ||
            chosenOption.equals(SUB_MENU_FILE_CREATE) ||
            chosenOption.equals(SUB_MENU_FILE_DELETE) ||
            chosenOption.equals(SUB_MENU_FILE_SEARCH) ||
            chosenOption.equals(SUB_MENU_FILE_EXIT)
        )
        ); // no need for == true
        return subMenu+chosenOption;
    }


    public static void printMenu(boolean subMenu){
        Scanner in = new Scanner(System.in);

        System.out.println("*** Welcome to Virtual Key for Your Repositories");
        System.out.println("*** Author: Cleber Miranda Barbosa\n");
        System.out.println("*** Options Menu");
        System.out.println("1: All file in Ascending Order");
        System.out.println("2: File Handling Options");
        if(subMenu) {
            System.out.println("    21: Create file");
            System.out.println("    22: Delete file");
            System.out.println("    23: Search file");
            System.out.println("    24: Exit from sub option");
        }
        System.out.println("3: Exit main option");

    }


}
