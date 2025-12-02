import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main(String[] args) {

    JFileChooser chooser = new JFileChooser();
    File selectedFile;
    String rec = "";
    if (args.length < 1) {
        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                int wordLength = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                    wordLength += rec.split("\\W").length;
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nFile Information");
                System.out.println("--------------------");
                System.out.println("File name: " + selectedFile.getName());
                System.out.println("Number of lines: " + line);
                System.out.println("Number of characters: " + selectedFile.length());
                System.out.println("Number of words: " + wordLength);


            } else  // User closed the chooser without selecting a file
            {
                System.out.println("No file selected! \nExiting. Run the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    } else {
    try{
        selectedFile = new File(args[0]);
        Path file = selectedFile.toPath();
        InputStream in =
                new BufferedInputStream(Files.newInputStream(file, CREATE));
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(in));

        int line = 0;
        int wordLength = 0;
        while (reader.ready()) {
            rec = reader.readLine();
            line++;
            System.out.printf("\nLine %4d %-60s ", line, rec);
            wordLength += rec.split("\\W").length;
        }
        reader.close(); // must close the file to seal it and flush buffer
        System.out.println("\n\nFile Information");
        System.out.println("--------------------");
        System.out.println("File name: " + selectedFile.getName());
        System.out.println("Number of lines: " + line);
        System.out.println("Number of characters: " + selectedFile.length());
        System.out.println("Number of words: " + wordLength);

    } catch (NoSuchFileException e) {
        System.out.println("File not found!");
        e.printStackTrace();
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }

    }
}