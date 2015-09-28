package soa.assignment.uetlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.model.Category;

/**
 * Created by TuanTQ on 9/26/15.
 */
public class CategoryArrayAdapter extends ArrayAdapter<Category> {
    private static final String TAG = "BookArrayAdapter";
    private List<Category> categoryList = new ArrayList<>();


    public CategoryArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(Category object) {
        categoryList.add(object);
        super.add(object);
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return this.categoryList.size();
    }

    @Override
    public Category getItem(int index) {
        return this.categoryList.get(index);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View row = convertView;
        TextView categoryName;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.category_item_row, parent, false);
        }

        Category category = getItem(position);
        categoryName = (TextView) row.findViewById(R.id.categoryName);
        categoryName.setText(category.getName());

        return row;
    }

}