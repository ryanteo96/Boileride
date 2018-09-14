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

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonConverter {
    public static Object toObject(Reader in, String target) {
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(in);
            Class c = Class.forName(target);
            Constructor ct[] = c.getConstructors();
            Object obj = ct[0].newInstance();
            for (Method m : c.getMethods()) {
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

    public static Object toJson(Object obj) {
        return null;
    }
}
