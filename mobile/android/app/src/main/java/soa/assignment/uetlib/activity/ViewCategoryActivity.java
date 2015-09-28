package soa.assignment.uetlib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.adapter.BookArrayAdapter;
import soa.assignment.uetlib.model.Book;

public class ViewCategoryActivity extends AppCompatActivity {
    private ListView listView;

    public static List<Book> bookItemList;
    public static BookArrayAdapter bookArrayAdapter;
    public static int page;
    public static boolean loadAll;
    private String id;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);

        loadAll = false;
        bookItemList = new ArrayList<>();

        id = getIntent().getExtras().getString("id");
        name = getIntent().getExtras().getString("name");

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);

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

        page = 1;
        getBooks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getBooks() {
        loadAll = false;
        String url = "http://128.199.89.183:3000/books/category/" + id + "/" + String.valueOf(page);
        new GetBookTask(this, 2).execute(url);
    }

    public void viewBook(int i) {
        Intent viewBook = new Intent(this, ViewBookActivity.class);
        viewBook.putExtra("index", i);
        viewBook.putExtra("from", 2);
        startActivity(viewBook);
    }
}
