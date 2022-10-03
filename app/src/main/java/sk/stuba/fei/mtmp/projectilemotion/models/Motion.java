package sk.stuba.fei.mtmp.projectilemotion.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Motion implements Parcelable {
    @JsonProperty("x")
    private double x;
    @JsonProperty("y")
    private double y;
    @JsonProperty("time")
    private double time;

    public Motion(double x, double y, double time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    public Motion() {
    }

    protected Motion(Parcel in) {
        x = in.readDouble();
        y = in.readDouble();
        time = in.readDouble();
    }

    public static final Creator<Motion> CREATOR = new Creator<Motion>() {
        @Override
        public Motion createFromParcel(Parcel in) {
            return new Motion(in);
        }

        @Override
        public Motion[] newArray(int size) {
            return new Motion[size];
        }
    };

    public double getX() {
        return x;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(x);
        parcel.writeDouble(y);
        parcel.writeDouble(time);
    }
}
