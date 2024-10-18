package ao.co.isptec.aplm.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateNoteActivity extends AppCompatActivity {
    private EditText noteTitleEditText, noteContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        noteTitleEditText = findViewById(R.id.noteTitleEditText);
        noteContentEditText = findViewById(R.id.noteContentEditText);
        Button okButton = findViewById(R.id.okButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        okButton.setOnClickListener(v -> {
            String title = noteTitleEditText.getText().toString();
            String content = noteContentEditText.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", title);
            resultIntent.putExtra("content", content);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        cancelButton.setOnClickListener(v -> finish());
    }
}
