import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
// @author Moss LaFountain

public class DataSaver
{

    public static void main(String[] args)
    {
        ArrayList<String> recs = new ArrayList<>();
        boolean done = false;
        while(!done) {
            Scanner in = new Scanner(System.in);
            String fName = SafeInput.getNonZeroLenString(in, "What is your first name?");
            String lName = SafeInput.getNonZeroLenString(in, "What is your last name?");
            String ID = SafeInput.getRegExString(in, "What is your ID number?", "\\d{6}");
            String email = SafeInput.getRegExString(in, "What is your email?", "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            String yearBirth = SafeInput.getRegExString(in, "What is your birth year?", "\\d{4}");
            recs.add(fName + ", " + lName + ", " + ID + ", " + email + ", " + yearBirth);
            done = SafeInput.getYNConfirm(in, "Are you done?");
        }


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\newdata.txt");

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}

