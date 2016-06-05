package fia.ues.sv.p2dr12004.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fia.ues.sv.p2dr12004.Controladores.ControlVenta;
import fia.ues.sv.p2dr12004.Controladores.Zona;

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
        private static final String BASE_DATOS = "parcial2.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE Zona(cod_zona CHAR(3) NOT NULL PRIMARY KEY, nom_zona CHAR(30), casas INTEGER, edif INTEGER, otros INTEGER); ");
                db.execSQL("CREATE TABLE ControlVenta(num_venta CHAR(6) NOT NULL PRIMARY KEY, fecha_serv CHAR(6), cod_zona CHAR(3), es_casa CHAR(1), es_edif CHAR(1), es_otro CHAR(1)); ");
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

    public String insertar(Zona zona) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;


            ContentValues zonas = new ContentValues();

            zonas.put("cod_zona", zona.getCod_zona());
            zonas.put("nom_zona", zona.getNom_zona());
            zonas.put("casas", zona.getCasas());
            zonas.put("edif", zona.getEdif());
            zonas.put("otros", zona.getOtros());

            contador = db.insert("Zona", null, zonas);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción ";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    public String insertar(ControlVenta controlV) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;


        if (verificarIntegridad(controlV,1)) {
            ContentValues controles = new ContentValues();

            controles.put("num_venta", controlV.getNum_venta());
            controles.put("fecha_serv", controlV.getFecha_serv());
            controles.put("cod_zona", controlV.getCod_zona());
            controles.put("es_casa", controlV.getEs_casa());
            controles.put("es_edif", controlV.getEs_edif());
            controles.put("es_otro", controlV.getEs_otro());

            contador = db.insert("ControlVenta", null, controles);
        }

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción ";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    public String eliminar(Zona zona){
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        if (verificarIntegridad(zona, 3)) {
            contador += db.delete("ControlVenta", "cod_zona='" + zona.getCod_zona() + "'", null);
        }
        contador += db.delete("Zona", "cod_zona='" + zona.getCod_zona() + "'", null);
        regAfectados += contador;
        return regAfectados;
    }

    public String actualizar(ControlVenta controlV) {

        if (verificarIntegridad(controlV, 2)) {
            String[] id = {controlV.getNum_venta()};
            ContentValues cv = new ContentValues();
            cv.put("fecha_serv", controlV.getFecha_serv());
            cv.put("cod_zona", controlV.getCod_zona());
            cv.put("es_casa", controlV.getEs_casa());
            cv.put("es_edif", controlV.getEs_edif());
            cv.put("es_otro", controlV.getEs_otro());
            db.update("ControlVenta", cv, "num_venta = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con numero de venta " + controlV.getNum_venta() + " no existe";
        }

    }

    public int consultarVentas(String fecha, String zona) {

        Cursor cursor = db.rawQuery("select count(*) from ControlVenta where fecha_serv='" + fecha + "' and cod_zona='" + zona +"'", null);
        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            return count;
        } else {
            return 0;
        }
    }


    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que al insertar Venta Exista Zona
                 ControlVenta controlV1 = (ControlVenta) dato;
                String[] id = {controlV1.getCod_zona()};
                //abrir();
                Cursor cursor1 = db.query("Zona", null, "cod_zona = ?", id, null, null, null);

                if (cursor1.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 2: {
                //verificar que exista venta
                ControlVenta controlV2 = (ControlVenta) dato;
                String[] id = {controlV2.getNum_venta()};
                //abrir();
                Cursor c2 = db.query("ControlVenta", null, "num_venta = ?", id, null, null, null);

                if (c2.moveToFirst()) {
                    //Se encontro Alumno
                    return true;
                }
                return false;
            }
            case 3: {
                Zona zona = (Zona) dato;
                Cursor c = db.query(true, "ControlVenta", new String[]{"cod_zona"}, "cod_zona='" + zona.getCod_zona() + "'", null, null, null, null, null);
                if (c.moveToFirst())
                    return true;
                else
                    return false;
            }

            default:
                return false;
        }
    }

    public String llenarDBDR12004() {

        final String[] VZcod = {"slv", "sns", "ahc", "llb"};
        final String[] VZnom = {"San Salvador", "Sonsonate", "Ahuachapan", "La Libertad"};
        final int[] VZcasas = {200,50,20,150};
        final int[] VZedif = {30, 15, 10, 20};
        final int[] VZotros = {2,3,4,1};
        final String[] VCVnum = {"000001", "000002", "000003", "000004"};
        final String[] VCVfecha = {"051214", "23062015", "120916", "010614"};
        final String[] VCVcod = {"slv", "sns", "ahc", "llb"};
        final String[] VCVcasa = {"S", "N", "N", "N"};
        final String[] VCVedif = {"N", "N", "S", "S"};
        final String[] VCVotro = {"N", "S", "N", "N"};

        abrir();
        db.execSQL("DELETE FROM Zona");
        db.execSQL("DELETE FROM ControlVenta");

        Zona zona = new Zona();
        for (int i = 0; i < 4; i++) {
            zona.setCod_zona(VZcod[i]);
            zona.setNom_zona(VZnom[i]);
            zona.setCasas(VZcasas[i]);
            zona.setEdif(VZedif[i]);
            zona.setOtros(VZotros[i]);
            insertar(zona);
        }

        ControlVenta ctrl = new ControlVenta();
        for (int i = 0; i < 4; i++) {
            ctrl.setNum_venta(VCVnum[i]);
            ctrl.setFecha_serv(VCVfecha[i]);
            ctrl.setCod_zona(VCVcod[i]);
            ctrl.setEs_casa(VCVcasa[i]);
            ctrl.setEs_edif(VCVedif[i]);
            ctrl.setEs_otro(VCVotro[i]);
            insertar(ctrl);
        }

        cerrar();
        return "Guardo Correctamente";
    }

}


