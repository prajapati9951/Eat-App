package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.example.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUpActivity extends AppCompatActivity {
    MaterialEditText edtName,edtPhone,edtPassword;
    Button btnSignUp;
    String phone,password,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        edtName=findViewById(R.id.edt_Name);
        edtPhone=findViewById(R.id.edt_Phone);
        edtPassword=findViewById(R.id.edt_Password);
        btnSignUp=(Button) findViewById(R.id.btn_SignUp);

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference().child("User");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=edtName.getText().toString();
                password=edtPassword.getText().toString();
                phone=edtPhone.getText().toString();

                final ProgressDialog mDailog = new ProgressDialog(SignUpActivity.this);
                mDailog.setMessage("please waiting !!!!");
                mDailog.show();
                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(phone).exists()) {
                            mDailog.dismiss();

                            Toast.makeText(SignUpActivity.this, "Already", Toast.LENGTH_SHORT).show();

                        } else {
                            mDailog.dismiss();

                            User user = new User(name, password);
                            table_user.child(phone).setValue(user);
                            Toast.makeText(SignUpActivity.this, "SignUp successfully  !!!", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(SignUpActivity.this,HomeActivity.class);
                            startActivity(i);
                            finish();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

}
