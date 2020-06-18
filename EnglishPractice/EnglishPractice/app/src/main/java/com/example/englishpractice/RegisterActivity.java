package com.example.englishpractice;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class RegisterActivity extends AppCompatActivity {

    EditText uname_etx, email_etx, password_etx, password_conf_etx;
    private int STORAGE_PERMISSION_CODE = 1;
    private static final int TAKE_PICTURE = 1;
    Button submit_btn;
    ImageView userphoto;
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        submit_btn = (Button) findViewById(R.id.register_button);
        uname_etx = (EditText) findViewById(R.id.user_name_register);
        email_etx = (EditText) findViewById(R.id.email_register);
        password_etx = (EditText) findViewById(R.id.password_register);
        password_conf_etx = (EditText) findViewById(R.id.password_confirm_register);
        userphoto = (ImageView) findViewById(R.id.imageView);

        userphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        takeIntent();
                } else {
                    requestStoragePermission();
                }
            }
        });
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(RegisterActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takeIntent();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void takeIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    private boolean validate_name() {
        if (uname_etx.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (uname_etx.getText().toString().length() < 3) {
                Toast.makeText(this, "Minimum 3 Character", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }

    private boolean validate_password() {
        if (password_etx.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (password_etx.getText().toString().length() < 5) {
                Toast.makeText(this, "Minimum 5 Character", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }

    private boolean validate_password_confirm() {
        if (password_conf_etx.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (password_conf_etx.getText().toString().length() < 5) {
                Toast.makeText(this, "Minimum 5 Character", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }

    private boolean validate_email() {
        if (email_etx.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (email_etx.getText().toString().length() < 5) {
                Toast.makeText(this, "Minimum 5 Character", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }

    public void RegisterButton(View view) {
        String name = uname_etx.getText().toString();
        String email = email_etx.getText().toString();
        String password = password_etx.getText().toString();
        String password_cnf = password_conf_etx.getText().toString();

        if (validate_name() && validate_email() && validate_password() && validate_password_confirm()) {
            if (db.checkUserExist(name)) {
                if (password.equals(password_cnf)) {

                    //AsyncTask
                    BackgroundTask backgroundTask=new BackgroundTask(this);
                    backgroundTask.execute("addinfo",name,email,password);

                    StringBuffer buffer = new StringBuffer();
                    buffer.append(getString(R.string.user_name).toString() + ":" + name + "\n");
                    buffer.append(getString(R.string.email).toString() + ":" + email + "\n");
                    showMessage(getString(R.string.successful_register).toString(), buffer.toString());
                } else {
                    Toast.makeText(this, getString(R.string.password_match).toString(), Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, getString(R.string.already_exist).toString(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getString(R.string.empty_area).toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                userphoto.setImageBitmap(bmp);
            }
        }
    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("Close", null);
        builder.show();
    }

    public void LoginPage(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
