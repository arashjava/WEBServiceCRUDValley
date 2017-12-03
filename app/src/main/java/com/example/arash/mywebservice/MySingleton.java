package com.example.arash.mywebservice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by arash on 18/11/17.
 */

public class MySingleton {
    //  first
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCntx;

    //  Third
    private MySingleton(Context context)
    {
        mCntx= context;
        requestQueue= getRequestQueue();
    }

    //  Forth
    public static synchronized MySingleton getInstance(Context context){

        if (mInstance == null){
            mInstance= new MySingleton(context);
        }
        return mInstance;
    }

    //  Second
    public RequestQueue getRequestQueue()
    {
        if (requestQueue== null){
            requestQueue= Volley.newRequestQueue(mCntx.getApplicationContext());
        }
        return requestQueue;
    }

    //Fifth
    public<T> void addToRequestQue(Request<T> request)
    {
        requestQueue.add(request);
    }

}
