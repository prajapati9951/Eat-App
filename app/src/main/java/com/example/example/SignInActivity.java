package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.example.Common.Common;
import com.example.example.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignInActivity extends AppCompatActivity {

    MaterialEditText edtName,edt_Phone,edt_Password;
    Button btnSignin;
    String phone="",password="";
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in2);
       /* edtPhone=(MaterialEditText) findViewById(R.id.etPhone2);
        edtPassword=(MaterialEditText) findViewById(R.id.etPassword2);
        btnSignin=findViewById(R.id.btnSignin2);
        phone=edtPhone.getText().toString();
        password=edtPassword.getText().toString();*/

        edt_Phone=(MaterialEditText) findViewById(R.id.edt_Phone_signin);
        edt_Password=(MaterialEditText) findViewById(R.id.edt_Password_signin);

        btn=(Button) findViewById(R.id.btnSignin2);
        //name=edtName.getText().toString();
        password=edt_Password.getText().toString();
        phone=edt_Phone.getText().toString();
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference().child("User");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edt_Phone=findViewById(R.id.edt_Phone_signin);
                edt_Password=findViewById(R.id.edt_Password_signin);

                final ProgressDialog mDailog = new ProgressDialog(SignInActivity.this);
                mDailog.setMessage("please waiting !!!!");
                mDailog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (!phone.equals("") || !password.equals("")) {
                            mDailog.dismiss();
                            if (dataSnapshot.child(phone).exists()) {
                                User user = dataSnapshot.child(phone).getValue(User.class);
                                if (user.getPassword().equals(password)) {
                                    Toast.makeText(SignInActivity.this, "SignIn Successfully !!", Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(SignInActivity.this,HomeActivity.class);
                                    Common.currentUser=user;
                                    startActivity(i);
                                } else {
                                    Toast.makeText(SignInActivity.this, "Failed !!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                mDailog.dismiss();
                                Toast.makeText(SignInActivity.this, "User not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDailog.dismiss();
                            Toast.makeText(SignInActivity.this, "Please fill all the values!!!!", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        mDailog.dismiss();
                        Toast.makeText(SignInActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
    }
}
