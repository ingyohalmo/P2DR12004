package fia.ues.sv.p2dr12004;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fia.ues.sv.p2dr12004.Controladores.ControlVenta;
import fia.ues.sv.p2dr12004.Helper.ControlDBDR12004;

public class ControlVentaActualizarActivity extends Activity {

    ControlDBDR12004 helper;
    EditText editNumVenta;
    EditText editFechaServ;
    EditText editCodZona;
    EditText editEsCasa;
    EditText editEsEdif;
    EditText editEsOtro;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_venta_actualizar);
        helper = new ControlDBDR12004(this);
        editNumVenta = (EditText) findViewById(R.id.editNumVenta);
        editFechaServ = (EditText) findViewById(R.id.editFechaServ);
        editCodZona = (EditText) findViewById(R.id.editCodZona);
        editEsCasa = (EditText) findViewById(R.id.editEsCasa);
        editEsEdif = (EditText) findViewById(R.id.editEsEdif);
        editEsOtro = (EditText) findViewById(R.id.editEsOtro);
    }

    public void actualizarControlVenta(View v) {
        ControlVenta venta = new ControlVenta();
        venta.setNum_venta(editNumVenta.getText().toString());
        venta.setFecha_serv(editFechaServ.getText().toString());
        venta.setCod_zona(editCodZona.getText().toString());
        venta.setEs_casa(editEsCasa.getText().toString());
        venta.setEs_edif(editEsEdif.getText().toString());
        venta.setEs_otro(editEsOtro.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(venta);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editNumVenta.setText("");
        editFechaServ.setText("");
        editCodZona.setText("");
        editEsCasa.setText("");
        editEsEdif.setText("");
        editEsOtro.setText("");
    }
}
