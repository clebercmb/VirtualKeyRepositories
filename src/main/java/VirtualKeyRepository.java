import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class VirtualKeyRepository {

    private HashMap<String, String> repository;
    private String rootFolder;

    private ArrayList<String> folders = new ArrayList<>();
    private ArrayList<File> files = new ArrayList<>();

    public  VirtualKeyRepository(String rootFolder) throws IOException {
        this.rootFolder = rootFolder;
        loadFilesAndFolders(rootFolder);
    }

    public ArrayList<File> getAllFileInAscendingOrder() {
        files.sort( (f1, f2) -> (f1.getName()+f1.getParentFile().getPath()).compareTo(f2.getName()+f2.getParentFile().getPath()));
        return files;
    }

    public ArrayList<String> getFoldersList() {
        return folders;
    }

    public void addFile(String filePathToBeAdded, String virtualFolder)  {
        try {

            Path source = Paths.get(filePathToBeAdded);
            Path target = Paths.get(virtualFolder, filePathToBeAdded);

            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

            folders = new ArrayList<>();
            files = new ArrayList<>();

            loadFilesAndFolders(rootFolder);

        } catch (NoSuchFileException e) {
            System.out.println("File or Destination Virtual Folder does not exist\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadFilesAndFolders(String root) throws IOException {

        File path = new File(root);
        for(File file: Objects.requireNonNull(path.listFiles())) {
            //System.out.println(file.);
            if (file.isDirectory()) {
                folders.add(file.getPath());
                loadFilesAndFolders(file.getPath());
            } else {
                files.add(file);

            }
        }
        folders.add(path.getPath());

        folders.sort(String::compareTo);

    }

}
