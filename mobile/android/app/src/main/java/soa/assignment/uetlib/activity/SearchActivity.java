package soa.assignment.uetlib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.adapter.BookArrayAdapter;
import soa.assignment.uetlib.model.Book;

public class SearchActivity extends AppCompatActivity {
    private ListView listView;
    private EditText search;

    public static List<Book> bookItemList;
    public static BookArrayAdapter bookArrayAdapter;
    public static int page;
    private String query;
    public static boolean loadAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookItemList = new ArrayList<>();
        page = 1;
        loadAll = false;

        search = (EditText) findViewById(R.id.searchQuery);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search();
                    return true;
                }
                return false;
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        bookArrayAdapter = new BookArrayAdapter(this, R.layout.book_item_row);
        bookArrayAdapter.setBookList(bookItemList);
        listView.setAdapter(bookArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewBook(i);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                int threshold = 1;
                int count = listView.getCount();

                if (scrollState == SCROLL_STATE_IDLE && !loadAll) {
                    if (listView.getLastVisiblePosition() >= count - threshold) {
                        // Execute LoadMoreDataTask AsyncTask
                        getBooks();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            search();
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void search() {
        page = 1;
        loadAll = false;
        bookItemList.clear();
        bookArrayAdapter.notifyDataSetChanged();

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        try {
            query = URLEncoder.encode(search.getText().toString(), "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            query = URLEncoder.encode(search.getText().toString()).replace("+", "%20");
        }

        getBooks();
    }

    public void getBooks() {
        String url = "http://128.199.89.183:3000/books/search/" + query + "/page/" + String.valueOf(page);
        new GetBookTask(this, 3).execute(url);
    }

    public void viewBook(int i) {
        Intent viewBook = new Intent(this, ViewBookActivity.class);
        viewBook.putExtra("index", i);
        viewBook.putExtra("from", 3);
        startActivity(viewBook);
    }
}
