package fia.ues.sv.p2dr12004.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fia.ues.sv.p2dr12004.Controladores.Autor;
import fia.ues.sv.p2dr12004.Controladores.Publicaciones;

/**
 * Created by yohalmo on 06-05-16.
 */
public class ControlDBDR12004 {


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlDBDR12004(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "parcial.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE Publicaciones(num_pub CHAR(6) NOT NULL PRIMARY KEY, fecha_pub CHAR(6) NOT NULL, cod_autor CHAR(4) NOT NULL, editorial CHAR(30) NOT NULL, valor_pub DOUBLE NOT NULL); ");
                db.execSQL("CREATE TABLE Autor(cod_autor CHAR(4) NOT NULL PRIMARY KEY, nom_autor CHAR(30) NOT NULL, cant_libPub INTEGER); ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }


    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }

    public String insertar(Publicaciones publi) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;

        ContentValues pubs = new ContentValues();

        pubs.put("num_pub", publi.getNum_pub());
        pubs.put("fecha_pub", publi.getFecha_pub());
        pubs.put("cod_autor", publi.getCod_autor());
        pubs.put("editorial", publi.getEditorial());
        pubs.put("valor_pub", publi.getValor_pub());

        contador = db.insert("Publicaciones", null, pubs);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción ";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    public String insertar(Autor autor) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;


        ContentValues autores = new ContentValues();

        autores.put("cod_autor", autor.getCod_autor());
        autores.put("nom_autor", autor.getNom_autor());
        autores.put("cant_libPub", autor.getCant_libPub());

        contador = db.insert("Autor", null, autores);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción ";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    public String eliminar(Autor autor) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        if (verificarIntegridad(autor, 3)) {
            contador += db.delete("Autor", "cod_autor='" + autor.getCod_autor() + "'", null);
        }
        regAfectados += contador;
        return regAfectados;
    }

    public String actualizar(Publicaciones pubs) {

        if (verificarIntegridad(pubs, 2)) {
            String[] id = {pubs.getNum_pub()};
            ContentValues cv = new ContentValues();
            cv.put("fecha_pub", pubs.getFecha_pub());
            cv.put("cod_autor", pubs.getCod_autor());
            cv.put("editorial", pubs.getEditorial());
            cv.put("valor_pub", pubs.getValor_pub());
            db.update("Publicaciones", cv, "num_pub = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con numero de publicacion " + pubs.getNum_pub() + " no existe o verificar Autor " +pubs.getCod_autor() ;
        }

    }

    public int consultarPubli(String fecha, String autor) {

        Cursor cursor = db.rawQuery("select count(*) from Publicaciones where fecha_pub='" + fecha + "' and cod_autor='" + autor + "'", null);
        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            return count;
        } else {
            return 0;
        }
    }


    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 2: {
                //verificar que exista Publicacion y Autor
                Publicaciones publi2 = (Publicaciones) dato;
                String[] id1 = {publi2.getNum_pub()};
                String[] id2 = {publi2.getCod_autor()};
                //abrir();
                Cursor c2 = db.query("Publicaciones", null, "num_pub = ?", id1, null, null, null);
                Cursor c1 = db.query("Autor", null, "cod_autor = ?", id2, null, null, null);

                if (c2.moveToFirst() && c1.moveToFirst()) {

                    return true;
                }
                return false;
            }
            case 3: {
                Autor autor = (Autor) dato;
                Cursor c = db.query(true, "Publicaciones", new String[]{"cod_autor"}, "cod_autor='" + autor.getCod_autor() + "'", null, null, null, null, null);
                if (c.moveToFirst())
                    return false;
                else
                    return true;
            }

            default:
                return false;
        }
    }

    public String llenarDBDR12004() {

        // Valores Autor
        final String[] VAcod = {"cs01", "cz02", "ec04", "jr02"};
        final String[] VAnom = {"Carlos Sanchez", "Cristian Zapata", "Edwin Cardona", "James Rodriguez"};
        final int[] VAcant = {10, 50, 20, 40};
        // Valores Publicaciones
        final String[] VPnum = {"000001", "000002", "000003", "000004"};
        final String[] VPfecha = {"051214", "23062015", "120916", "010614"};
        final String[] VPcod = {"cs01", "cs01", "ec04", "jr02"};
        final String[] VPedit = {"Santillana", "Roger y Teyes", "Camargo", "Mc Graham"};
        final Double[] VPvalor = {300.50, 40.25, 200.75, 1000.45};

        abrir();
        db.execSQL("DELETE FROM Publicaciones");
        db.execSQL("DELETE FROM Autor");

        Publicaciones publicaciones = new Publicaciones();
        for (int i = 0; i < 4; i++) {
            publicaciones.setNum_pub(VPnum[i]);
            publicaciones.setFecha_pub(VPfecha[i]);
            publicaciones.setCod_autor(VPcod[i]);
            publicaciones.setEditorial(VPedit[i]);
            publicaciones.setValor_pub(VPvalor[i]);
            insertar(publicaciones);
        }

        Autor autor = new Autor();
        for (int i = 0; i < 4; i++) {
            autor.setCod_autor(VAcod[i]);
            autor.setNom_autor(VAnom[i]);
            autor.setCant_libPub(VAcant[i]);
            insertar(autor);
        }

        cerrar();
        return "Guardo Correctamente";
    }

}


