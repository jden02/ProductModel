import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        boolean repeat;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> products = new ArrayList<String>();

        do{
            String ID = SafeInput.getNonZeroLenString(sc,"Enter product ID");
            String name = SafeInput.getNonZeroLenString(sc, "Enter product name");
            String desc = SafeInput.getNonZeroLenString(sc,"Enter product description");
            double cost = SafeInput.getDouble(sc,"Enter product cost");
            String formatedInfo = ID + ", " + name + ", " + desc + ", " + cost;
            products.add(formatedInfo);

            repeat = SafeInput.getYNConfirm(sc, "Do you want to add another product?");
        }while(repeat);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + SafeInput.getNonZeroLenString(sc, "Enter file name") + ".txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String item : products) {
                writer.write(item, 0, item.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("List saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving list to file: " + e.getMessage());
        }

    }
}