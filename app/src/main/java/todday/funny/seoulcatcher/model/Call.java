package todday.funny.seoulcatcher.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Call implements Parcelable {
    private User user;
    private String kind;
    private String age;
    private String name;
    private String address;
    private double latitude;
    private double longitude;


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.kind);
        dest.writeString(this.age);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    public Call() {
    }

    protected Call(Parcel in) {
        this.user = in.readParcelable(User.class.getClassLoader());
        this.kind = in.readString();
        this.age = in.readString();
        this.name = in.readString();
        this.address = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Parcelable.Creator<Call> CREATOR = new Parcelable.Creator<Call>() {
        @Override
        public Call createFromParcel(Parcel source) {
            return new Call(source);
        }

        @Override
        public Call[] newArray(int size) {
            return new Call[size];
        }
    };
}
