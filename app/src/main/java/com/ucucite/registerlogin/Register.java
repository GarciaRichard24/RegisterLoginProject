package com.ucucite.registerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText username, password, repass, completename, address;
    Spinner status;
    RadioGroup gender;
    Button register, login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        DB = new DBHelper(this);

        username = (EditText)findViewById(R.id.r_username);
        password = (EditText)findViewById(R.id.r_password);
        repass= (EditText)findViewById(R.id.r_repass);
        completename = (EditText)findViewById(R.id.r_completename);
        address = (EditText)findViewById(R.id.r_address);
        gender = (RadioGroup)findViewById(R.id.gender);
        register = (Button)findViewById(R.id.register);
        login = (Button)findViewById(R.id.btnlogin1);

        status = (Spinner)findViewById(R.id.r_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 String itemValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String repassValue = repass.getText().toString();
                String completenameValue = completename.getText().toString();
                String addressValue = address.getText().toString();
                String statusValue = status.getSelectedItem().toString();
                RadioButton checkedbtn = findViewById(gender.getCheckedRadioButtonId());
                String genderValue = checkedbtn.getText().toString();

                if ( usernameValue.equals("")||passwordValue.equals("")||completenameValue.equals("")||addressValue.equals("")||
                        statusValue.equals("")||genderValue.equals(""))
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                else {
                    if (passwordValue.equals(repassValue)){
                        Boolean checkuser = DB.checkusername(usernameValue);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(usernameValue, passwordValue, completenameValue, addressValue, statusValue, genderValue);
                            if (insert==true){
                                Toast.makeText(Register.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                Intent int1 = new Intent(getApplicationContext(),Home.class);
                                startActivity(int1);
                            }else{
                                Toast.makeText(Register.this, "Register Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Register.this, "Username already exist!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "User Password not match!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Register.this, MainActivity.class);
                startActivity(int2);
            }
        });


    }
}
