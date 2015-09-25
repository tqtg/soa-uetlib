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
 * Created by TuanTQ on 9/23/15.
 */
public class HomeFragment extends android.support.v4.app.Fragment {
    private BookArrayAdapter bookArrayAdapter;
    private ListView listView;
    private boolean flag_loading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity)getActivity()).getBooks();
        bookArrayAdapter = new BookArrayAdapter(getActivity().getApplicationContext(), R.layout.book_item_row);
        bookArrayAdapter.setBookList(MainActivity.bookItemList);
        flag_loading = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
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
                        additems();
                    }
                }

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0)
//                {
//                    if (flag_loading == false)
//                    {
//                        flag_loading = true;
//                        additems();
//                    }
//                }
            }
        });

        return view;
    }

    private void additems() {
        ((MainActivity)getActivity()).getBooks();
        bookArrayAdapter.notifyDataSetChanged();
        flag_loading = false;
    }
}
