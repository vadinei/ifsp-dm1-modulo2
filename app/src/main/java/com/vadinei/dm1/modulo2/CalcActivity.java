package com.vadinei.dm1.modulo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {

    private String operacao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final Button btCalcular = findViewById(R.id.btCalcular);
        final Button btVoltar = findViewById(R.id.btVoltar);

        btCalcular.setOnClickListener(this);
        btVoltar.setOnClickListener(this);

        operacao = getIntent().getStringExtra("operacao");
        final TextView tvTitulo = findViewById(R.id.tvTitulo);
        tvTitulo.setText(String.format("%s Números", operacao));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btVoltar) {
            finish();
        } else if (view.getId() == R.id.btCalcular) {
            calc();
        }
    }

    private void calc() {
        final EditText etNumero1 = findViewById(R.id.etNumero1);
        final EditText etNumero2 = findViewById(R.id.etNumero2);

        final int numero1 = Integer.parseInt(etNumero1.getText().toString());
        final int numero2 = Integer.parseInt(etNumero2.getText().toString());
        int resultado = 0;

        switch (operacao) {
            case "Somar":
                resultado = numero1 + numero2;
                break;
            case "Subtrair":
                resultado = numero1 - numero2;
                break;
            case "Multiplicar":
                resultado = numero1 * numero2;
                break;
            case "Dividir":
                resultado = numero1 / numero2;
                break;
        }

        Toast.makeText(CalcActivity.this, String.format("Resultado: %d", resultado), Toast.LENGTH_LONG).show();
    }
}