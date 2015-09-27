package soa.assignment.uetlib.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import soa.assignment.uetlib.R;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = (EditText) findViewById(R.id.input_username);
        passwordInput = (EditText) findViewById(R.id.input_password);

        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        signup = (TextView) findViewById(R.id.link_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void showDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        dialog.cancel();
                    }
                })
                .show();
    }

    private void goHome() {
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
        finish();
    }

    private boolean validate() {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (username.isEmpty() || password.isEmpty()) return false;

        return true;
    }

    private void login() {
        if (validate()) {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                    R.style.Theme_AppCompat_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            String url = "http://128.199.89.183:3000/mobile/login";
            try {
                boolean isLogin = new LoginTask(username, password).execute(url).get();
                progressDialog.cancel();
                if (isLogin) {
                    goHome();
                } else {
                    showDialog("Wrong username or password!");
                }
            } catch (Exception e) {
                showDialog("Oops! We can not connect to server.");
            }
        } else {
            showDialog("Enter valid username and password!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
