import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class VirtualKeyRepository {

    private HashMap<String, String> repository;
    private String rootFolder;

    private ArrayList<String> folders = new ArrayList<>();
    private ArrayList<File> files = new ArrayList<>();

    public  VirtualKeyRepository(String rootFolder) {
        this.rootFolder = rootFolder;
        loadFilesAndFolders(rootFolder);

        System.out.println("*****Folders");
        folders.forEach(System.out::println);

        System.out.println("*****Files");
        files.sort( (f1, f2) -> f1.getName().compareTo(f2.getName()));
        files.forEach(System.out::println);


    }

    public ArrayList<String> getAllFileInAscendingOrder() {
        Path path = Paths.get(rootFolder);

        File f = new File(rootFolder);

        //System.out.println("***File: ");
        //Arrays.stream(Objects.requireNonNull(f.listFiles())).forEach(e-> System.out.println(e.getName()));
        //System.out.println("***Path: :");
        //path.forEach(e-> System.out.println(e.getFileName()));
        return null;
    }


    private void loadFilesAndFolders(String root) {
        File path = new File(root);

        for(File file: Objects.requireNonNull(path.listFiles())) {
            //System.out.println(file.getPath());
            if (file.isDirectory()) {
                folders.add(file.getPath());
                loadFilesAndFolders(file.getPath());
            } else {
                files.add(file);

            }
        }

    }

}
