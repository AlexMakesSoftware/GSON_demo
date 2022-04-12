package me.alexconnell;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");

    @Override
    public LocalDate read(JsonReader reader) throws IOException {
        String str = reader.nextString();
        return LocalDate.parse(str);
    }

    
    @Override
    public void write(JsonWriter writer, LocalDate date) throws IOException {        
        writer.value(formatter.format(date));        
    }

}
