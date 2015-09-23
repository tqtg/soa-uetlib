package soa.assignment.uetlib.adapter;

/**
 * Created by TuanTQ on 9/23/15.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.model.BookItem;

public class BookArrayAdapter extends ArrayAdapter<BookItem> {
    private static final String TAG = "FruitArrayAdapter";
    private List<BookItem> bookList = new ArrayList<BookItem>();

    static class FruitViewHolder {
        ImageView image;
        TextView title;
        TextView author;
    }

    public BookArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(BookItem object) {
        bookList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.bookList.size();
    }

    @Override
    public BookItem getItem(int index) {
        return this.bookList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FruitViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.book_item_row, parent, false);
            viewHolder = new FruitViewHolder();
            viewHolder.image = (ImageView) row.findViewById(R.id.bookImage);
            viewHolder.title = (TextView) row.findViewById(R.id.bookTitle);
            viewHolder.author = (TextView) row.findViewById(R.id.bookAuthor);
            row.setTag(viewHolder);
        } else {
            viewHolder = (FruitViewHolder)row.getTag();
        }
        BookItem book = getItem(position);
//        viewHolder.image.setImageResource(book.getImage());
        viewHolder.title.setText(book.getTitle());
        viewHolder.author.setText(book.getAuthor());
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}