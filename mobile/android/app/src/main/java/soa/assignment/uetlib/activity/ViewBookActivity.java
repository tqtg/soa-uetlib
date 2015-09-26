package soa.assignment.uetlib.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.model.Book;

public class ViewBookActivity extends AppCompatActivity {
    private ImageView bookCover;
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookPublisher;
    private TextView bookDate;
    private TextView bookPage;
    private TextView bookDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        
        int i = getIntent().getExtras().getInt("index");
        int from = getIntent().getExtras().getInt("from");
        Book book = new Book();
        switch (from) {
            case 1:
                book = MainActivity.bookItemList.get(i);
                break;
            case 2:
                book = ViewCategoryActivity.bookItemList.get(i);
                break;
            case 3:
                book = SearchActivity.bookItemList.get(i);
                break;
        }

        String title = book.getTitle();
        String author = book.getAuthor();
        String publisher = book.getPublisher();
        String date = book.getDate();
        String description = book.getDescription();
        int page = book.getPage();
        Drawable image = book.getImage();

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        bookCover = (ImageView) findViewById(R.id.bookCover);
        bookCover.setImageDrawable(image);

        bookTitle = (TextView) findViewById(R.id.bookTitle);
        bookTitle.setText(title);

        bookAuthor = (TextView) findViewById(R.id.bookAuthor);
        bookAuthor.setText("Author: " + author);

        bookPublisher = (TextView) findViewById(R.id.bookPublisher);
        if (publisher != null) bookPublisher.setText("Publisher: " + publisher);

        bookDate = (TextView) findViewById(R.id.bookDate);
        if (date != null) bookDate.setText("Published on: " + date);

        bookPage = (TextView) findViewById(R.id.bookPage);
        if (page > 0) bookPage.setText("Page numbers: " + String.valueOf(page));

        bookDescription = (TextView) findViewById(R.id.bookDescription);
        if (description != null) bookDescription.setText(description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_book, menu);
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
}
