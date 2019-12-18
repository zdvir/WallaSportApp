package com.dvirz.rssfeedapp.Retofit;

import com.dvirz.rssfeedapp.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitRequest
{
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
