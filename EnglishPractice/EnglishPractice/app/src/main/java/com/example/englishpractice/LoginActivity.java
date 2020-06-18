package com.example.englishpractice;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    Database db = db = new Database(this);
    TextInputLayout uname_layout, password_layout;
    TextInputEditText uname_etx, password_etx;
    public static String login_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView login = (TextView) findViewById(R.id.log_in_text);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/indieflower.ttf");
        login.setTypeface(custom_font);

        uname_layout = (TextInputLayout) findViewById(R.id.username_lyt);
        password_layout = (TextInputLayout) findViewById(R.id.password_lyt);

        uname_etx = (TextInputEditText) findViewById(R.id.user_name_edit_text);
        password_etx = (TextInputEditText) findViewById(R.id.password_edit_text);
    }

    private boolean validate_password() {
        if (password_etx.getText().toString().trim().isEmpty()) {
            password_layout.setError("Enter password");
            requestFocus(password_etx);
            return false;
        } else {
            password_layout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_username() {
        if (uname_etx.getText().toString().trim().isEmpty()) {
            uname_layout.setError("Enter username");
            requestFocus(uname_etx);
            return false;
        } else {
            uname_layout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean CheckInfo() {
        String name = uname_etx.getText().toString().trim();
        String password = password_etx.getText().toString().trim();
        if (validate_username() && validate_password()) {
            if (db.checkUserExist(name)) {
                Toast.makeText(this,getString(R.string.not_found).toString(),Toast.LENGTH_SHORT).show();
                return false;
            } else {
                Cursor cursor = db.getPassword(name);
                cursor.moveToFirst();
                if (password.equals(cursor.getString(0))) {
                    login_username = uname_etx.getText().toString().trim();
                    return true;
                }else{
                    Toast.makeText(this,getString(R.string.wrong_password).toString(),Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        }
        return false;
    }


    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void checkUserInfo(View view) {
        if (CheckInfo()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void createNewUser(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
