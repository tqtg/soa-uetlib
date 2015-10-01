package soa.assignment.uetlib.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import soa.assignment.uetlib.fragment.AllBookFragment;
import soa.assignment.uetlib.model.Book;

/**
 * Created by TuanTQ on 9/23/15.
 */
public class GetBookTask extends AsyncTask<String, String, String> {
    private Context context;
    private int invoker;
    private ProgressDialog pDialog;
    public static final int INVOKER_HOME = 1;
    public static final int INVOKER_CATEGORY = 2;
    public static final int INVOKER_SEARCH = 3;

    public GetBookTask(Context context, int invoker) {
        this.context = context;
        this.invoker = invoker;
    }

    protected String doInBackground(String... urls) {
        String result = "[ ]";
        String url = urls[0];

        Log.d("soa_getData", "Loading...");

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // add request header
            con.setRequestMethod("GET");
            con.setRequestProperty("Cookie", HomeActivity.cookie);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending GET request to URL: " + url);
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            result = response.toString();
        } catch (Exception e) {
            Log.d("soa_getData", "Error!");
        }

        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = ProgressDialog.show(context, "", "Loading...");
    }

    protected void onProgressUpdate(String... progress) {
        super.onProgressUpdate();
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONArray bookArray = new JSONArray(result);
            List<Book> bookItemList = new ArrayList<>();
            for (int i = 0; i < bookArray.length(); i++) {
                JSONObject bookObj = bookArray.getJSONObject(i);
                Book book = new Book(bookObj);
                new GetImageTask(book).execute(bookObj.getString("image"));
                bookItemList.add(book);
            }

            switch (invoker) {
                case INVOKER_HOME:
                    HomeActivity.bookItemList.addAll(bookItemList);
                    AllBookFragment.bookArrayAdapter.notifyDataSetChanged();
                    break;
                case INVOKER_CATEGORY:
                    if (bookItemList.size() > 0) {
                        ViewCategoryActivity.bookItemList.addAll(bookItemList);
                        ViewCategoryActivity.bookArrayAdapter.notifyDataSetChanged();
                        if (bookItemList.size() < 16) ViewCategoryActivity.loadAll = true;
                        else ViewCategoryActivity.page++;
                    } else {
                        ViewCategoryActivity.loadAll = true;
                    }
                    break;
                case INVOKER_SEARCH:
                    if (bookItemList.size() > 0) {
                        SearchActivity.bookItemList.addAll(bookItemList);
                        SearchActivity.bookArrayAdapter.notifyDataSetChanged();
                        if (bookItemList.size() < 16) SearchActivity.loadAll = true;
                        else SearchActivity.page++;
                    } else {
                        SearchActivity.loadAll = true;
                        new AlertDialog.Builder(context)
                                .setMessage("Nothing found!")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }

        pDialog.cancel();
    }

    class GetImageTask extends AsyncTask<String, Integer, Drawable> {
        private Book book;

        public GetImageTask(Book book) {
            this.book = book;
        }

        @Override
        protected Drawable doInBackground(String... arg0) {
            // This is done in a background thread
            return downloadImage(arg0[0]);
        }

        /**
         * Called after the image has been downloaded
         * -> this calls a function on the main thread again
         */
        protected void onPostExecute(Drawable image)
        {
            this.book.setImage(image);

            switch (invoker) {
                case INVOKER_HOME:
//                    HomeActivity.bookItemList.addAll(bookItemList);
                    AllBookFragment.bookArrayAdapter.notifyDataSetChanged();
                    break;
                case INVOKER_CATEGORY:
//                    ViewCategoryActivity.bookItemList.addAll(bookItemList);
                    ViewCategoryActivity.bookArrayAdapter.notifyDataSetChanged();
                    break;
                case INVOKER_SEARCH:
//                    SearchActivity.bookItemList.addAll(bookItemList);
                    SearchActivity.bookArrayAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }


        /**
         * Actually download the Image from the _url
         * @param _url
         * @return
         */
        private Drawable downloadImage(String _url)
        {
            //Prepare to download image
            URL url;
            InputStream in;
            BufferedInputStream buf;

            try {
                url = new URL(_url);
                in = url.openStream();

                // Read the inputstream
                buf = new BufferedInputStream(in);

                // Convert the BufferedInputStream to a Bitmap
                Bitmap bMap = BitmapFactory.decodeStream(buf);
                if (in != null) {
                    in.close();
                }
                if (buf != null) {
                    buf.close();
                }

                return new BitmapDrawable(bMap);

            } catch (Exception e) {
                Log.e("Error reading file", e.toString());
            }

            return null;
        }

    }
}
