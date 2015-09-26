package soa.assignment.uetlib.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.activity.MainActivity;
import soa.assignment.uetlib.adapter.BookArrayAdapter;

/**
 * Created by TuanTQ on 9/26/15.
 */
public class AllBookFragment extends android.support.v4.app.Fragment {
    private BookArrayAdapter bookArrayAdapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allbook, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        bookArrayAdapter = new BookArrayAdapter(getActivity().getApplicationContext(), R.layout.book_item_row);
        bookArrayAdapter.setBookList(MainActivity.bookItemList);
        listView.setAdapter(bookArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((MainActivity) getActivity()).viewBook(i);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                int threshold = 1;
                int count = listView.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (listView.getLastVisiblePosition() >= count - threshold) {
                        // Execute LoadMoreDataTask AsyncTask
                        addItems();
                    }
                }

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        return view;
    }

    private void addItems() {
        ((MainActivity) getActivity()).getBooks();
        bookArrayAdapter.notifyDataSetChanged();
    }
}
