package com.example.coursework_covid19_statistics.ui.country;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.coursework_covid19_statistics.R;

import java.util.Objects;

public class CountryDetail extends Fragment {
    private static String ARG_ITEM;
    private static String ARG_INDEX;
    private int position;
    private CountryModel item;

    public CountryDetail(){

    }

    TextView tvDetailCountryName, tvDetailTotalCases, tvDetailTodayCases, tvDetailTotalDeaths,
            tvDetailTodayDeaths, tvDetailTotalRecovered, tvDetailTotalActive, tvDetailTotalCritical;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = (CountryModel) requireArguments().getParcelable("item");
        position = requireArguments().getInt("Index");
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_country_activity, container, false);
        tvDetailCountryName = view.findViewById(R.id.tvDetailCountryName);
        tvDetailTotalCases = view.findViewById(R.id.tvDetailTotalCases);
        tvDetailTodayCases = view.findViewById(R.id.tvDetailTodayCases);
        tvDetailTotalDeaths = view.findViewById(R.id.tvDetailTotalDeaths);
        tvDetailTodayDeaths = view.findViewById(R.id.tvDetailTodayDeaths);
        tvDetailTotalRecovered = view.findViewById(R.id.tvDetailTotalRecovered);
        tvDetailTotalActive = view.findViewById(R.id.tvDetailTotalActive);
        tvDetailTotalCritical = view.findViewById(R.id.tvDetailTotalCritical);


        // set text view
        tvDetailCountryName.setText(item.getmCovidCountry());
        tvDetailTotalCases.setText(Integer.toString(item.getmCases()));
        tvDetailTodayCases.setText(item.getmTodayCases());
        tvDetailTotalDeaths.setText(item.getmDeaths());
        tvDetailTodayDeaths.setText(item.getmTodayDeaths());
        tvDetailTotalRecovered.setText(item.getmRecovered());
        tvDetailTotalActive.setText(item.getmActive());
       tvDetailTotalCritical.setText(item.getmCritical());



        return view;




    }

   public static CountryDetail newInstance(CountryModel item, int position){
        Bundle args = new Bundle();

        ARG_ITEM = "item";
        ARG_INDEX = "index";

        args.putParcelable(ARG_ITEM,item);
        args.putInt(ARG_INDEX,position);
        CountryDetail fragment = new CountryDetail();
        fragment.setArguments(args);
        return fragment;
   }


}
