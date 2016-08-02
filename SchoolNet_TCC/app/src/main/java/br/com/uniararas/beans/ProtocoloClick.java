package br.com.uniararas.beans;

        import java.util.ArrayList;

/**
 * Created by theuv on 26/07/2016.
 */
public class ProtocoloClick {
    private String protocolo;
    private String data;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
