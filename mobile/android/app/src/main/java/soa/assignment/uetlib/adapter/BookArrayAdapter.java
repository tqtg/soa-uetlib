package soa.assignment.uetlib.adapter;

/**
 * Created by TuanTQ on 9/23/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.model.Book;

public class BookArrayAdapter extends ArrayAdapter<Book> {
    private static final String TAG = "BookArrayAdapter";
    private List<Book> bookList = new ArrayList<>();

    static class BookViewHolder {
        ImageView image;
        TextView title;
        TextView author;
    }

    public BookArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(Book object) {
        bookList.add(object);
        super.add(object);
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return this.bookList.size();
    }

    @Override
    public Book getItem(int index) {
        return this.bookList.get(index);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View row = convertView;
        BookViewHolder viewHolder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.book_item_row, parent, false);
            viewHolder = new BookViewHolder();
            viewHolder.image = (ImageView) row.findViewById(R.id.bookCover);
            viewHolder.title = (TextView) row.findViewById(R.id.bookTitle);
            viewHolder.author = (TextView) row.findViewById(R.id.bookAuthor);
            row.setTag(viewHolder);
        } else {
            viewHolder = (BookViewHolder)row.getTag();
        }

        final Book book = getItem(position);
        viewHolder.image.setImageDrawable(book.getImage());
        viewHolder.title.setText(book.getTitle());
        viewHolder.author.setText(book.getAuthor());

        return row;
    }

}