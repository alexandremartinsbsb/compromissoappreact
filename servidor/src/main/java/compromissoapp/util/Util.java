package compromissoapp.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import compromissoapp.util.error.UtilException;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Util {

    /**
     * Cria um json-string que representa os dados fornecidos pelo Map.
     * 
     * @param dataMap;
     *            pode ser nulo / vazio, mas resultará em uma Cadeia de caracteres
     *            vazia; Caso contrário, deve ser Strings mapeados para Objetos
     *            arbitrários.
     * @return o json-string representa os dados, ou uma empty-string; ira retornar
     *         null.
     */
    public static String GET_MAP_PARA_JSON_STRING(Map<String, Object> dataMap) throws UtilException {

        /* NOTE: Por Jackson-Dox, o mapa deve ser de tipo <String, Object> */

        if (dataMap == null || dataMap.isEmpty()) {
            return new String(); // não quer retornar nulo para evitar erro.
        }

        Writer writer = new StringWriter();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, dataMap);
        } catch (Throwable t) {
            String msg = "incapaz de converter dados do Map em json: " + dataMap.toString();
            throw new UtilException(msg);
        }
        return writer.toString();
    }

    /**
     * Cria um mapa representado pelos dados do json fornecidos.
     * 
     * @param jsonResponse;
     *            pode ser nulo / vazio, mas resultará em um mapa vazio;
     * @return Strings mapeados para Objetos arbitrários
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> GET_JSON_STRING_PARA_MAP(String jsonResponse) throws UtilException {

        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            return new HashMap<String, Object>(); // não quer retornar nulo para evitar erro.
        }
        jsonResponse = jsonResponse.trim();

        Map<String, Object> result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object o = mapper.readValue(jsonResponse, Object.class);
            if (o instanceof Map) {
                result = (Map<String, Object>) o;
                System.out.println(result);
            }

        } catch (Throwable t) {
            String msg = "incapaz de mapear json-response: " + jsonResponse;
            throw new UtilException(msg, t);
        }

        // não quer retornar nulo para evitar erro.
        if (result == null) {
            result = new LinkedHashMap<String, Object>();
        }
        return result;
    }
}
