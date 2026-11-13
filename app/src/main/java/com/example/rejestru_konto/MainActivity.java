package com.example.rejestru_konto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button confirm, save;
    EditText haslo, email, drugieHaslo;
    TextView komunikat;
    String NAZWA_PREFERENCJI = "HaslPreferencje";
    String KLUCZ_HASLA = "KLUCZ_HASLA";
    String KLUCZ_EMAIL = "KLUCZ_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        confirm = findViewById(R.id.confirm);
        haslo = findViewById(R.id.editHaslo);
        email = findViewById(R.id.editEmail);
        drugieHaslo = findViewById(R.id.editDrugieHaslo);
        komunikat = findViewById(R.id.komunikat);
        save = findViewById(R.id.save);



        /*Obsługa zdarzenia kliknięcia przycisku "Zatwierdz"
         * Sprawdza hasła i adres email, a nastepnie wyswietla odpowiedni komunikat w TextView o id komunikat
         *
         * @param v - widok reprezentujący kliknięty przycisk
         * @author Artem Prykhoda
         * */
        confirm.setOnClickListener(v->{
            String mail = email.getText().toString();
            String password = haslo.getText().toString();
            String password2 = drugieHaslo.getText().toString();
            if(!password.equals(password2)){
                komunikat.setText("Hasła nie są takie same");
            }
            else if(!mail.contains("@")){
                komunikat.setText("Nieprawidłowy adres email");
            }
            else{
                komunikat.setText("Witaj "+ mail);
                startActivity(new android.content.Intent(MainActivity.this, poZapisie.class));
            }
        });
        /*Obsługa zdarzenia kliknięcia przycisku "Zapisz"
         * Zapisuje hasło i email w SharedPreferences jeśli hasła są takie same po kliknieciu w przycisk Zapisz i przechodzi do nowej intencji
         *
         * @param v - widok reprezentujący kliknięty przycisk
         * @author Artem Prykhoda
         * */
        save.setOnClickListener(v->{
                String mail = email.getText().toString();
                String password = haslo.getText().toString();
                String password2 = drugieHaslo.getText().toString();
                SharedPreferences Settings = getSharedPreferences(NAZWA_PREFERENCJI, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = Settings.edit();
                editor.putString(KLUCZ_HASLA, haslo.getText().toString());
                editor.putString(KLUCZ_EMAIL, email.getText().toString());
                editor.apply();

        });

    }
}