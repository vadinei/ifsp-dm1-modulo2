package com.vadinei.dm1.modulo2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TripActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trip);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final Button btCalcularViagen = findViewById(R.id.btCalcularViagem);
        final Button btLimparCalculoViagem = findViewById(R.id.btLimparCalculoViagem);
        final Button btVoltarViagem = findViewById(R.id.btVoltarViagem);

        btCalcularViagen.setOnClickListener(this);
        btLimparCalculoViagem.setOnClickListener(this);
        btVoltarViagem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btVoltarViagem) {
            finish();
        } else {
            final EditText etDistancia = findViewById(R.id.etDistancia);
            final EditText etPrecoLitroCombustivel = findViewById(R.id.etPrecoLitroCombustivel);
            final EditText etMediaQuilometroPorLitro = findViewById(R.id.etMediaQuilometroPorLitro);
            if (view.getId() == R.id.btCalcularViagem) {
                calc(etDistancia, etPrecoLitroCombustivel, etMediaQuilometroPorLitro);
            } else if (view.getId() == R.id.btLimparCalculoViagem) {
                clear(etDistancia, etPrecoLitroCombustivel, etMediaQuilometroPorLitro);
            }
        }
    }

    private void calc(
        final EditText etDistancia,
        final EditText etPrecoLitroCombustivel,
        final EditText etMediaQuilometroPorLitro
    ) {
        if (etDistancia.getText() == null || etDistancia.getText().isEmpty()) {
            etDistancia.requestFocus();
        } else if (etPrecoLitroCombustivel.getText() == null || etPrecoLitroCombustivel.getText().isEmpty()) {
            etPrecoLitroCombustivel.requestFocus();
        } else if (etMediaQuilometroPorLitro.getText() == null || etMediaQuilometroPorLitro.getText().isEmpty()) {
            etMediaQuilometroPorLitro.requestFocus();
        } else if (etMediaQuilometroPorLitro.getText().toString().equals("0")) {
            Toast.makeText(TripActivity.this, "Valor inválido à média de KM/Litro de combustível", Toast.LENGTH_LONG).show();
        } else {
            final BigDecimal distancia = new BigDecimal(etDistancia.getText().toString());
            final BigDecimal precoLitroCombustivel = new BigDecimal(etPrecoLitroCombustivel.getText().toString());
            final BigDecimal mediaQuilometroPorLitro = new BigDecimal(etMediaQuilometroPorLitro.getText().toString());
            final BigDecimal litros = distancia.divide(mediaQuilometroPorLitro, 15, RoundingMode.HALF_UP);
            final BigDecimal custo = litros.multiply(precoLitroCombustivel);
            Toast.makeText(TripActivity.this, String.format("%.2f Litro(s) | Custo Total: %.2f", litros, custo), Toast.LENGTH_LONG).show();
        }
    }

    private void clear(
        final EditText etDistancia,
        final EditText etPrecoLitroCombustivel,
        final EditText etMediaQuilometroPorLitro
    ) {
        etDistancia.setText(null);
        etPrecoLitroCombustivel.setText(null);
        etMediaQuilometroPorLitro.setText(null);
    }
}