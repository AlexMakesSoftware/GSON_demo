package me.alexconnell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        serialise();
        deserialise();
    }

    public static void serialise() {
        CustomRandom random = new CustomRandom();
        AnimalStates states = AnimalStates.build(100,300, random);
        Gson gson = init();
        String json = gson.toJson(states);
        try(FileWriter fw = new FileWriter(filename.toString())) {
            fw.write(json);
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }

    public static void deserialise() throws IOException {
        String json = FileUtils.readFileToString(filename, Charset.defaultCharset());
        Gson gson = init();
        AnimalStates states = gson.fromJson(json, AnimalStates.class);
        CustomRandom random = new CustomRandom();
        states.setRandom(random); // restore the transient field manually.
        states.diagnostic();
    }


    // https://www.tutorialspoint.com/gson/gson_custom_adapters.htm
    public static Gson init() {
        GsonBuilder builder = new GsonBuilder(); 
        builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter()); 
        return builder.create(); 
    }
}
