package com.example.gsondemoapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public static final String DATA = "DATA";
    private TextView txtResults;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

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
        txtResults = findViewById(R.id.txtResults);
        setupSharedPrefs();
    }
    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

    }
    public void btnSaveOnClick(View view) {
        Book books[] = new Book[3];
        books[0] = new Book("Toqa Abdeen", "How to die");
        books[1] = new Book("Rand Abdeen", "Cooking");
        books[2] = new Book("Nabila Abdeen", "SkinCare");

        Gson gson = new Gson();
        String bookStrings = gson.toJson(books);
        editor.putString(DATA,bookStrings);
        editor.commit();
        txtResults.setText(bookStrings);

    }

    public void btnLoadOnClick(View view) {
        Gson gson = new Gson();
        String string = prefs.getString(DATA,"");
        if(!string.equals("")){
            Book books [] = gson.fromJson(string,Book[].class);
            txtResults.setText("number of books:  " + books.length);
        }
        else{
            txtResults.setText("No data found");
        }
    }
}