package com.example.coursework_covid19_statistics.ui.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.coursework_covid19_statistics.R;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> implements Filterable {

    private List<CountryModel> countryModels;
    private List<CountryModel> countryModelsFull;
    private OnItemClickListener onItemClickListener;

    public CountryAdapter(List<CountryModel> countryModels, OnItemClickListener onItemClickListener) {
       this.countryModels = countryModels;
       this.onItemClickListener = onItemClickListener;
       countryModelsFull = new ArrayList<>(countryModels);
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, final int position) {

        final CountryModel countryModel = countryModels.get(position);
        final int adapterPosition = holder.getAdapterPosition();
        holder.tvTotalCases.setText(Integer.toString(countryModel.getmCases()));
        holder.tvTotalCountryAcronyme.setText(countryModel.getmAcronyme());
        holder.tvCoutryName.setText(countryModel.getmCovidCountry());

        Glide.with(holder.mView).load(countryModel.getmFlags()).apply(new RequestOptions().centerCrop()).into(holder.imgCountryFlag);


        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(countryModel,adapterPosition);
            }
        };
        holder.mView.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return countryModels.size();
    }





     protected class ViewHolder  extends RecyclerView.ViewHolder{
        TextView tvTotalCases;
        TextView tvTotalCountryAcronyme;
        TextView tvCoutryName;
        ImageView  imgCountryFlag;
        private View mView;


        protected ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.country_row_item);
            tvTotalCases =  itemView.findViewById(R.id.tvCountryCases);
            tvCoutryName =  itemView.findViewById(R.id.tvCountryName_row);
            tvTotalCountryAcronyme = itemView.findViewById(R.id.tvCountryAcronym);
            imgCountryFlag = itemView.findViewById(R.id.ivFlag);
        }


    }
    @Override
    public Filter getFilter() {
        return countriesFilter;
    }

    private Filter countriesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<CountryModel> filteredCountryModel = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredCountryModel.addAll(countryModelsFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (CountryModel itemCountryModel : countryModelsFull) {
                    if (itemCountryModel.getmCovidCountry().toLowerCase().contains(filterPattern)) {
                        filteredCountryModel.add(itemCountryModel);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredCountryModel;
            return  results;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            countryModels.clear();
            countryModels.addAll((List) filterResults.values);
            notifyDataSetChanged();

        }


    };
    public interface  OnItemClickListener{
        void onItemClick(CountryModel item, int adapterPosition);
    }
}

