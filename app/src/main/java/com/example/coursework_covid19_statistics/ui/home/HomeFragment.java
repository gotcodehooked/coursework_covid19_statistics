package com.example.coursework_covid19_statistics.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.coursework_covid19_statistics.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayoutHome;
    private TextView tvTotalConfirmed;
    private TextView tvTotalDeaths;
    private TextView tvTotalRecovered;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvTotalConfirmed = view.findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths = view.findViewById(R.id.tvTotalDeath);
        tvTotalRecovered = view.findViewById(R.id.tvTotalRecovered);
        swipeRefreshLayoutHome = view.findViewById(R.id.swiperefreshcountry);
        swipe();


        return view;


    }

    private void swipe() {
        swipeRefreshLayoutHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                RequestQueue queue = Volley.newRequestQueue(requireActivity());
                String url = "https://corona.lmao.ninja/v2/all";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        swipeRefreshLayoutHome.setRefreshing(false);
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvTotalConfirmed.setText(jsonObject.getString("cases"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTotalRecovered.setText(jsonObject.getString("recovered"));
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });

                queue.add(stringRequest);


            }


        });
    }

}


