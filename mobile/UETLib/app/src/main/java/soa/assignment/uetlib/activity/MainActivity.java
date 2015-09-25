package soa.assignment.uetlib.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.fragment.BookFragment;
import soa.assignment.uetlib.fragment.HomeFragment;
import soa.assignment.uetlib.model.BookItem;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private HomeFragment homeFragment;
    private BookFragment bookFragment;
    private boolean showBookActionbar;
    private static int page;
    private ProgressDialog loadingDialog;

    public static List<BookItem> bookItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        showBookActionbar = false;

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        bookItemList = new ArrayList<>();
        page = 7;

        bookFragment = new BookFragment();
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_body, homeFragment).commit();
    }

    public void viewBook(int i) {
        Bundle data = new Bundle();
        data.putInt("index", i);

        bookFragment.setArguments(data);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_body, bookFragment).addToBackStack(null).commit();

//        getSupportActionBar().setHomeButtonEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(bookItemList.get(i).getTitle());

        showBookActionbar = true;
        invalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (showBookActionbar) {
            showBookActionbar = false;
            invalidateOptionsMenu();
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (showBookActionbar) {
            getMenuInflater().inflate(R.menu.menu_book, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Show search bar", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        switch (position) {
            case 0:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void getBooks() {
        String url = "http://128.199.89.183:3000/books/page/" + String.valueOf(page);

        try {
            String books = new GetBookTask(this).execute(url).get();
            JSONArray bookArray = new JSONArray(books);
            for (int i = 0; i < bookArray.length(); i++) {
                BookItem book = new BookItem(bookArray.getJSONObject(i));
                bookItemList.add(book);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        page++;
    }

    public void showLoadingDialog() {
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("Processing...");
        loadingDialog.setMessage("Please wait.");
        loadingDialog.setCancelable(false);
        loadingDialog.setIndeterminate(true);
        loadingDialog.show();
    }

    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }
}
