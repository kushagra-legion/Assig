package com.example.myfrag;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfrag.Adapter.RecyclerViewAdapter;
import com.example.myfrag.Adapter.example_item;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class profile extends Fragment {
    View root;
    TextView editText;
    TextView editText1;
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    private RecyclerView mrecyclerview;
    private RecyclerViewAdapter mrecycleadapter;
    private RecyclerView.LayoutManager layoutManager;


    private String url= "https://api.github.com/users/akashsarkar188";
    private String url1= "https://api.github.com/users/akashsarkar188/followers";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_profile, container, false);


        sendAndRequestResponse();
        sendAndRequestResponse1();


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sendAndRequestResponse();
    }

    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getActivity());



        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>()  {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            String name=response.getString("name");
                            String url=response.getString("avatar_url");
                            String githublink=response.getString("blog");
                            String loc=response.getString("location");
                            String bio=response.getString("bio");
                            String follow=response.getString("followers");
                            String following=response.getString("following");

                            editText=root.findViewById(R.id.editText);
                            editText1=root.findViewById(R.id.editText2);
                            textView=root.findViewById(R.id.textView);
                            textView1=root.findViewById(R.id.textView3);
                            textView2=root.findViewById(R.id.textView6);
                            textView3=root.findViewById(R.id.textView7);
                            ImageView imageView = root.findViewById(R.id.imageView2);
                            editText.setText(name);
                            textView.setText(loc);
                            textView1.setText(bio);
                            textView2.setText("Followers : "+follow);
                            textView3.setText("Following : "+following);
                            editText1.setText(githublink);
                            Picasso.get().load(url).into(imageView);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                });
        mRequestQueue.add(jsonObjectRequest);

    }

    private void sendAndRequestResponse1() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getActivity());

//RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getActivity());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                            ArrayList<example_item> exampleList = new ArrayList<>();
                        /*    ImageView imageView = root.findViewById(R.id.imageView2);

                            Picasso.get().load(picx).into(imageView);
*/                          String str=response;
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = null;
                    try {
                        obj = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        String loginname = obj.getString("login");

                        String loginpic = obj.getString("avatar_url");
                        exampleList.add(new example_item(loginname,loginpic));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                              //  String loginname = employee.getString("login");
                              //  String loginpic = employee.getString("avatar_url");
                               // exampleList.add(new example_item(loginname,loginpic));


                            }

                            mrecyclerview = root.findViewById(R.id.recycler_view1);
                            mrecyclerview.setHasFixedSize(true);
                            layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            mrecycleadapter = new RecyclerViewAdapter(exampleList);
                            mrecyclerview.setLayoutManager(layoutManager);
                            mrecyclerview.setAdapter(mrecycleadapter);




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        mRequestQueue.add(mStringRequest);


    }


}
