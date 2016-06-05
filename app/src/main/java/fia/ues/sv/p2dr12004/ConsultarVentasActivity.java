package fia.ues.sv.p2dr12004;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import fia.ues.sv.p2dr12004.Helper.ControlDBDR12004;

public class ConsultarVentasActivity extends Activity {

    EditText editFecha;
    EditText editCod;
    TextView text1;
    ControlDBDR12004 controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_ventas);
        controlhelper = new ControlDBDR12004(this);

        editFecha = (EditText) findViewById(R.id.editFecha);
        editCod = (EditText) findViewById(R.id.editCod);

        text1 = (TextView) findViewById(R.id.text1);
    }

    public void consultarVentas(View v){
        String fecha = editFecha.getText().toString();
        String codigo = editCod.getText().toString();

        controlhelper.abrir();
        int count = controlhelper.consultarVentas(fecha,codigo);
        controlhelper.cerrar();

        text1.setText(String.valueOf(count));

    }
}
