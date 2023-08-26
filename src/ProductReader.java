import java.util.ArrayList;
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser j = new JFileChooser();
        ArrayList<String> lines = new ArrayList<>();
        File selectedFile;
        String rec = "";


        final int FIELDS_LENGTH = 4;
        String id, name, desc;
        double cost;

        try{
            File workingDir = new File(System.getProperty("user.dir"));
            j.setCurrentDirectory(workingDir);

            if(j.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                selectedFile = j.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int count = 0;
                while(reader.ready()){
                    rec = reader.readLine();
                    lines.add(rec);
                    count++;
                    System.out.printf("\nLine %4d %-60s", count, rec);
                }
                reader.close();
                System.out.println("\nData Read Complete");

                System.out.printf("\n%-8s%-25s%-25s%6s\n", "ID#", "Name", "Description", "Cost");
                for(int i = 0;i<70;i++){
                    System.out.print("=");
                }

                String[] sec;
                for(String l:lines) {
                    sec = l.split(",");

                    if (sec.length == FIELDS_LENGTH) {
                        id = sec[0].trim();
                        name = sec[1].trim();
                        desc = sec[2].trim();
                        cost = Double.parseDouble(sec[3].trim());
                        System.out.printf("\n%-8s%-25s%-25s%,.2f", id, name, desc, cost);
                    } else {
                        System.out.println("Records may be corrupt: ");
                        System.out.println(l);
                    }
                }
            }else{
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found!");

        } catch (IOException e) {
            System.out.println("Error saving list to file: " + e.getMessage());
        }

    }
}
