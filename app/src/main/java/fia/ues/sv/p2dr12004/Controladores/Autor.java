package fia.ues.sv.p2dr12004.Controladores;

/**
 * Created by yohalmo on 06-05-16.
 */
public class Autor {
    String cod_autor;// char(4)
    String nom_autor;// char(30)
    int cant_libPub;//  integer

    public Autor() {
    }

    public Autor(String cod_autor, String nom_autor, int cant_libPub) {
        this.cod_autor = cod_autor;
        this.nom_autor = nom_autor;
        this.cant_libPub = cant_libPub;
    }

    public String getCod_autor() {
        return cod_autor;
    }

    public void setCod_autor(String cod_autor) {
        this.cod_autor = cod_autor;
    }

    public String getNom_autor() {
        return nom_autor;
    }

    public void setNom_autor(String nom_autor) {
        this.nom_autor = nom_autor;
    }

    public int getCant_libPub() {
        return cant_libPub;
    }

    public void setCant_libPub(int cant_libPub) {
        this.cant_libPub = cant_libPub;
    }
}
