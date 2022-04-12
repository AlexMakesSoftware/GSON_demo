package me.alexconnell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    private static File filename = Paths.get("target","output.json").toFile();

    public static void main( String[] args ) throws IOException
    {
        //serialise();
        deserialise();
    }


    public static void serialise() {
        CustomRandom random = new CustomRandom();
        AnimalStates states = AnimalStates.build(1024,6000, random);
        Gson gson = new Gson();
        String json = gson.toJson(states);
        try(FileWriter fw = new FileWriter(filename.toString())) {
            fw.write(json);
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }

    public static void deserialise() throws IOException {
        String json = FileUtils.readFileToString(filename, Charset.defaultCharset());
        Gson gson = new Gson();
        AnimalStates states = gson.fromJson(json, AnimalStates.class);
        CustomRandom random = new CustomRandom();
        states.setRandom(random); // restore the transient field manually.
        states.diagnostic();
    }
}
