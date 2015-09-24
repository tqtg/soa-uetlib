package soa.assignment.uetlib.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.activity.MainActivity;
import soa.assignment.uetlib.adapter.BookArrayAdapter;

/**
 * Created by TuanTQ on 9/23/15.
 */
public class HomeFragment extends android.support.v4.app.Fragment {
    private BookArrayAdapter bookArrayAdapter;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookArrayAdapter = new BookArrayAdapter(getActivity().getApplicationContext(), R.layout.book_item_row);
        bookArrayAdapter.setBookList(MainActivity.bookItemList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(bookArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle data = new Bundle();
                data.putInt("index", i);

                ((MainActivity) getActivity()).viewBook(data);
            }
        });


        return view;
    }
}
