package com.daniel.notes.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daniel.notes.R;

public class AddNotes extends AppCompatActivity {

    private EditText titleTxt,descriptionTxt;
   private Button cancelNote,saveNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
       getSupportActionBar().setTitle("Add Note");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        saveNote = (Button) findViewById(R.id.saveNote);
        cancelNote = (Button) findViewById(R.id.cancelNote);
        titleTxt = (EditText) findViewById(R.id.titleTxt);
        descriptionTxt = (EditText) findViewById(R.id.descriptionTxt);

        saveNote();
        cancelNote();
    }


    public  void saveNote(){
          saveNote.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                   String title=  titleTxt.getText().toString();
                   String desc  =   descriptionTxt.getText().toString();
                   Intent i =   new Intent();
                  i.putExtra("title",title);
                  i.putExtra("desc",desc);

                  setResult(RESULT_OK,i);
                  Toast.makeText(getApplicationContext(),"note saved",Toast.LENGTH_SHORT).show();
                  finish();

              }
          });

    }


    public  void cancelNote(){

         cancelNote.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Toast.makeText(getApplicationContext(),"note cancelled",Toast.LENGTH_SHORT).show();
                 finish();
             }
         });

    }
}

