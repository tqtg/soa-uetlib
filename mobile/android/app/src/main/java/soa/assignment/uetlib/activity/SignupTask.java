package soa.assignment.uetlib.activity;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by TuanTQ on 9/23/15.
 */
public class SignupTask extends AsyncTask<String, String, Boolean> {
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

        Log.d("soa_login", "Error!");
        return false;
    }

    protected void onProgressUpdate(String... progress) {
        super.onProgressUpdate();
    }

    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
    }

    private static String convertStreamToString(InputStream is) {
    /*
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
