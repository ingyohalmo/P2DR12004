package fia.ues.sv.p2dr12004.Controladores;

/**
 * Created by yohalmo on 06-05-16.
 */

public class Publicaciones {
    String num_pub;// char(6)
    String fecha_pub;// char(6)
    String cod_autor;// char(3)
    String editorial;// char(30)
    Double valor_pub;// DOUBLE

    public Publicaciones() {
    }

    public Publicaciones(String num_pub, String fecha_pub, String cod_autor, String editorial, Double valor_pub) {
        this.num_pub = num_pub;
        this.fecha_pub = fecha_pub;
        this.cod_autor = cod_autor;
        this.editorial = editorial;
        this.valor_pub = valor_pub;
    }

    public String getNum_pub() {
        return num_pub;
    }

    public void setNum_pub(String num_pub) {
        this.num_pub = num_pub;
    }

    public String getFecha_pub() {
        return fecha_pub;
    }

    public void setFecha_pub(String fecha_pub) {
        this.fecha_pub = fecha_pub;
    }

    public String getCod_autor() {
        return cod_autor;
    }

    public void setCod_autor(String cod_autor) {
        this.cod_autor = cod_autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Double getValor_pub() {
        return valor_pub;
    }

    public void setValor_pub(Double valor_pub) {
        this.valor_pub = valor_pub;
    }
}
