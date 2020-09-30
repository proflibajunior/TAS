package br.edu.materdei.tas.desktop.service;

import br.edu.materdei.tas.desktop.exception.ResponseException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BackendService {    
    
    private static String callBackend(String recurso, String method, JSONObject json) 
            throws IOException, ResponseException {
        
        Properties properties = new Properties();

        properties.load(BackendService.class.getResourceAsStream("/project.properties"));
        String urlSaaS = properties.getProperty("urlsaas");

        URL url = new URL(urlSaaS + recurso);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod(method);

            connection.setRequestProperty("Content-Language", "pt-BR");  			
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            if (json != null) {
                connection.setRequestProperty("Content-Length", "" + Integer.toString(json.toString().getBytes().length));
                connection.setRequestProperty("Content-Type", "application/json");

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                bw.write(json.toString());
                bw.flush();
                bw.close();
            } else {
                connection.setRequestProperty("Content-Length", "0");
            }

            connection.connect();
            int status = connection.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line + "\n");
            }
            br.close();

            //Respostas de sucesso ficam entre 200 e 226
            if ((status < 200)||(status > 226)) {                
                 throw new ResponseException(response.toString());
            }

            return response.toString();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        
    }    
    public static void testBackend(String recurso) throws IOException, ResponseException {
        callBackend(recurso, "POST", null);
    }
    public static Object[] findAll(String recurso) throws IOException, ResponseException {
        String json = callBackend(recurso, "GET", null);

        JSONArray jsonArray = JSONArray.fromObject(json);

        return jsonArray.toArray();
    }
    public static void deleteById(String recurso) throws IOException, ResponseException {
        callBackend(recurso, "DELETE", null);
    }
    public static void save(String recurso, JSONObject json) throws IOException, ResponseException {
        callBackend(recurso, "POST", json);
    }
}
