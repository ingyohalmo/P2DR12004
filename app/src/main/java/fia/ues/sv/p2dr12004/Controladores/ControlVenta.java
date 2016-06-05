package fia.ues.sv.p2dr12004.Controladores;

/**
 * Created by yohalmo on 06-05-16.
 */
public class ControlVenta {
    String num_venta;//  char(6)
    String fecha_serv;// char(6)
    String cod_zona;// char(3)
    String es_casa;// char(1)
    String es_edif;// char(1)
    String es_otro;// char(1)

    public ControlVenta() {
    }

    public ControlVenta(String num_venta, String fecha_serv, String cod_zona, String es_casa, String es_edif, String es_otro) {
        this.num_venta = num_venta;
        this.fecha_serv = fecha_serv;
        this.cod_zona = cod_zona;
        this.es_casa = es_casa;
        this.es_edif = es_edif;
        this.es_otro = es_otro;
    }

    public String getNum_venta() {
        return num_venta;
    }

    public void setNum_venta(String num_venta) {
        this.num_venta = num_venta;
    }

    public String getFecha_serv() {
        return fecha_serv;
    }

    public void setFecha_serv(String fecha_serv) {
        this.fecha_serv = fecha_serv;
    }

    public String getCod_zona() {
        return cod_zona;
    }

    public void setCod_zona(String cod_zona) {
        this.cod_zona = cod_zona;
    }

    public String getEs_casa() {
        return es_casa;
    }

    public void setEs_casa(String es_casa) {
        this.es_casa = es_casa;
    }

    public String getEs_edif() {
        return es_edif;
    }

    public void setEs_edif(String es_edif) {
        this.es_edif = es_edif;
    }

    public String getEs_otro() {
        return es_otro;
    }

    public void setEs_otro(String es_otro) {
        this.es_otro = es_otro;
    }
}
