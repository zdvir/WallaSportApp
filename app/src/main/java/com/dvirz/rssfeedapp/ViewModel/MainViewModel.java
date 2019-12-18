package com.dvirz.rssfeedapp.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.dvirz.rssfeedapp.ExtractXML;
import com.dvirz.rssfeedapp.Model.Post;
import com.dvirz.rssfeedapp.Repository.PostRepository;
import com.dvirz.rssfeedapp.Retofit.Response.Item;
import com.dvirz.rssfeedapp.Retofit.Response.Rss;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel
{
    private static final String TAG = "MainViewModel";
    private MutableLiveData<List<Post>> mMutableLivePosts;

    public MutableLiveData<List<Post>> getmMutableLivePosts()
    {
        if(mMutableLivePosts == null)
            mMutableLivePosts = new MutableLiveData<>();

        return mMutableLivePosts;
    }

    public void getPosts(String feed_name)
    {
        PostRepository.getINSTANCE().getItems(feed_name).enqueue(new Callback<Rss>()
        {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response)
            {
                List<Item> items = response.body().getChannel().getItems();
                ArrayList<Post> posts = extractPostsFromItems(items);
                mMutableLivePosts.setValue(posts);
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private ArrayList<Post> extractPostsFromItems(List<Item> items)
    {
        ArrayList<Post> postList = new ArrayList<>();

        Log.d(TAG, "extractPostsFromItems: "+ items.size());

        for(Item item : items)
        {
            ExtractXML extractXML1 = new ExtractXML(item.getDescription(), "<a href=");
            List<String> postDescription = extractXML1.start();

            try{
                ExtractXML extractXML2 = new ExtractXML(item.getDescription(), "<img hspace=5 border=0 align=\"right\" src=");
                postDescription.add(extractXML2.start().get(0));
            }
            catch (Exception e)
            {
                postDescription.add(null);
            }

            postList.add(new Post(item.getTitle(),item.getPubDate(),postDescription.get(0), postDescription.get(1)));
        }
        return postList;
    }
}
