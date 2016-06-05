package fia.ues.sv.p2dr12004;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fia.ues.sv.p2dr12004.Controladores.Zona;
import fia.ues.sv.p2dr12004.Helper.ControlDBDR12004;

public class ZonaEliminarActivity extends Activity {

    EditText editCodZona;
    ControlDBDR12004 controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_eliminar);
        controlhelper = new ControlDBDR12004(this);
        editCodZona = (EditText) findViewById(R.id.editCodZona);
    }

    public void eliminarZona(View v) {
        String regEliminadas;
        Zona zona = new Zona();
        zona.setCod_zona(editCodZona.getText().toString());
        controlhelper.abrir();
        regEliminadas = controlhelper.eliminar(zona);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
