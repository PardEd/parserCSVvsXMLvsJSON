import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserJson {

    static List<Employee> list = new ArrayList<> ();

    public static String readString(String fileName) {
        StringBuilder builder = new StringBuilder ();
        try (BufferedReader reader = new BufferedReader (new FileReader (fileName))) {
            while (reader.ready ()) {
                builder.append (reader.readLine ());
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
        jsonToList (builder.toString ());
        return builder.toString ();
    }

    public static List<Employee> jsonToList(String json) {
        JsonArray jsonElements = (JsonArray) JsonParser.parseString(json);
        Gson gson = new GsonBuilder ().create ();
        for ( Object jsonObject : jsonElements ) {
            Employee employee = gson.fromJson ((JsonObject) jsonObject, Employee.class);
            list.add (employee);
            System.out.println (employee);
        }
        return list;
    }
}