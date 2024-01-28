package com.daniel.notes.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.daniel.notes.Adapters.NotesAdapter;
import com.daniel.notes.Events.OnItemListener;
import com.daniel.notes.Models.Note;
import com.daniel.notes.R;
import com.daniel.notes.ViewModels.NoteViewModel;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity  {
  private  static  final int ADD_NOTE= R.id.addNote;

  private RecyclerView noteNoteRecycler;

  private ActivityResultLauncher<Intent> activityResultLauncher;

  private  ActivityResultLauncher<Intent> activityResultLauncherUpdate;

  private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterActivityForAddNote();
        RegisterUpdateLauncher();
        noteNoteRecycler = (RecyclerView) findViewById(R.id.noteNoteRecycler);
        NotesAdapter notesAdapter =  new NotesAdapter();
        noteNoteRecycler.setAdapter(notesAdapter);
        noteNoteRecycler.setLayoutManager(new LinearLayoutManager(this));

        noteViewModel =  new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
            // update recycler view
                notesAdapter.setNotes(notes);


            }
        });
        notesAdapter.setOnItemListener(new OnItemListener() {
            @Override
            public void onItemClick(Note note) {

                Intent intent = new Intent(MainActivity.this, UpateNote.class);
                intent.putExtra("id",note.getId());
                intent.putExtra("title",note.getTitle());
                intent.putExtra("desc",note.getDescription());
                activityResultLauncherUpdate.launch(intent);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
              noteViewModel.delete(notesAdapter.getNotes(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplication(),"item deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(noteNoteRecycler);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.add_header,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int ID = item.getItemId();
        if (ID==ADD_NOTE){
//          TODO open note activity
            Intent intent = new Intent(MainActivity.this,AddNotes.class);
            activityResultLauncher.launch(intent);


        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(resultCode==RESULT_OK && requestCode==1 && data !=null){
//            String title =  data.getStringExtra("title");
//            String desc =  data.getStringExtra("desc");
//            Date date  =  new Date();
//            Note note  = new Note(title,desc,String.valueOf(date.getTime()));
//            noteViewModel.insert(note);
//        }
//    }

    public  void RegisterActivityForAddNote(){
        activityResultLauncher =  registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                int ressutCode =  o.getResultCode();
                Intent data =  o.getData();

                if(ressutCode == RESULT_OK && data !=null){
                    String title =  data.getStringExtra("title");
                    String desc =  data.getStringExtra("desc");
                    LocalDate date = LocalDate.now();
                    Note note  = new Note(title,desc,String.valueOf(date));
                    noteViewModel.insert(note);
                }
            }
        });
    }




    public  void  RegisterUpdateLauncher(){
        activityResultLauncherUpdate = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                int resCode = o.getResultCode();
                 Intent data = o.getData();
                 if(resCode==RESULT_OK && data!=null){
                     int  id =  data.getIntExtra("id",-1);
                     String title = data.getStringExtra("title");
                     String desc =  data.getStringExtra("desc");
                     String date =  data.getStringExtra("date");
                     Note note =  new Note(title,desc,date);
                     note.setId(id);
                     noteViewModel.update(note);

                 }
            }
        });
    }
}