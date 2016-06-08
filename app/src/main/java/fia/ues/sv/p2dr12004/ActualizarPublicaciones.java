package fia.ues.sv.p2dr12004;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fia.ues.sv.p2dr12004.Controladores.Publicaciones;
import fia.ues.sv.p2dr12004.Helper.ControlDBDR12004;

public class ActualizarPublicaciones extends Activity {

    ControlDBDR12004 helper;
    EditText editNumPub;
    EditText editFechaPub;
    EditText editCodAutor;
    EditText editEdit;
    EditText editValorPub;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_publicaciones);
        helper = new ControlDBDR12004(this);
        editNumPub = (EditText) findViewById(R.id.editNumPub);
        editFechaPub = (EditText) findViewById(R.id.editFechaPub);
        editCodAutor = (EditText) findViewById(R.id.editCodAutor);
        editEdit = (EditText) findViewById(R.id.editEdit);
        editValorPub = (EditText) findViewById(R.id.editValorPub);
    }

    public void actualizarPublicacion(View v) {
        Publicaciones pub = new Publicaciones();
        pub.setNum_pub(editNumPub.getText().toString());
        pub.setFecha_pub(editFechaPub.getText().toString());
        pub.setCod_autor(editCodAutor.getText().toString());
        pub.setEditorial(editEdit.getText().toString());
        pub.setValor_pub(Double.valueOf(editValorPub.getText().toString()));

        helper.abrir();
        String estado = helper.actualizar(pub);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
    }

    public void limpiarTexto(View v) {
        editNumPub.setText("");
        editFechaPub.setText("");
        editEdit.setText("");
        editValorPub.setText("");
    }
}
