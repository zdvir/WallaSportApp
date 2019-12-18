package com.dvirz.rssfeedapp.Repository;

import com.dvirz.rssfeedapp.Retofit.Response.Rss;
import com.dvirz.rssfeedapp.Retofit.RetrofitRequest;
import com.dvirz.rssfeedapp.Retofit.RssAPI;
import retrofit2.Call;

public class PostRepository
{
    private static final String TAG = "PostRepository";
    private static PostRepository INSTANCE;
    private RssAPI rssAPI;

    public PostRepository()
    {
        rssAPI = RetrofitRequest.getRetrofitInstance().create(RssAPI.class);
    }

    public static PostRepository getINSTANCE()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new PostRepository();
        }
        return INSTANCE;
    }

    public Call<Rss> getItems(String feed_name)
    {
        return rssAPI.getItems(feed_name);
    }

}
