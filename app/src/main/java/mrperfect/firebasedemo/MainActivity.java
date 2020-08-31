package mrperfect.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private Button buttonRegister;
        private EditText editTextEmail;
        private EditText editTextPassword;
        private TextView textViewSignin;

        private ProgressDialog progressDialog;

        private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);


    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();

            //stopping the function excuting further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();

            //stopping the function excuting further
            return;
        }
        //if validations are OK
        // we will first show progressbar

        progressDialog.setMessage("Registering user ...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is successfully registered and loggin in
                            //we will start the profile activity here
                            //display a toast message
                            Toast.makeText(MainActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
             registerUser();
        }
        if (v == textViewSignin) {
            // open login activity
        }
    }
}