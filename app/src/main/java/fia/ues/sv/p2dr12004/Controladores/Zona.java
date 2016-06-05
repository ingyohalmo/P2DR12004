package fia.ues.sv.p2dr12004.Controladores;

/**
 * Created by yohalmo on 06-05-16.
 */

public class Zona {
    String cod_zona;// char(3)
    String nom_zona;// char(30)
    int casas;//  integer
    int edif;// integer
    int otros;// integer

    public Zona() {
    }

    public Zona(String cod_zona, String nom_zona, int casas, int edif, int otros) {
        this.cod_zona = cod_zona;
        this.nom_zona = nom_zona;
        this.casas = casas;
        this.edif = edif;
        this.otros = otros;
    }

    public String getCod_zona() {
        return cod_zona;
    }

    public void setCod_zona(String cod_zona) {
        this.cod_zona = cod_zona;
    }

    public String getNom_zona() {
        return nom_zona;
    }

    public void setNom_zona(String nom_zona) {
        this.nom_zona = nom_zona;
    }

    public int getCasas() {
        return casas;
    }

    public void setCasas(int casas) {
        this.casas = casas;
    }

    public int getEdif() {
        return edif;
    }

    public void setEdif(int edif) {
        this.edif = edif;
    }

    public int getOtros() {
        return otros;
    }

    public void setOtros(int otros) {
        this.otros = otros;
    }
}
