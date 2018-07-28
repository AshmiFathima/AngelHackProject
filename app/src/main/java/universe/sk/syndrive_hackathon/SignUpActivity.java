package universe.sk.syndrive_hackathon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText etName, etEmailsign, etPassign, etConfirmPassign, etBloodgroup, etDate, etEmName1, etEmName2, etEmName3, etEmNum1, etEmNum2, etEmNum3;
    private Button btn_register;
    private TextView tvExist;
    private FirebaseAuth firebaseAuth;
    String name, email, password, bloodgrp, date;
    String emname1, emname2, emname3;
    String emnum1, emnum2, emnum3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("SIGN UP");
        setUIviews();

        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate())
                {
                    String user_email = etEmailsign.getText().toString().trim();
                    String user_password = etPassign.getText().toString().trim();

                    //store in database:to be done after filling the contacts
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                               // adduser();
                                finish();
                                //startActivity(new Intent(SignUpActivity.this, NavigationActivity.class));
                                Toast.makeText(SignUpActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(SignUpActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        tvExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });
    }

    private void setUIviews() {
        etName = findViewById(R.id.etName);
        etEmName1 = findViewById(R.id.etEmName1);
        etEmName2 = findViewById(R.id.etEmName2);
        etEmName3 = findViewById(R.id.etEmName3);
        etBloodgroup = findViewById(R.id.etBloodgroup);
        etEmNum1 = findViewById(R.id.etEmNum1);
        etEmNum2 = findViewById(R.id.etEmNum2);
        etEmNum3 = findViewById(R.id.etEmNum3);
        etEmailsign = findViewById(R.id.etEmailsign);
        etPassign = findViewById(R.id.etPassign);
        etConfirmPassign = findViewById(R.id.etConfirmPassign);
        etDate = findViewById(R.id.etDate);
        btn_register = findViewById(R.id.btn_register);
        tvExist = findViewById(R.id.tvExist);

    }

    private Boolean validate()
    {
        Boolean result = false;
        bloodgrp = etBloodgroup.getText().toString().trim();
        date = etDate.getText().toString().trim();
        name = etName.getText().toString();
        password = etPassign.getText().toString();
        email = etEmailsign.getText().toString();
        emname1 = etEmName1.getText().toString();
        emnum1 =etEmNum1.getText().toString();
        emname2 = etEmName2.getText().toString();
        emnum2 = etEmNum2.getText().toString();
        emname3 = etEmName3.getText().toString();
        emnum3 = etEmNum3.getText().toString();

        String confirmpass = etConfirmPassign.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty())
        {
            Toast.makeText(this, "Please enter all the details!", Toast.LENGTH_SHORT).show();
        }
        else
        {   if(password.equals(confirmpass))
            result = true;
        else
            Toast.makeText(this, "Confirm password doesn't match with your password!", Toast.LENGTH_SHORT).show();
        }
        return result;
    }


}
