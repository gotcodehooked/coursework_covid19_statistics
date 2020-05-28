package com.example.coursework_covid19_statistics.ui.country;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class BaseFragment extends Fragment{



    protected RecyclerView recyclerView;
    protected LinearLayoutManager mLayoutManager;




    // protected SwipeRefreshLayout swipeRefreshLayout;

    protected void setRecyclerView(RecyclerView.Adapter<?> adapter){
        mLayoutManager = (LinearLayoutManager)  new LinearLayoutManager(requireActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }




    public void onBackPressed() {

    }

    public void updateRecycleView() throws ExecutionException, InterruptedException {};


}