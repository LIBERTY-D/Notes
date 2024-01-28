package com.daniel.notes.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daniel.notes.Events.OnItemListener;
import com.daniel.notes.R;

public class UpateNote extends AppCompatActivity  {

    private EditText uptitleTxt,updescriptionTxt;
    private  int id;
    private Button upcancelNote,upsaveNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upate_note);
        getSupportActionBar().setTitle("Update");
        upsaveNote = (Button) findViewById(R.id.upsaveNote);
        upcancelNote = (Button) findViewById(R.id.upcancelNote);
        uptitleTxt = (EditText) findViewById(R.id.uptitleTxt);
        updescriptionTxt = (EditText) findViewById(R.id.updescriptionTxt);
        updateInitData();
        upcancelNote();
        upsaveNote();

    }


    public  void    upsaveNote(){
       upsaveNote.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String title =  uptitleTxt .getText().toString();
               String desc =   updescriptionTxt.getText().toString();
               Intent i =  new Intent(UpateNote.this,MainActivity.class);
               i.putExtra("title",title);
               i.putExtra("desc",desc);
               if(id!=-1){
                   i.putExtra("id",id);
                   setResult(RESULT_OK,i);
                   Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_SHORT).show();
                   finish();
               }

           }
       });
    }
 public  void  upcancelNote(){
        upcancelNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"failed to update",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
 }

 public  void updateInitData(){
        Intent i =  getIntent();
        id =  i.getIntExtra("id",-1);
        String title =  i.getStringExtra("title");
        String desc =  i.getStringExtra("desc");
        uptitleTxt.setText(title);
        updescriptionTxt.setText(desc);



 }

}