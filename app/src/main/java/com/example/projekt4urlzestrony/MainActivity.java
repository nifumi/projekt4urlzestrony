package com.example.projekt4urlzestrony;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mLink;
    private Button btn1;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLink = (EditText) findViewById(R.id.editTxt1);
        btn1 = (Button) findViewById(R.id.btn1);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);

        //declaring kinda the "database"
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //stores shared preferences
        mEditor = mPreferences.edit();

        checkSharedPreferences();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save the checkbox preference
                if (mCheckBox.isChecked()) {
                    //set a checkbox when the application starts
                    mEditor.putString(getString(R.string.checkbox), "True");
                    mEditor.commit();

                    //save the link
                    String link = mLink.getText().toString();
                    mEditor.putString(getString(R.string.url_link), link);
                    mEditor.commit();

                }else {
                    //set a checkbox when the application starts
                    mEditor.putString(getString(R.string.checkbox), "False");
                    mEditor.commit();

                    //save the link
                    mEditor.putString(getString(R.string.url_link), "");
                    mEditor.commit();
                }
            }
        });
    }

    private void checkSharedPreferences() {
        String checkBox = mPreferences.getString(getString(R.string.checkbox), "False");
        String url_link = mPreferences.getString(getString(R.string.url_link), "");

        mLink.setText(url_link);

        if (checkBox.equals("True")) {
            mCheckBox.setChecked(true);
        } else {
            mCheckBox.setChecked(false);
        }
    }
}