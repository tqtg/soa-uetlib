package soa.assignment.uetlib.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;

import java.io.IOException;

import soa.assignment.uetlib.R;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private TextView signup;

    private ProgressDialog pDialog;
    public static final int LOADING_DIALOG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = (EditText) findViewById(R.id.input_username);
        passwordInput = (EditText) findViewById(R.id.input_password);
//        getUserInfo();

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

    private void showAlertDialog(String message) {
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

            String url = "http://128.199.89.183:3000/mobile/login";
            new LoginTask(username, password).execute(url);
        } else {
            showAlertDialog("Enter valid username and password!");
        }
    }

    private void saveUserInfo(String username, String password) {
        SharedPreferences settings = getSharedPreferences("UETLib", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    private void getUserInfo() {
        SharedPreferences settings = getSharedPreferences("UETLib", 0);
        String username = settings.getString("username", "");
        String password = settings.getString("password", "");
        usernameInput.setText(username);
        passwordInput.setText(password);
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

    private void showLoadingDialog() {
        pDialog = ProgressDialog.show(this, "", "Authenticating...");
    }

    private void hideLoadingDialog() {
        pDialog.cancel();
    }

    class LoginTask extends AsyncTask<String, String, Boolean> {
        private String username;
        private String password;

        public LoginTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        protected Boolean doInBackground(String... urls) {
            String url = urls[0] + "?username=" + username + "&password=" + password;
            AndroidHttpClient httpClient = AndroidHttpClient.newInstance("SOA");
            HttpPost httpPost = new HttpPost(url);

            Log.d("soa_login", "authenticating...");

            try {
                HttpResponse response = httpClient.execute(httpPost);
                Log.d("soa_login", String.valueOf(response.getStatusLine().getStatusCode()));
                if (response.getStatusLine().getStatusCode() == 200)
                    return true;
            } catch (ClientProtocolException e) {
                // Log exception
                e.printStackTrace();
            } catch (IOException e) {
                // Log exception
                e.printStackTrace();
            }

            Log.d("soa_login", "Error!");
            return false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoadingDialog();
        }

        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate();
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            hideLoadingDialog();

            if (result) {
//                    saveUserInfo(username, password);
                goHome();
            } else {
                showAlertDialog("Wrong username or password!");
            }
        }
    }
}
