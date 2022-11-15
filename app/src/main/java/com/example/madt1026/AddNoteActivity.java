package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    EditText edNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        this.edNote = findViewById(R.id.edNote);
    }

    public void onBtnSaveAndCloseClick(View view) {
        String noteToAdd = this.edNote.getText().toString();

        SharedPreferences sharedPref = this.getSharedPreferences(Constants.NOTES_FILE, this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> savedSet = sharedPref.getStringSet(Constants.NOTES_ARRAY_KEY, null);
        Set<String> newSet = new HashSet<>();
        if(savedSet != null) {
            newSet.addAll(savedSet);
        }

        // Check if string is empty and display toast if empty
        if (!TextUtils.isEmpty(noteToAdd)) {
            newSet.add(noteToAdd);
        }
        else {
            Toast.makeText(getApplicationContext(), "Error: Empty string", Toast.LENGTH_SHORT).show();
        }

        editor.putString(Constants.NOTE_KEY, noteToAdd);
        editor.putStringSet(Constants.NOTES_ARRAY_KEY, newSet);
        editor.apply();

        finish();
    }
}