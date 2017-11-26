package compromissoapp.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import compromissoapp.modelo.FirebaseResponse;
import compromissoapp.modelo.enun.MetodoRest;
import compromissoapp.util.Util;
import compromissoapp.util.error.FirebaseException;
import compromissoapp.util.error.UtilException;

public class Firebase {

    public static final String EXTENSAO_JSON = ".json";
    public static final String BARRA         = "/";
    public static final String INTERROGACAO  = "?";
    public static final String IGUAL         = "=";
    public static final String UTF_8         = "UTF-8";
    public static final String E_COMERCIAL   = "&";
    public static final String AUTH          = "auth";
    public static final String NO_CONTENT    = "No Content";
    public static final String OK            = "OK";

    // exemplo: 'https://nomeprojeto.firebase.com/tabela'
    public static final String FIREBASE_BASE_URL = "https://compromissoappreact.firebaseio.com";

    private String              tabela = new String();
    private String              token  = null;
    private List<NameValuePair> query  = new ArrayList<>();

    public Firebase(String tabela) throws FirebaseException {
        this.tabela = tabela;
        this.query = new ArrayList<NameValuePair>();
    }

    public Firebase(String tabela, String token) throws FirebaseException {
        this.tabela = tabela;
        this.token = token;
        this.query = new ArrayList<NameValuePair>();
    }

    public FirebaseResponse get() throws FirebaseException, UnsupportedEncodingException {
        return this.get(null);
    }

    public FirebaseResponse get(String caminho) throws FirebaseException, UnsupportedEncodingException {

        String url = this.buildUrlCompletaCaminhoRelativo(caminho);
        HttpGet requisisao = new HttpGet(url);
        HttpResponse resposta = this.fazerRequest(requisisao);

        return this.processarResposta(MetodoRest.GET, resposta);
    }

    public FirebaseResponse caminho(Map<String, Object> dado)
            throws FirebaseException, UtilException, UnsupportedEncodingException {
        return this.caminho(null, dado);
    }

    public FirebaseResponse caminho(String caminho, Map<String, Object> dado)
            throws FirebaseException, UtilException, UnsupportedEncodingException {
        String url = this.buildUrlCompletaCaminhoRelativo(caminho);
        HttpPatch requisisao = new HttpPatch(url);
        requisisao.setEntity(this.buildEntityParaDataMap(dado));
        HttpResponse resposta = this.fazerRequest(requisisao);

        return this.processarResposta(MetodoRest.PATCH, resposta);
    }

    public FirebaseResponse caminho(String dadosJson) throws UnsupportedEncodingException, FirebaseException {
        return this.caminho(null, dadosJson);
    }

    public FirebaseResponse caminho(String caminho, String dadoJson)
            throws UnsupportedEncodingException, FirebaseException {
        String url = this.buildUrlCompletaCaminhoRelativo(caminho);
        HttpPatch requisisao = new HttpPatch(url);
        requisisao.setEntity(this.buildEntityParaDataMap(dadoJson));
        HttpResponse resposta = this.fazerRequest(requisisao);

        return this.processarResposta(MetodoRest.PATCH, resposta);
    }

    public FirebaseResponse put(Map<String, Object> dado)
            throws UtilException, FirebaseException, UnsupportedEncodingException {
        return this.put(null, dado);
    }

    public FirebaseResponse put(String caminho, Map<String, Object> dado)
            throws UtilException, FirebaseException, UnsupportedEncodingException {

        String url = this.buildUrlCompletaCaminhoRelativo(caminho);
        HttpPut requisisao = new HttpPut(url);
        requisisao.setEntity(this.buildEntityParaDataMap(dado));
        HttpResponse resposta = this.fazerRequest(requisisao);

        return this.processarResposta(MetodoRest.PUT, resposta);
    }

    public FirebaseResponse put(String dadoJson) throws FirebaseException, UnsupportedEncodingException {
        return this.put(null, dadoJson);
    }

    public FirebaseResponse put(String caminho, String dadoJson)
            throws FirebaseException, UnsupportedEncodingException {

        String url = this.buildUrlCompletaCaminhoRelativo(caminho);
        HttpPut requisisao = new HttpPut(url);
        requisisao.setEntity(this.buildEntityParaDataMap(dadoJson));
        HttpResponse resposta = this.fazerRequest(requisisao);

        return this.processarResposta(MetodoRest.PUT, resposta);
    }

    public FirebaseResponse post(Map<String, Object> dado)
            throws UtilException, FirebaseException, UnsupportedEncodingException {
        return this.post(null, dado);
    }

    public FirebaseResponse post(String caminho, Map<String, Object> dado)
            throws UtilException, FirebaseException, UnsupportedEncodingException {

        String url = this.buildUrlCompletaCaminhoRelativo(caminho);
        HttpPost requisisao = new HttpPost(url);
        requisisao.setEntity(this.buildEntityParaDataMap(dado));
        HttpResponse resposta = this.fazerRequest(requisisao);

        return this.processarResposta(MetodoRest.POST, resposta);
    }

    public FirebaseResponse post(String dadoJson) throws FirebaseException, UnsupportedEncodingException {
        return this.post(null, dadoJson);
    }

    public FirebaseResponse post(String caminho, String dadoJson)
            throws FirebaseException, UnsupportedEncodingException {

        String url = this.buildUrlCompletaCaminhoRelativo(caminho);
        HttpPost requisisao = new HttpPost(url);
        requisisao.setEntity(this.buildEntityParaDataMap(dadoJson));
        HttpResponse resposta = this.fazerRequest(requisisao);

        return this.processarResposta(MetodoRest.POST, resposta);
    }

    public Firebase adicionaQuery(String query, String parametro) {
        this.query.add(new BasicNameValuePair(query, parametro));
        return this;
    }

    public FirebaseResponse delete() throws FirebaseException, UnsupportedEncodingException {
        return this.delete(null);
    }

    public FirebaseResponse delete(String caminho) throws FirebaseException, UnsupportedEncodingException {

        String url = this.buildUrlCompletaCaminhoRelativo(caminho);
        HttpDelete requisisao = new HttpDelete(url);
        HttpResponse resposta = this.fazerRequest(requisisao);

        return this.processarResposta(MetodoRest.DELETE, resposta);
    }

    private StringEntity buildEntityParaDataMap(Map<String, Object> dataMap) throws FirebaseException, UtilException {

        String jsonData = Util.GET_MAP_PARA_JSON_STRING(dataMap);

        return this.buildEntityParaDataMap(jsonData);
    }

    private StringEntity buildEntityParaDataMap(String dadoJson) throws FirebaseException {

        StringEntity result = null;
        try {

            result = new StringEntity(dadoJson);

        } catch (Throwable t) {

            String msg = "incapaz de criar entidade a partir de dados; os dados foram: ".concat(dadoJson);
            throw new FirebaseException(msg, t);

        }

        return result;
    }

    private String buildUrlCompletaCaminhoRelativo(String caminho) throws UnsupportedEncodingException {

        if (caminho == null) {
            caminho = new String();
        }
        caminho = caminho.trim();
        if (!caminho.isEmpty() && !caminho.startsWith(BARRA)) {
            caminho = BARRA.concat(caminho);
        }
        String url = FIREBASE_BASE_URL.concat(BARRA).concat(this.tabela).concat(caminho).concat(EXTENSAO_JSON);

        if (query != null) {
            url += INTERROGACAO;
            Iterator<NameValuePair> it = query.iterator();
            NameValuePair e;
            while (it.hasNext()) {
                e = it.next();
                url += e.getName().concat(IGUAL).concat(URLEncoder.encode(e.getValue(), UTF_8)).concat(E_COMERCIAL);
            }
        }

        if (token != null) {
            if (query != null) {
                url += AUTH.concat(IGUAL).concat(token);
            } else {
                url += INTERROGACAO.concat(AUTH).concat(IGUAL).concat(token);
            }
        }

        if (url.lastIndexOf(E_COMERCIAL) == url.length()) {
            StringBuilder str = new StringBuilder(url);
            str.deleteCharAt(str.length());
            url = str.toString();
        }

        return url;
    }

    private HttpResponse fazerRequest(HttpRequestBase requisisao) throws FirebaseException {

        HttpResponse resposta = null;

        if (requisisao == null) {

            String msg = "requisisao nao pode ser nulo";
            throw new FirebaseException(msg);
        }

        try {
            HttpClient cliente = HttpClients.createDefault();
            resposta = cliente.execute(requisisao);

        } catch (Throwable t) {

            String msg = "incapaz de receber a resposta(".concat(requisisao.getMethod()).concat(") @ ")
                    .concat(requisisao.getURI().toString());
            throw new FirebaseException(msg, t);

        }

        return resposta;
    }

    private FirebaseResponse processarResposta(MetodoRest metodoRest, HttpResponse respostaHttp)
            throws FirebaseException {

        FirebaseResponse resposta = null;

        if (metodoRest == null) {

            String msg = "método não pode ser nulo";
            throw new FirebaseException(msg);
        }
        if (respostaHttp == null) {

            String msg = "respostaHttp método não pode ser nulo";
            throw new FirebaseException(msg);
        }

        // obter a entidade de resposta
        HttpEntity entity = respostaHttp.getEntity();

        // obter o codigo da resposta
        int codigo = respostaHttp.getStatusLine().getStatusCode();

        // definir o sucesso da resposta
        boolean sucesso = false;
        switch (metodoRest) {

            case DELETE:
                if (respostaHttp.getStatusLine().getStatusCode() == 204
                        && NO_CONTENT.equalsIgnoreCase(respostaHttp.getStatusLine().getReasonPhrase())) {
                    sucesso = true;
                }
                break;
            case PATCH:
            case PUT:
            case POST:
            case GET:
                if (respostaHttp.getStatusLine().getStatusCode() == 200
                        && OK.equalsIgnoreCase(respostaHttp.getStatusLine().getReasonPhrase())) {
                    sucesso = true;
                }
                break;
            default:
                break;
        }

        // obter o corpo da resposta
        Writer writer = new StringWriter();
        if (entity != null) {

            try {

                InputStream is = entity.getContent();
                char[] buffer = new char[1024];
                Reader reader = new BufferedReader(new InputStreamReader(is, UTF_8));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }

            } catch (Throwable t) {

                String msg = "incapaz de ler o conteúdo da resposta; leia até este ponto: '".concat(writer.toString())
                        .concat("'");
                writer = new StringWriter();
                throw new FirebaseException(msg, t);
            }
        }

        // converte o corpo da resposta para Map
        Map<String, Object> corpo = null;
        try {

            corpo = Util.GET_JSON_STRING_PARA_MAP(writer.toString());

        } catch (UtilException jue) {

            String msg = "incapaz de converter o corpo da resposta no mapa; O corpo de resposta era: '"
                    .concat(writer.toString()).concat("'");
            throw new FirebaseException(msg, jue);
        }

        // build da resposta
        resposta = new FirebaseResponse(sucesso, codigo, corpo, writer.toString());

        // limpa a query
        query = null;

        return resposta;
    }
}
