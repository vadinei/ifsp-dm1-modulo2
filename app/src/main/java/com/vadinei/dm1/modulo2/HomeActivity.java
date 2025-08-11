package com.vadinei.dm1.modulo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final Button btSomar = findViewById(R.id.btSomar);
        final Button btSubtair = findViewById(R.id.btSubtrair);
        final Button btMultiplicar = findViewById(R.id.btMultiplicar);
        final Button btDividir = findViewById(R.id.btDividir);

        btSomar.setOnClickListener(this);
        btSubtair.setOnClickListener(this);
        btMultiplicar.setOnClickListener(this);
        btDividir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final Intent telaCalc = new Intent(HomeActivity.this, CalcActivity.class);

        if (view.getId() == R.id.btSomar) {
            telaCalc.putExtra("operacao", "Somar");
        } else if (view.getId() == R.id.btSubtrair) {
            telaCalc.putExtra("operacao", "Subtrair");
        } else if (view.getId() == R.id.btMultiplicar) {
            telaCalc.putExtra("operacao", "Multiplicar");
        } else if (view.getId() == R.id.btDividir) {
            telaCalc.putExtra("operacao", "Dividir");
        }

        startActivity(telaCalc);
    }
}