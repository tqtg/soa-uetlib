package soa.assignment.uetlib.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.activity.MainActivity;
import soa.assignment.uetlib.model.BookItem;

/**
 * Created by TuanTQ on 9/23/15.
 */
public class BookFragment extends android.support.v4.app.Fragment {
    private ImageView bookCover;
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookPublisher;
    private TextView bookDate;
    private TextView bookPage;
    private TextView bookDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        BookItem book = MainActivity.bookItemList.get(getArguments().getInt("index"));

        String title = book.getTitle();
        String author = book.getAuthor();
        String publisher = book.getPublisher();
        String date = book.getDate();
        String description = book.getDescription();
        int page = book.getPage();
        Drawable image = book.getImage();

        bookCover = (ImageView) view.findViewById(R.id.bookCover);
        bookCover.setImageDrawable(image);

        bookTitle = (TextView) view.findViewById(R.id.bookTitle);
        bookTitle.setText(title);

        bookAuthor = (TextView) view.findViewById(R.id.bookAuthor);
        bookAuthor.setText("Author: " + author);

        bookPublisher = (TextView) view.findViewById(R.id.bookPublisher);
        if (publisher != null) bookPublisher.setText("Publisher: " + publisher);

        bookDate = (TextView) view.findViewById(R.id.bookDate);
        if (date != null) bookDate.setText("Published on: " + date);

        bookPage = (TextView) view.findViewById(R.id.bookPage);
        if (page > 0) bookPage.setText("Page numbers: " + String.valueOf(page));

        bookDescription = (TextView) view.findViewById(R.id.bookDescription);
        if (description != null) bookDescription.setText(description);

        return view;
    }
}
