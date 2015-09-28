package soa.assignment.uetlib.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.activity.HomeActivity;
import soa.assignment.uetlib.adapter.CategoryArrayAdapter;
import soa.assignment.uetlib.model.Category;

/**
 * Created by TuanTQ on 9/26/15.
 */
public class CategoryFragment extends android.support.v4.app.Fragment {
    private CategoryArrayAdapter categoryArrayAdapter;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryArrayAdapter = new CategoryArrayAdapter(getActivity().getApplicationContext(), R.layout.category_item_row);
        categoryArrayAdapter.setCategoryList(HomeActivity.categoryItemList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(categoryArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ((HomeActivity) getActivity()).viewCategory(i);
                Category category = categoryArrayAdapter.getItem(i);
                ((HomeActivity) getActivity()).viewCategory(category.getId(), category.getName());
            }
        });

        return view;
    }
}
