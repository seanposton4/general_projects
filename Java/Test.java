import java.util.*;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));

            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

            Scanner input = new Scanner(new File("input.txt"));
            File fileout = new File("output.txt");
            if (fileout.createNewFile()) {}
            FileWriter writer = new FileWriter(fileout);
            while (input.hasNext()) {
                writer.write(input.next() + "\n");
            }

            writer.close();

        }

        catch (FileNotFoundException e) {

        }
        catch (IOException e) {

        }
    }
}