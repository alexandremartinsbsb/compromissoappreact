package compromissoapp.modelo;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirebaseResponse {

    private final boolean             sucesso;
    private final int                 codigo;
    private final Map<String, Object> corpo;
    private final String              corpoBruto;

    public FirebaseResponse(boolean sucesso, int codigo, Map<String, Object> corpo, String corpoBruto) {

        this.sucesso = sucesso;
        this.codigo = codigo;

        if (corpo == null) {
            corpo = new LinkedHashMap<String, Object>();
        }
        this.corpo = corpo;

        if (corpoBruto == null) {
            corpoBruto = new String();
        }
        this.corpoBruto = corpoBruto.trim();
    }

    /**
     * Retorna se a resposta do Firebase-client foi ou não bem-sucedida
     * 
     * @return true se a resposta do Firebase-client foi bem sucedida
     */
    public boolean getSucesso() {
        return this.sucesso;
    }

    /**
     * Retorna o código de status HTTP retornado do cliente Firebase
     * 
     * @return um integer que representa um código de status HTTP
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Retorna um mapa dos dados retornados pelo cliente Firebase
     * 
     * @return um mapa de Strings para Objects
     */
    public Map<String, Object> getCorpo() {
        return this.corpo;
    }

    /**
     * Retorna a resposta de dados brutos retornada pelo cliente Firebase
     * 
     * @return uma Cadeia de caracteres da resposta JSON do cliente
     */
    public String getCorpoBruto() {
        return this.corpoBruto;
    }

    @Override
    public String toString() {

        StringBuilder resultado = new StringBuilder();

        resultado.append(
                FirebaseResponse.class.getSimpleName() + "[ ")
                    .append("(Sucesso:").append(this.sucesso).append(") ")
                    .append("(Codigo:").append(this.codigo).append(") ")
                    .append("(Corpo:").append(this.corpo).append(") ")
                    .append("(Corpo Bruto:").append(this.corpoBruto).append(") ")
                    .append("]");

        return resultado.toString();
    }

}
