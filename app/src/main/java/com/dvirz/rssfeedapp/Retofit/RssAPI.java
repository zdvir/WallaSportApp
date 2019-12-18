package com.dvirz.rssfeedapp.Retofit;

import com.dvirz.rssfeedapp.Retofit.Response.Rss;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RssAPI
{
    //static
    @GET("156")
    Call<Rss> getItems();

    //Non-static
    @GET("{feed_name}")
    Call<Rss> getItems(@Path("feed_name") String feed_name);
}
