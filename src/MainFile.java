import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFile {
    public static void main(String[] args) {
//        String filePath = ".\\gitignore";
//
//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
//
//        File dir = new File("./src/com/urise/webapp");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        List<File> listFile = new ArrayList<>();
        searchFiles(new File("C:/java/BaseJava/basejava/src/"), listFile);
        for (File file1 : listFile) {
            System.out.println(file1.getName());
        }
    }

    private static void searchFiles(File file, List<File> listFile) {
        if (!file.isFile()) {
            File[] directoryFiles = file.listFiles();
            if (directoryFiles != null) {
                for (File file1 : directoryFiles) {
                    if (!file1.isFile()) {
                        searchFiles(file1, listFile);
                    } else {
                        System.out.println(file1.getName());
                        listFile.add(file1);
                    }
                }
            }
        }
    }
}
