package com.mikepenz.itemanimators.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.itemanimators.AlphaInAnimator;
import com.mikepenz.itemanimators.BaseItemAnimator;
import com.mikepenz.itemanimators.ScaleUpAnimator;
import com.mikepenz.itemanimators.ScaleXAnimator;
import com.mikepenz.itemanimators.ScaleYAnimator;
import com.mikepenz.itemanimators.SlideDownAlphaAnimator;
import com.mikepenz.itemanimators.SlideLeftAlphaAnimator;
import com.mikepenz.itemanimators.SlideRightAlphaAnimator;
import com.mikepenz.itemanimators.SlideUpAlphaAnimator;
import com.mikepenz.itemanimators.app.dummy.ImageDummyData;
import com.mikepenz.itemanimators.app.items.ImageItem;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SampleActivity extends AppCompatActivity {
    enum Type {
        CrossFade(new AlphaCrossFadeAnimator()),
        FadeIn(new AlphaInAnimator()),
        ScaleUp(new ScaleUpAnimator()),
        ScaleX(new ScaleXAnimator()),
        ScaleY(new ScaleYAnimator()),
        SlideDownAlpha(new SlideDownAlphaAnimator()),
        SlideLeftAlpha(new SlideLeftAlphaAnimator()),
        SlideRightAlpha(new SlideRightAlphaAnimator()),
        SlideUpAlpha(new SlideUpAlphaAnimator());

        private BaseItemAnimator mAnimator;

        Type(BaseItemAnimator animator) {
            mAnimator = animator;
        }

        public BaseItemAnimator getAnimator() {
            return mAnimator;
        }
    }

    //save our header or result
    private Drawer mResult = null;
    //our rv
    RecyclerView mRecyclerView;
    //save our FastAdapter
    private FastAdapter mFastAdapter;
    //save our FastAdapter
    private ItemAdapter<IItem> mItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        //improve ui
        findViewById(android.R.id.content).setSystemUiVisibility(findViewById(android.R.id.content).getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //Create the drawer
        mResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.open_source).withSelectable(false).withIdentifier(100).withIcon(MaterialDesignIconic.Icon.gmi_github)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 100) {
                                intent = new LibsBuilder()
                                        .withFields(R.string.class.getFields())
                                        .withActivityTitle(getString(R.string.open_source))
                                        .withActivityStyle(Libs.ActivityStyle.LIGHT)
                                        .intent(SampleActivity.this);
                            }
                            if (intent != null) {
                                SampleActivity.this.startActivity(intent);
                            }
                        }
                        return false;
                    }
                })
                .build();

        //create our ItemAdapter which will host our items
        mItemAdapter = new ItemAdapter<>();

        //create our FastAdapter which will manage everything
        mFastAdapter = FastAdapter.with(mItemAdapter);
        mFastAdapter.withSelectable(true);
        mFastAdapter.withMultiSelect(true);
        mFastAdapter.withSelectOnLongClick(false);

        //configure our fastAdapter
        //get our recyclerView and do basic setup
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFastAdapter);
        mRecyclerView.setItemAnimator(new AlphaCrossFadeAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mRecyclerView.getItemAnimator().setRemoveDuration(500);

        //restore selections (this has to be done after the items were added
        mFastAdapter.withSavedInstanceState(savedInstanceState);

        /**
         * selection spinner for the different animtors
         */
        Spinner spinner = (Spinner) findViewById(R.id.spinner_nav);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Type type : Type.values()) {
            spinnerAdapter.add(type.name());
        }
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mRecyclerView.setItemAnimator(Type.values()[position].getAnimator());
                mRecyclerView.getItemAnimator().setAddDuration(500);
                mRecyclerView.getItemAnimator().setRemoveDuration(500);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //if we do this. the first added items will be animated :D
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //add some dummy data
                mItemAdapter.add(ImageDummyData.getImages());
            }
        }, 50);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.findItem(R.id.item_add).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_plus_square).color(Color.BLACK).actionBar());
        menu.findItem(R.id.item_delete).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_minus_square).color(Color.BLACK).actionBar());
        menu.findItem(R.id.item_change).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_settings_square).color(Color.BLACK).actionBar());
        menu.findItem(R.id.item_move).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_format_valign_bottom).color(Color.BLACK).actionBar());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //find out the current visible position
        int position = 0;
        if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        } else if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        }

        //handle the menu item click
        switch (item.getItemId()) {
            case R.id.item_add:
                if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    position = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                } else if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    position = ((GridLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                }
                mItemAdapter.add(position + 1, ImageDummyData.getDummyItem());
                return true;
            case R.id.item_change:
                for (Integer pos : (Iterable<Integer>) mFastAdapter.getSelections()) {
                    ImageItem i = (ImageItem) mItemAdapter.getAdapterItem(pos);
                    i.withName("CHANGED");
                    i.withDescription("This item was modified");
                    mItemAdapter.set(pos, i);
                }
                return true;
            case R.id.item_move:
                List items = mItemAdapter.getAdapterItems();
                if (items.size() > position + 3) {
                    IItem i = (IItem) items.get(position + 1);
                    items.remove(position + 1);
                    items.add(position + 3, i);
                    mFastAdapter.notifyAdapterItemMoved(position + 1, position + 3);
                }
                return true;
            case R.id.item_delete:
                mFastAdapter.deleteAllSelectedItems();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = mResult.saveInstanceState(outState);
        //add the values which need to be saved from the adapter to the bundel
        outState = mFastAdapter.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (mResult != null && mResult.isDrawerOpen()) {
            mResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
