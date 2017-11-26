package compromissoapp;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import compromissoapp.dao.Firebase;
import compromissoapp.modelo.FirebaseResponse;
import compromissoapp.modelo.Usuario;
import compromissoapp.util.error.FirebaseException;
import compromissoapp.util.error.UtilException;

public class Teste {

    public static void main(String[] args)
            throws FirebaseException, JsonParseException, JsonMappingException, IOException, UtilException {

        // create the firebase
        Firebase firebase = new Firebase(Usuario.class.getSimpleName().toLowerCase());

        // "DELETE" (the fb4jDemo-root)
        FirebaseResponse response = firebase.delete();

        // "PUT" (test-map into the fb4jDemo-root)
        Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
        

        // "GET" (the fb4jDemo-root)
        response = firebase.get();
        System.out.println("\n\nResult of GET:\n" + response);
        System.out.println("\n");

        // "PUT" (test-map into a sub-node off of the fb4jDemo-root)
        dataMap = new LinkedHashMap<String, Object>();
        dataMap.put("nome", "Alexandre Martins da Silva");
        dataMap.put("email", "alexandre.martinsbsb@gmail.com");
        dataMap.put("login", "alexandre.martinsbsb");
        dataMap.put("senha", "123456");
        dataMap.put("situacao", Boolean.TRUE);
        Map<String, Object> dataMap2 = new LinkedHashMap<String, Object>();
        dataMap2.put("nome", "Alexandre Martins da Silva");
        dataMap2.put("email", "alexandre.martinsbsb@gmail.com");
        dataMap2.put("login", "alexandre.martinsbsb");
        dataMap2.put("senha", "123456");
        dataMap2.put("situacao", Boolean.TRUE);
        System.out.println("\n\nResult of PUT (for the test-PUT):\n" + response);
        System.out.println("\n");

        // "GET" (the test-PUT)
        response = firebase.get("test-PUT");
        System.out.println("\n\nResult of GET (for the test-PUT):\n" + response);
        System.out.println("\n");

        // "POST" (test-map into a sub-node off of the fb4jDemo-root)
        response = firebase.post(dataMap);
        response = firebase.post(dataMap2);
        System.out.println("\n\nResult of POST (for the test-POST):\n" + response);
        System.out.println("\n");

       

        System.out.println(response.getCorpo().get("Key_1"));

    }

}
