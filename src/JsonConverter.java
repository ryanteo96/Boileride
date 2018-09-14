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

public class JsonConverter {
    public static Object toObject(Reader in, String target) {
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(in);
            // Just some trial, still haven't figure out how to get it to the arguments
            Iterator<String> it = json.keySet().iterator();
            while (it.hasNext()){
                System.out.println(it.next());
            }
            Class c = Class.forName(target);
            Constructor ct[] = c.getConstructors();
            // testing constants, it can run with the same number of arguments
            Object obj = ct[0].newInstance("a","b","c","d");
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
