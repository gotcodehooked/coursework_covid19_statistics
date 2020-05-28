package com.example.coursework_covid19_statistics.ui.country;

import android.os.Parcel;
import android.os.Parcelable;

public class CountryModel implements Parcelable {
    String mCovidCountry;
    String mTodayCases;
    String mDeaths;
    String mTodayDeaths;
    String mRecovered;
    String mActive;
    String mCritical;
    String mFlags;


    String mAcronyme;
    int mCases;

    public CountryModel(String mCovidCountry, int mCases, String mTodayCases, String mDeaths, String mTodayDeaths, String mRecovered, String mActive, String mCritical, String mFlags , String mAcronyme  ) {
        this.mCovidCountry = mCovidCountry;
        this.mCases = mCases;
        this.mTodayCases = mTodayCases;
        this.mDeaths = mDeaths;
        this.mTodayDeaths = mTodayDeaths;
        this.mRecovered = mRecovered;
        this.mActive = mActive;
        this.mCritical = mCritical;
        this.mFlags = mFlags;
        this.mAcronyme = mAcronyme;
    }

    public String getmCovidCountry() {
        return mCovidCountry;
    }

    public int getmCases() {
        return mCases;
    }

    public String getmTodayCases() {
        return mTodayCases;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public String getmTodayDeaths() {
        return mTodayDeaths;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public String getmActive() {
        return mActive;
    }

    public String getmCritical() {
        return mCritical;
    }

    public String getmFlags() {
        return mFlags;
    }

    public String getmAcronyme() {
        return mAcronyme;
    }

    public void setmAcronyme(String mAcronyme) {
        this.mAcronyme = mAcronyme;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCovidCountry);
        dest.writeInt(this.mCases);
        dest.writeString(this.mTodayCases);
        dest.writeString(this.mDeaths);
        dest.writeString(this.mTodayDeaths);
        dest.writeString(this.mRecovered);
        dest.writeString(this.mActive);
        dest.writeString(this.mCritical);
        dest.writeString(this.mFlags);
        dest.writeString(this.mAcronyme);
    }

    protected CountryModel(Parcel in) {
        this.mCovidCountry = in.readString();
        this.mCases = in.readInt();
        this.mTodayCases = in.readString();
        this.mDeaths = in.readString();
        this.mTodayDeaths = in.readString();
        this.mRecovered = in.readString();
        this.mActive = in.readString();
        this.mCritical = in.readString();
        this.mFlags = in.readString();
        this.mAcronyme = in.readString();
    }

//    @Override
//    public String toString() {
//        return "CountryModel" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", newsWhen='" + newsWhen + '\'' +
//                ", shortDescription='" + shortDescription + '\'' +
//                ", description='" + description + '\'' +
//                ", category=" + categories +
//                ", image=" + images +
//                ", orientationMode=" + orientationMode +
//                '}';
//    }


    public static final Creator<CountryModel> CREATOR = new Creator<CountryModel>() {
        @Override
        public CountryModel createFromParcel(Parcel source) {
            return new CountryModel(source);
        }

        @Override
        public CountryModel[] newArray(int size) {
            return new CountryModel[size];
        }
    };
}
