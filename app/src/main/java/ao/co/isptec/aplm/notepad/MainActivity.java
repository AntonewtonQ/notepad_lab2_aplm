package ao.co.isptec.aplm.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> noteTitles = new ArrayList<>();
    private ArrayList<String> noteContents = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView notesListView = findViewById(R.id.notesListView);
        Button newNoteButton = findViewById(R.id.newNoteButton);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteTitles);
        notesListView.setAdapter(adapter);

        newNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
            startActivityForResult(intent, 1);
        });

        notesListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, ReadNoteActivity.class);
            intent.putExtra("title", noteTitles.get(position));
            intent.putExtra("content", noteContents.get(position));
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            noteTitles.add(title);
            noteContents.add(content);
            adapter.notifyDataSetChanged();
        }
    }
}
