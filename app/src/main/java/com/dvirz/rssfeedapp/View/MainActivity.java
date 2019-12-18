package com.dvirz.rssfeedapp.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dvirz.rssfeedapp.Adapters.PostAdapter;
import com.dvirz.rssfeedapp.ExtractXML;
import com.dvirz.rssfeedapp.Retofit.Response.Item;
import com.dvirz.rssfeedapp.Retofit.Response.Rss;
import com.dvirz.rssfeedapp.Model.Post;
import com.dvirz.rssfeedapp.R;
import com.dvirz.rssfeedapp.Retofit.RssAPI;
import com.dvirz.rssfeedapp.Constants;
import com.dvirz.rssfeedapp.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements PostAdapter.OnItemClickListener
{
    private static final String TAG = "MainActivity";

    private Button button1, button2, button3, button4,button5;
    private RecyclerView recyclerView;
    private PostAdapter adapter;

    private MainViewModel mMainViewModel;
    private String currentFeed = "3825";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initButtons();

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mMainViewModel.getPosts(currentFeed);

        initRecyclerView();

        mMainViewModel.getmMutableLivePosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts)
            {
                adapter.setList(posts);
            }
        });
    }

    private void init()
    {
         recyclerView = findViewById(R.id.recycler_view);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
         button4 = findViewById(R.id.button4);
         button5 = findViewById(R.id.button5);
    }

    private void initButtons()
    {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                setFeedPage("3825");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                setFeedPage("156");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                setFeedPage("316");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                setFeedPage("151");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                setFeedPage("152");
            }
        });
    }

    private void initRecyclerView()
    {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new PostAdapter(MainActivity.this);
        adapter.setOnItemClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.requestFocus();
    }

    public void setFeedPage(String feed_name)
    {
        if(!feed_name.isEmpty())
            currentFeed = feed_name;

        mMainViewModel.getPosts(currentFeed);
    }

    @Override
    public void onItemClick(int position)
    {
        Post post = adapter.getList().get(position);
        String url = post.getLink();
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
