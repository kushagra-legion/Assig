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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;


public class profile extends Fragment {
    View root;
    TextView editText;
    TextView editText1;
    TextView textView;
    TextView textView1;

    private String url= "https://api.github.com/users/akashsarkar188";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_profile, container, false);


        sendAndRequestResponse();


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


                            editText=root.findViewById(R.id.editText);
                            editText1=root.findViewById(R.id.editText2);
                            textView=root.findViewById(R.id.textView);
                            textView1=root.findViewById(R.id.textView3);
                            ImageView imageView = root.findViewById(R.id.imageView2);
                            editText.setText(name);
                            textView.setText(loc);
                            textView1.setText(bio);
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



}
