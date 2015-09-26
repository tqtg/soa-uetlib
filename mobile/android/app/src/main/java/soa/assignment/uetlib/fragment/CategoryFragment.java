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
//        categoryArrayAdapter.setCategoryList(HomeActivity.categoryItemList);

        categoryArrayAdapter.add(new Category("c320", "Sách Tiếng Anh"));
        categoryArrayAdapter.add(new Category("c839", "Sách Văn Học - Tiểu Thuyết"));
        categoryArrayAdapter.add(new Category("c846", "Sách Kinh Tế"));
        categoryArrayAdapter.add(new Category("c873", "Sách Chuyên Ngành"));
        categoryArrayAdapter.add(new Category("c870", "Sách Kỹ Năng Sống - Nghệ Thuật Sống"));
        categoryArrayAdapter.add(new Category("c1012", "Sách Giáo Khoa - Tham Khảo"));
        categoryArrayAdapter.add(new Category("c887", "Sách Học Ngoại Ngữ - Từ Điển"));
        categoryArrayAdapter.add(new Category("c714", "Sách Cho Tuổi Mới Lớn"));
        categoryArrayAdapter.add(new Category("c393", "Sách Truyện Thiếu Nhi"));
        categoryArrayAdapter.add(new Category("c862", "Sách Thường Thức - Đời Sống"));
        categoryArrayAdapter.add(new Category("c1084", "Truyện Tranh, Manga, Comic"));
        categoryArrayAdapter.add(new Category("c857", "Sách Văn Hoá - Nghệ Thuật - Du Lịch"));
        categoryArrayAdapter.add(new Category("c1468", "Tạp Chí"));
        categoryArrayAdapter.add(new Category("c2527", "Sách Nuôi Dạy Con"));
        categoryArrayAdapter.add(new Category("c3447", "Sách Cũ Giá Tốt"));
        categoryArrayAdapter.add(new Category("c3550", "Combo - Series Sách Đặc Sắc"));
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
