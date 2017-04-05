package tonymaluk.com.loginactivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.imv_logo)
    ImageView imvLogo;
    @BindView(R.id.edt_login)
    AutoCompleteTextView edtLogin;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.tv_sign_in)
    TextView getTvSignIn;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btnLogin = (Button) findViewById(R.id.btn_log_in);

        /*
        putIndexToList();//take saved logins from database. Create database first.

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, loginList);
        edtLogin.setAdapter(adapter);
         */
    }

    public void onClickLogin(View view) throws UnsupportedEncodingException {

        //ContentValues cv = new ContentValues();

        // Reset errors.
        edtLogin.setError(null);
        edtPassword.setError(null);

        String login = edtLogin.getText().toString();
        String password = edtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            edtPassword.setError("Invalid password");
            focusView = edtPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Field required");
            focusView = edtPassword;
            cancel = true;
        }

        // Check for a valid login.
        if (TextUtils.isEmpty(login)) {
            edtLogin.setError("Field required");
            focusView = edtLogin;
            cancel = true;
        } else if (!isLoginValid(login)) {
            edtLogin.setError("Invalid login");
            focusView = edtLogin;
            cancel = true;
        } else if (login.length() < 13) {
            edtLogin.setError("Login is to short");
            focusView = edtLogin;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();// There was an error, don't attempt login and focus the first form field with an error.
        } else {
            /* Check for login repeat in database.
            if(!phoneList.contains(login)) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                cv.put("PHONE", login);
                db.insert("LOGIN", null, cv);
            }
            else dbHelper.close();
            */

            //TODO: Implement method with authenticating logic here.
        }
    }

    public void onClickSignIn(View view) {

    }

    private boolean isLoginValid(String login) {
        return login.contains("@");
    } //Or contains something else.

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }


    /* Save new login data in database.
    public void putIndexToList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("LOGIN", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int nameColumIndex = cursor.getColumnIndex("PHONE");

            do {
                phoneList.add(cursor.getString(nameColumIndex));
            }
            while (cursor.moveToNext());
        }
        else cursor.close();
        dbHelper.close();
    }
    */

    // Disable going back to the MainActivity
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
