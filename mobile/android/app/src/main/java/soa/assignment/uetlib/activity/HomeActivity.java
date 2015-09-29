package soa.assignment.uetlib.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import soa.assignment.uetlib.R;
import soa.assignment.uetlib.adapter.PagerAdapter;
import soa.assignment.uetlib.model.Book;
import soa.assignment.uetlib.model.Category;


public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private static int page;

    public static String cookie;
    public static List<Book> bookItemList;
    public static List<Category> categoryItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        categoryItemList = new ArrayList<>();
        bookItemList = new ArrayList<>();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Categories"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                search();
            }
        });

        getCategories();
        page = 7;
        getBooks();
    }

    private void search() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void getBooks() {
        String url = "http://128.199.89.183:3000/books/page/" + String.valueOf(page);
        new GetBookTask(this, 1).execute(url);

//        try {
//            String data = new GetBookTask(this).execute(url).get();
//            JSONArray bookArray = new JSONArray(data);
//            for (int i = 0; i < bookArray.length(); i++) {
//                Book book = new Book(bookArray.getJSONObject(i));
//                bookItemList.add(book);
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//        }

        page++;
    }

    public void getCategories() {
        String url = "http://128.199.89.183:3000/categories";

//        try {
//            String data = new GetBookTask(this).execute(url).get();
//            JSONArray categoryArray = new JSONArray(data);
//            for (int i = 0; i < categoryArray.length(); i++) {
//                Category category = new Category(categoryArray.getJSONObject(i));
//                categoryItemList.add(category);
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//        }


        categoryItemList.add(new Category("c320", "Sách Tiếng Anh"));
        categoryItemList.add(new Category("c839", "Sách Văn Học - Tiểu Thuyết"));
        categoryItemList.add(new Category("c846", "Sách Kinh Tế"));
        categoryItemList.add(new Category("c873", "Sách Chuyên Ngành"));
        categoryItemList.add(new Category("c870", "Sách Kỹ Năng Sống - Nghệ Thuật Sống"));
        categoryItemList.add(new Category("c1012", "Sách Giáo Khoa - Tham Khảo"));
        categoryItemList.add(new Category("c887", "Sách Học Ngoại Ngữ - Từ Điển"));
        categoryItemList.add(new Category("c714", "Sách Cho Tuổi Mới Lớn"));
        categoryItemList.add(new Category("c393", "Sách Truyện Thiếu Nhi"));
        categoryItemList.add(new Category("c862", "Sách Thường Thức - Đời Sống"));
        categoryItemList.add(new Category("c1084", "Truyện Tranh, Manga, Comic"));
        categoryItemList.add(new Category("c857", "Sách Văn Hoá - Nghệ Thuật - Du Lịch"));
        categoryItemList.add(new Category("c1468", "Tạp Chí"));
        categoryItemList.add(new Category("c2527", "Sách Nuôi Dạy Con"));
        categoryItemList.add(new Category("c3447", "Sách Cũ Giá Tốt"));
        categoryItemList.add(new Category("c3550", "Combo - Series Sách Đặc Sắc"));
    }

    public void viewBook(int i) {
        Intent viewBook = new Intent(this, ViewBookActivity.class);
        viewBook.putExtra("index", i);
        viewBook.putExtra("from", 1);
        startActivity(viewBook);
    }

    public void viewCategory(String id, String name) {
        Intent viewCategory = new Intent(this, ViewCategoryActivity.class);
        viewCategory.putExtra("id", id);
        viewCategory.putExtra("name", name);
        startActivity(viewCategory);
    }
}
