package com.example.rejestru_konto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class poZapisie extends AppCompatActivity {

    Button back;
    ListView lista;
    String NAZWA_PREFERENCJI = "HaslPreferencje";
    String KLUCZ_HASLA = "KLUCZ_HASLA";
    String KLUCZ_EMAIL = "KLUCZ_EMAIL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_po_zapisie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences preferencje = getSharedPreferences(NAZWA_PREFERENCJI, Context.MODE_PRIVATE);
        String mail = preferencje.getString(KLUCZ_EMAIL, "");
        String haslo = preferencje.getString(KLUCZ_HASLA, "");
        ArrayList<String> listaElementow = new ArrayList<>();
        listaElementow.add("Email:" + mail);
        listaElementow.add("Hasło:" + haslo);
        lista = findViewById(R.id.lista);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaElementow
        );
        lista.setAdapter(adapter);

        back = findViewById(R.id.wroc);
        back.setOnClickListener(v->{
            finish();
        });
    }
}