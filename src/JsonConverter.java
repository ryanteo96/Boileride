/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * JsonConverter converts string to json object
 *
 * @version September 10, 2018
 */
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;

import DTO.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import com.google.gson.Gson;

public class JsonConverter {
    public static Object toObject(Reader in, Class target) {
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(in);

            Constructor ct[] = target.getConstructors();
            Object obj = ct[0].newInstance();
            for (Method m : target.getMethods()) {
                Anno anno = m.getAnnotation(Anno.class);
                if (anno == null) continue;
                m.invoke(obj, json.get(anno.name()));
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object obj) {

        // found handy lib, maybe just use it instead of writing ourselves.
        // JsonConverter can be removed.
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
