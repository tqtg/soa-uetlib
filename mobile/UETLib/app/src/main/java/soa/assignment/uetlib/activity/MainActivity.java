package soa.assignment.uetlib.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.adapter.BookArrayAdapter;
import soa.assignment.uetlib.model.BookItem;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private BookArrayAdapter bookArrayAdapter;
    private ListView listView;
    private static int colorIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        colorIndex = 0;
        listView = (ListView) findViewById(R.id.listView);
        bookArrayAdapter = new BookArrayAdapter(getApplicationContext(), R.layout.book_item_row);
        listView.setAdapter(bookArrayAdapter);

        List<String[]> bookList = readData();
        for(String[] bookData : bookList ) {
//            String image = fruitData[0];
            String title = bookData[1];
            String author = bookData[2];
            int image = 1;

            BookItem book = new BookItem(image, title, author);
            bookArrayAdapter.add(book);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
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

    public List<String[]> readData(){
        List<String[]> resultList = new ArrayList<>();

        String[] fruit7 = new String[3];
        fruit7[0] = "orange";
        fruit7[1] = "Orange";
        fruit7[2] = "47 Calories";
        resultList.add(fruit7);

        String[] fruit1 = new String[3];
        fruit1[0] = "cherry";
        fruit1[1] = "Cherry";
        fruit1[2] = "50 Calories";
        resultList.add(fruit1);


        String[] fruit3 = new String[3];
        fruit3[0] = "banana";
        fruit3[1] = "Banana";
        fruit3[2] = "89 Calories";
        resultList.add(fruit3);

        String[] fruit4 = new String[3];
        fruit4[0] = "apple";
        fruit4[1] = "Apple";
        fruit4[2] = "52 Calories";
        resultList.add(fruit4);

        String[] fruit10 = new String[3];
        fruit10[0] = "kiwi";
        fruit10[1] = "Kiwi";
        fruit10[2] = "61 Calories";
        resultList.add(fruit10);

        String[] fruit5 = new String[3];
        fruit5[0] = "pear";
        fruit5[1] = "Pear";
        fruit5[2] = "57 Calories";
        resultList.add(fruit5);


        String[] fruit2 = new String[3];
        fruit2[0] = "strawberry";
        fruit2[1] = "Strawberry";
        fruit2[2] = "33 Calories";
        resultList.add(fruit2);

        String[] fruit6 = new String[3];
        fruit6[0] = "lemon";
        fruit6[1] = "Lemon";
        fruit6[2] = "29 Calories";
        resultList.add(fruit6);

        String[] fruit8 = new String[3];
        fruit8[0] = "peach";
        fruit8[1] = "Peach";
        fruit8[2] = "39 Calories";
        resultList.add(fruit8);

        String[] fruit9 = new String[3];
        fruit9[0] = "apricot";
        fruit9[1] = "Apricot";
        fruit9[2] = "48 Calories";
        resultList.add(fruit9);

        String[] fruit11 = new String[3];
        fruit11[0] = "mango";
        fruit11[1] = "Mango";
        fruit11[2] = "60 Calories";
        resultList.add(fruit11);

        return  resultList;
    }
}
