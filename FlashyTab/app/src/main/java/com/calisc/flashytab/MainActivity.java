package com.calisc.flashytab;

import android.os.Bundle;

import com.calisc.flashytab.FlashyTab.FlashyTab;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private FlashyTab flashyTab = null;
    private String[] titles = new String[]{"Events", "Highlights", "Search", "Settings"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //
        mFragmentList.add(new TabFragment(titles[0]));
        mFragmentList.add(new TabFragment(titles[1]));
        mFragmentList.add(new TabFragment(titles[2]));
        mFragmentList.add(new TabFragment(titles[3]));

        ViewPager viewPager = findViewById(R.id.view_pager);
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };
        viewPager.setAdapter(adapter);

        // Generate tab
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        flashyTab = new FlashyTab(tabLayout);
        flashyTab.addTabItem(titles[0], R.drawable.ic_events, null, null);
        flashyTab.addTabItem(titles[1], R.drawable.ic_highlights, null, null);
        flashyTab.addTabItem(titles[2], R.drawable.ic_search, null, null);
        flashyTab.addTabItem(titles[3], R.drawable.ic_settings, null, null);
        flashyTab.highLightTab(0);

        viewPager.addOnPageChangeListener(flashyTab);
    }

    @Override
    protected void onStart() {
        super.onStart();
        flashyTab.onStart((TabLayout) findViewById(R.id.tabLayout));
    }

    @Override
    protected void onStop() {
        super.onStop();
        flashyTab.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
