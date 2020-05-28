package com.example.coursework_covid19_statistics.ui.country;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coursework_covid19_statistics.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CountryFragment extends  BaseFragment {
    private CountryDetail countryDetail;

    private CountryAdapter countryAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<CountryModel> countryModelList;

    public static CountryFragment newInstance(){
        Bundle args = new Bundle();
        CountryFragment fragment = new CountryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_country, container, false);

        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.recyclerview_country);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh_country);
        countryModelList = new ArrayList<>();

        getDataFromServer();
        showRecyclerView();
        return view;
    }


    private void showRecyclerView() {
        countryAdapter = new CountryAdapter(countryModelList, new CountryAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(CountryModel item, int position) {

                countryDetail = CountryDetail.newInstance(item, position);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.countryFragment, countryDetail)
                        .addToBackStack("back").commit();

                recyclerView.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.GONE);

            }
        });
        setRecyclerView(countryAdapter);


    }



    @Override
    public void onBackPressed() {
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        countryDetail.getView().setFocusableInTouchMode(true);
        countryDetail.getView().requestFocus();
        countryDetail.getView().setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return i == KeyEvent.KEYCODE_BACK;
            }
        });
    }




    private void getDataFromServer() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                countryModelList.clear();
                String url = "https://corona.lmao.ninja/v2/countries";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(i);


                                    JSONObject countryInfo = data.getJSONObject("countryInfo");

                                    countryModelList.add(new CountryModel(
                                            data.getString("country"), data.getInt("cases"),
                                            data.getString("todayCases"), data.getString("deaths"),
                                            data.getString("todayDeaths"), data.getString("recovered"),
                                            data.getString("active"), data.getString("critical"),
                                            countryInfo.getString("flag"),
                                            countryInfo.getString("iso3")


                                    ));
                                }



                                getActivity().setTitle(jsonArray.length() + " countries");
                                showRecyclerView();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                Volley.newRequestQueue(requireActivity()).add(stringRequest);


            }
        });

    }


    private void getDataFromServerSortAlphabet() {
        String url = "https://corona.lmao.ninja/v2/countries";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);

                            // Extract JSONObject inside JSONObject
                            JSONObject countryInfo = data.getJSONObject("countryInfo");

                            countryModelList.add(new CountryModel(
                                    data.getString("country"), data.getInt("cases"),
                                    data.getString("todayCases"), data.getString("deaths"),
                                    data.getString("todayDeaths"), data.getString("recovered"),
                                    data.getString("active"), data.getString("critical"),
                                    countryInfo.getString("flag"),
                                    countryInfo.getString("iso3")
                            ));
                        }

                        // Action Bar Title
                        getActivity().setTitle(jsonArray.length() + " countries");

                        //showRecyclerView();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(requireActivity()).add(stringRequest);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.country_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(requireActivity());
        searchView.setQueryHint("Search...");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (countryAdapter != null) {
                    countryAdapter.getFilter().filter(newText);
                }else {
                    System.out.println("error");
                }
                return true;
            }
        });

        searchItem.setActionView(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_alpha:
                Toast.makeText(getContext(), "Sort Alphabetically", Toast.LENGTH_SHORT).show();
                countryModelList.clear();
                getDataFromServerSortAlphabet();
                //showRecyclerView();

                return true;

            case R.id.action_sort_cases:
                Toast.makeText(getContext(), "Sort by Total Cases", Toast.LENGTH_SHORT).show();
                sortCollection(countryModelList);
                showRecyclerView();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void sortCollection(List countryModelList){

        Collections.sort(countryModelList, new Comparator<CountryModel>() {
            @Override
            public int compare(CountryModel o1, CountryModel o2) {
                if (o1.getmCases() > o2.getmCases()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
    }

}
