package com.timen4.ronnny.JueJinModel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.timen4.ronnny.JueJinModel.bean.NewsItem;
import com.timen4.ronnny.JueJinModel.fragment.ExploreFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ListView mlistView;
    private LinearLayout mBottom_bar;
    private LinearLayout mHead_bar;
    private ViewHolderAdapter mAdapter;
    private SwipeRefreshLayout mSr_refresh;
    private Button mEmptyFresh;
    private boolean isRunning = false;
    boolean isLoading=false;

    private static  int mTouchSlop = 0;
    private ObjectAnimator mHeaderAnimator;
    private ObjectAnimator mBottomAnimator;
    private ImageButton mIb_explore;
    private ArrayList<Integer> imgs=new ArrayList<>();
    private List<NewsItem> mDates;
    private View header;
    private View footview;
    private TextView mLoadMore;
    private FrameLayout mContent_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().add(R.id.main_content, new ExploreFragment(mBottom_bar)).commit();
        }

//        initData();
//        bindEvents();
    }


    private void initView() {

//        mSr_refresh = (SwipeRefreshLayout) findViewById(R.id.sr_refresh);
//        mlistView = (ListView) findViewById(R.id.listView);
        mBottom_bar = (LinearLayout) findViewById(R.id.bottom_bar);
//        mHead_bar = (LinearLayout) findViewById(R.id.head_bar);
//        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
//
//        mEmptyFresh = (Button) findViewById(R.id.empty_fresh);
        mIb_explore = (ImageButton) findViewById(R.id.ib_explore);
        mIb_explore.setImageResource(R.drawable.tab_explore);
//
//        header = LayoutInflater.from(this).inflate(R.layout.lv_headerview, mlistView, false);
////      header = getLayoutInflater().inflate(R.layout.lv_headerview,null,true);
////      header=View.inflate(this,R.layout.lv_headerview,null);
//        mlistView.addHeaderView(header);
//
//        footview = LayoutInflater.from(this).inflate(R.layout.lv_footview, mlistView, false);
//        mLoadMore = (TextView) footview.findViewById(R.id.tv_loadmore);
//        mlistView.addFooterView(footview);
//        mAdapter = new ViewHolderAdapter(this, mDates);
//        mlistView.setAdapter(mAdapter);
//
//        //设置下拉刷新加载圈的颜色
//        mSr_refresh.setColorSchemeColors(getResources().getColor(R.color.refresh_circle));
//        //设置下拉加载圈出现距离顶部的位置
//        mSr_refresh.setDistanceToTriggerSync(getResources().getDimensionPixelOffset(R.dimen.swipe_progress_appear_offset));
//        //设置下拉加载圈转动时距离顶部的位置
//        mSr_refresh.setProgressViewEndTarget(true, getResources().getDimensionPixelOffset(R.dimen.swipe_progress_to_top));

    }

    private void initData() {
        imgs.add(R.drawable.img1);
        imgs.add(R.drawable.img2);
        imgs.add(R.drawable.img3);
        imgs.add(R.drawable.img4);
        imgs.add(R.drawable.img5);
        imgs.add(R.drawable.img6);
        imgs.add(R.drawable.img7);
        imgs.add(R.drawable.img8);
        imgs.add(R.drawable.img9);
        imgs.add(R.drawable.img10);
//        mDates = refreshDate("Init");
    }

//    private void bindEvents() {
//        mSr_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mSr_refresh.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        List<NewsItem> newsItems=refreshDate("New");
//                        mAdapter.addDate(newsItems, true);
//                        mAdapter.notifyDataSetChanged();
//                        //调用该方法结束刷新，否则加载圈会一直在
//                        mSr_refresh.setRefreshing(false);
//                    }
//                },1000);
//            }
//        });
//        mlistView.setOnTouchListener(new View.OnTouchListener() {
//            private float mEndY;
//            private float mStartY;
//            private int direction;//0表示向上，1表示向下
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        mStartY = event.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        mEndY = event.getY();
//                        float v1 = mEndY - mStartY;
//
//                        if (v1 > 3 && !isRunning&& direction == 1) {
//                            direction = 0;
//                            showBar();
//                            mStartY = mEndY;
//                            return false;
//                        } else if (v1 < -3 && !isRunning && direction == 0) {
//                            direction = 1;
//                            hideBar();
//                            mStartY = mEndY;
//                            return false;
//                        }
//                        mStartY = mEndY;
//
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//                return false;
//            }
//        });
//        mlistView.setOnScrollListener(new AbsListView.OnScrollListener(){
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//            }
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                //使用isLoading作为标识符避免刷新到底部时，每次触底进行多次加载
//                if(view.getLastVisiblePosition()==view.getCount()-1&&!isLoading){
//                    mLoadMore.setVisibility(View.VISIBLE);
//                    isLoading=true;
//                    new AsyncTask<Void, Void, List<NewsItem>>() {
//                        @Override
//                        protected List<NewsItem> doInBackground(Void... params) {
//                            SystemClock.sleep(1500);
//                            List<NewsItem> loadItems=refreshDate("Old");
//                            return loadItems;
//                        }
//                        @Override
//                        protected void onPostExecute(List<NewsItem> loadItems) {
//                            isLoading=false;
//                            mLoadMore.setVisibility(View.GONE);
//                            mAdapter.addDate(loadItems,false);
//                            mAdapter.notifyDataSetChanged();
//                        }
//                    }.execute();
//                }
//
//            }
//        });
//    }



}
