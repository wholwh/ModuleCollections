package com.byronginvest.modulecollections.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.byronginvest.modulecollections.R;
import com.byronginvest.modulecollections.data.adapter.CustomSimpleAdapter;
import com.byronginvest.modulecollections.data.adapter.ListAdapter;
import com.byronginvest.modulecollections.data.event.Event;
import com.byronginvest.modulecollections.ui.widget.MyListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
//        @Bind(R.id.recycler_view_list)
//    RecyclerView recyclerViewList;
    @Bind(R.id.listview)
    MyListView listview;
    private Context ctx = this;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private CustomSimpleAdapter simpleAdapter;
    private ListAdapter listAdapter;
    private ArrayList<HashMap<String, String>> mData;
    private String[] imageUrl = {"http://imgsrc.baidu.com/forum/pic/item/ec43564e9258d109ad29cde7d758ccbf6d814db7.jpg"
            , "http://imgsrc.baidu.com/forum/pic/item/67599258d109b3de8375803dcabf6c81810a4cb7.jpg", "http://imgsrc.baidu.com/forum/pic/item/47d1a7efce1b9d16fea7fa34f5deb48f8c54641d.jpg"
            , "http://imgsrc.baidu.com/forum/pic/item/bc0701e93901213f4a382e8952e736d12f2e9577.jpg", "http://imgsrc.baidu.com/forum/pic/item/0159251f95cad1c889c49c72793e6709c93d517a.jpg"
            , "http://imgsrc.baidu.com/forum/pic/item/4dce8d1001e93901d09ded6b7dec54e737d196db.jpg", "http://imgsrc.baidu.com/forum/pic/item/ec43564e9258d109a310c3e7d758ccbf6d814d8e.jpg"
            , "http://imgsrc.baidu.com/forum/pic/item/e054ad4bd11373f0dbcc05caa20f4bfbfaed048e.jpg"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ctx, "Snackbar Click Action.", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

//        ImageLoader.getInstance().displayImage("http://imgsrc.baidu.com/forum/pic/item/ec43564e9258d109ad29cde7d758ccbf6d814db7.jpg",imgImage);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        mData = new ArrayList<HashMap<String, String>>();
        for (String url : imageUrl) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", url.substring(7, 13));
            map.put("content", url.substring(7, 13));
            map.put("image", url);
            mData.add(map);
        }
        simpleAdapter = new CustomSimpleAdapter(ctx, mData, R.layout.listitem_listview, new String[]{"title", "content", "image"}
                , new int[]{R.id.title, R.id.content, R.id.image});

//        recyclerViewList.setNestedScrollingEnabled(true);
//        recyclerViewList.setHasFixedSize(true);
//        listAdapter = new ListAdapter(ctx, mData);
//        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, true);
//        recyclerViewList.setLayoutManager(layoutManager);
//        recyclerViewList.setAdapter(listAdapter);
        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(HomeActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                                                EventBus.getDefault().post(new Event.ItemOnClickEvent("EventBus", "测试消息发布订阅"));
                                            }
                                        }

        );
    }
//        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//            }
//        });
//}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Snackbar.make(fab, "Menu Item Action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ctx, "Snackbar Click Action.", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
            case R.id.action_animation:
                Intent intent = new Intent(HomeActivity.this,
                        AnimationActivity.class);
                startActivityForResult(intent, 0);
                break;
            default:
                break;
//            case R.id.action_buletooth:
//                Intent intent = new Intent(HomeActivity.this, DeviceList.class);
//                startActivityForResult(intent, 0);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onEventMainThread(Event.ItemOnClickEvent event) {
        Toast.makeText(HomeActivity.this, "key: " + event.getKey() + "value: " + event.getValue(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {

        }
    }
}
