package fia.ues.sv.p2dr12004;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fia.ues.sv.p2dr12004.Controladores.Autor;
import fia.ues.sv.p2dr12004.Helper.ControlDBDR12004;

public class EliminarAutor extends Activity {

    EditText editCodAutor;
    ControlDBDR12004 controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_autor);
        controlhelper = new ControlDBDR12004(this);
        editCodAutor = (EditText) findViewById(R.id.editCodAutor);
    }

    public void eliminarAutor(View v) {
        String regEliminadas;
        Autor autor = new Autor();
        autor.setCod_autor(editCodAutor.getText().toString());
        controlhelper.abrir();
        regEliminadas = controlhelper.eliminar(autor);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
