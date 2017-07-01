package com.example.dbm0204.assignment143;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This is the mainActivity which extends AppCompactActivity and implements onClickListener
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String FILENAME="testFile.txt";
    EditText mEditText;
    TextView mtextView;
    Button readBtn;
    Button writeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText =(EditText) findViewById(R.id.editText);
        mtextView=(TextView) findViewById(R.id.textView);
        readBtn = (Button) findViewById(R.id.buttonRead);
        writeBtn = (Button) findViewById(R.id.buttonWrite);
        readBtn.setOnClickListener(this);
        writeBtn.setOnClickListener(this);
    }
    public void writeFile(View view) {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOutputStream.write(mEditText.getText().toString().getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFile(View view){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = openFileInput(FILENAME);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String newLine = null;
                while ((newLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(newLine + "\n");
                }
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mtextView.setText(stringBuilder);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonRead:
                Toast.makeText(getApplicationContext(),"Reading to File",Toast.LENGTH_LONG).show();
                readFile(view);
                break;
            case R.id.buttonWrite:
                Toast.makeText(getApplicationContext(),"Writing to File",Toast.LENGTH_LONG).show();
                writeFile(view);
                break;
        }
    }
}
