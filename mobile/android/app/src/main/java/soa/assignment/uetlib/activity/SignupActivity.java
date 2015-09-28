package soa.assignment.uetlib.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SignupActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Button createAccount;
    private TextView login;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameInput = (EditText) findViewById(R.id.input_username);
        passwordInput = (EditText) findViewById(R.id.input_password);

        createAccount = (Button) findViewById(R.id.btn_login);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        login = (TextView) findViewById(R.id.link_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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

    private void signup() {
        if (validate()) {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            String url = "http://128.199.89.183:3000/mobile/signup";
            new SignupTask(username, password).execute(url);
        } else {
            showDialog("Enter valid username and password!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
        pDialog = ProgressDialog.show(this, "", "Signing up...");
    }

    private void hideLoadingDialog() {
        pDialog.cancel();
    }

    class SignupTask extends AsyncTask<String, String, Boolean> {
        private String username;
        private String password;

        public SignupTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        protected Boolean doInBackground(String... urls) {
            String url = urls[0] + "?username=" + username + "&password=" + password;
            AndroidHttpClient httpClient = AndroidHttpClient.newInstance("SOA");
            HttpPost httpPost = new HttpPost(url);

            Log.d("soa_signup", "signing up...");

            try {
                HttpResponse response = httpClient.execute(httpPost);
                Log.d("soa_signup", response.getStatusLine().getReasonPhrase());
                if (response.getStatusLine().getStatusCode() == 200)
                    return true;
            } catch (ClientProtocolException e) {
                // Log exception
                e.printStackTrace();
            } catch (IOException e) {
                // Log exception
                e.printStackTrace();
            }

            Log.d("soa_signup", "Error!");
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
                goHome();
            } else {
                showDialog("That username is already taken!");
            }
        }
    }
}
