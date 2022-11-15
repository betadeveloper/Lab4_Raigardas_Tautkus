package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class RemoveNoteActivity extends AppCompatActivity {

    EditText edsNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_note);

        this.edsNote = findViewById(R.id.edsNote);
    }

    public void onBtnSaveAndCloseClick2(View view) {
        String noteToRemove = this.edsNote.getText().toString();

        SharedPreferences sharedPref = this.getSharedPreferences(Constants.NOTES_FILE, this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> savedSet = sharedPref.getStringSet(Constants.NOTES_ARRAY_KEY, null);
        Set<String> newSet = new HashSet<>();
        if(savedSet != null) {
            newSet.addAll(savedSet);
        }

        // Check if string is empty and display toast if empty
        if (!TextUtils.isEmpty(noteToRemove)) {
            newSet.remove(Constants.NOTES_ARRAY_KEY);
        }
        else {
            Toast.makeText(getApplicationContext(), "Error: Empty string, cannot remove empty", Toast.LENGTH_SHORT).show();
        }

        editor.putString(Constants.NOTE_KEY, noteToRemove);
        editor.putStringSet(Constants.NOTES_ARRAY_KEY, newSet);
        editor.apply();

        finish();
    }
}