package com.ppc_tp4_isea_lopez.curti_z;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollPantalla;

    EditText editTextIva21;
    EditText editTextIvaCigarrillos;
    EditText editTextMontoTotal;

    TextView txtProducto21;
    TextView txtCigarrillos;
    TextView txtExentas;

    Button btnCalcular;
    Button btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollPantalla = findViewById(R.id.scrollPantalla);

        editTextIva21 = findViewById(R.id.editTextIVA21);
        editTextIvaCigarrillos = findViewById(R.id.editTextIVACigarrillos);
        editTextMontoTotal = findViewById(R.id.editTextMontoTotal);

        txtProducto21 = findViewById(R.id.txtProducto21);
        txtCigarrillos = findViewById(R.id.txtCigarrillos);
        txtExentas = findViewById(R.id.txtExentas);

        btnCalcular = findViewById(R.id.buttonCalcular);
        btnLimpiar = findViewById(R.id.buttonLimpiar);

        // Para que al apretar hecho calcule.
//        editTextMontoTotal.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    controlar(v);
//                    return true;
//                }
//                return false;
//            }
//        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlar(v);
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

    }

    private void controlar(View v){
        String strIva21 = editTextIva21.getText().toString();
        String strIva3 = editTextIvaCigarrillos.getText().toString();
        String strTotal = editTextMontoTotal.getText().toString();

        // Controlamos que se haya ingresado texto.
        if(strIva21.isEmpty()){
            Toast.makeText(v.getContext(), "Ingrese el IVA al 21%", Toast.LENGTH_LONG).show();
        }else if(strIva3.isEmpty()){
            Toast.makeText(v.getContext(), "Ingrese el IVA de cigarrillos", Toast.LENGTH_LONG).show();
        }else if(strTotal.isEmpty()){
            Toast.makeText(v.getContext(), "Ingrese el monto total", Toast.LENGTH_LONG).show();
        }else {
            double iva21 = Double.parseDouble(strIva21);
            double iva3 = Double.parseDouble(strIva3);
            double total = Double.parseDouble(strTotal);

            calcular(iva21, iva3, total);
        }
    }

    private void calcular(double iva21, double iva3, double total){
        double producto21 = iva21 / .21 * 1.21;
        double cigarrillos = iva3 / .21 * 1.21;
        // Redondeo.
        producto21 = Math.round(producto21 * 100);
        producto21 = producto21 / 100;
        cigarrillos = Math.round(cigarrillos * 100);
        cigarrillos = cigarrillos / 100;
        double exentas = total - producto21 - cigarrillos;
        exentas = Math.round(exentas * 100);
        exentas = exentas / 100;

        txtProducto21.setText("Producto 21% : " + producto21);
        txtCigarrillos.setText("Cigarrillos : " + cigarrillos);
        txtExentas.setText("Exentas : " + exentas);

        //editTextIva21.requestFocus();
        scrollPantalla.smoothScrollTo(txtExentas.getScrollX(),txtExentas.getScrollY()+150);

    }

    private void limpiar(){
        editTextIva21.setText("");
        editTextIvaCigarrillos.setText("");
        editTextMontoTotal.setText("");
        txtProducto21.setText("");
        txtCigarrillos.setText("");
        txtExentas.setText("");

        editTextIva21.requestFocus();
    }

}