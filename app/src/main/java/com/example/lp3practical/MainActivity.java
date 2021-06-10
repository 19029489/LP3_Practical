package com.example.lp3practical;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyAdapter adapter;
    ViewPager vp;


    private String[] drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    ArrayAdapter<String> aa;
    String currentTitle;
    ActionBar ab;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = findViewById(R.id.vp);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new GuideFragment());
        al.add(new DrinkFragment());

        adapter = new MyAdapter(fm, al);

        vp.setAdapter(adapter);

        if (vp.getCurrentItem() == 0) {
            setTitle("Guide");
        } else {
            setTitle("Drink");
        }

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (vp.getCurrentItem() == 0) {
                    setTitle("Guide");
                } else {
                    setTitle("Drink");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);

        drawerItems = new String[] {"Guide", "Drink"};
        ab = getSupportActionBar();

        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, drawerItems);
        drawerList.setAdapter(aa);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fragment fragment = null;
                if (position == 0) {
                    fragment = new GuideFragment();
                    int previous = vp.getCurrentItem() - 1;
                    vp.setCurrentItem(previous, true);
                } else if (position == 1) {
                    fragment = new DrinkFragment();
                    int next = vp.getCurrentItem() + 1;
                    vp.setCurrentItem(next, true);
                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                trans.commit();

                drawerList.setItemChecked(position, true);
                currentTitle = drawerItems[position];
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList);

            }
        });

        currentTitle = this.getTitle().toString();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ab.setTitle("LP3 Practical");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ab.setTitle(currentTitle);
            }

        };

        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}