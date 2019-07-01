package sg.edu.rp.c346.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText etName, etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // Step 1: Get the user input from the EditText and store it in a variable
                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int rbID = rgGender.getCheckedRadioButtonId();

                // Step 2: Obtain an instance of the SharedPreferences
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                // Step 3: Obtain an instance of the SharedPreference Editor for update later
                SharedPreferences.Editor prefEdit = prefs.edit();

                // Step 4: Add the key-value pair
                prefEdit.putString("UserName", strName);
                prefEdit.putFloat("GPA", gpa);
                prefEdit.putInt("Gender", rbID);

                // Step 5: Call commit() to save the changes into SharedPreferences
                prefEdit.commit();

                Toast.makeText(MainActivity.this, "Data saved!", Toast.LENGTH_LONG).show();

            }
        });

        // This is a new line


    }

    /*
    @Override
    protected void onPause() {
        super.onPause();

        // Step 1: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int rbID = rgGender.getCheckedRadioButtonId();

        // Step 2: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 3: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 4: Add the key-value pair
        prefEdit.putString("UserName", strName);
        prefEdit.putFloat("GPA", gpa);
        prefEdit.putInt("Gender", rbID);

        // Step 5: Call commit() to save the changes into SharedPreferences
        prefEdit.commit();

    }
    */
    @Override
    protected void onResume() {
        super.onResume();

        // Step 6: Obtain an instance of the SharedPreferences
        SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(this);

        // Step 7: Retrieve the saved data from the SharedPreferences object
        String savedUser = prefs.getString("UserName", "");
        float  savedGPA = prefs.getFloat("GPA", 0.00f);
        int savedGender = prefs.getInt("Gender", 0);

        // Step 8: Update the UI element with the value
        etName.setText(savedUser);
        etGPA.setText(String.format("%.2f", savedGPA));
        rgGender.check(savedGender);
    }
}
