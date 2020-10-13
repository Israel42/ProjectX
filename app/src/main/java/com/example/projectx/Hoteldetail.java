package com.example.projectx;

public class Hoteldetail  {
    String hotelName;
    String hotelImageurl;
    String hotelrooms;
    String roomimageurl;

    public Hoteldetail(String hotelName, String hotelImageurl, String hotelrooms, String roomimageurl) {
        this.hotelName = hotelName;
        this.hotelImageurl = hotelImageurl;
        this.hotelrooms = hotelrooms;
        this.roomimageurl = roomimageurl;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelImageurl() {
        return hotelImageurl;
    }

    public void setHotelImageurl(String hotelImageurl) {
        this.hotelImageurl = hotelImageurl;
    }

    public String getHotelrooms() {
        return hotelrooms;
    }

    public void setHotelrooms(String hotelrooms) {
        this.hotelrooms = hotelrooms;
    }

    public String getRoomimageurl() {
        return roomimageurl;
    }

    public void setRoomimageurl(String roomimageurl) {
        this.roomimageurl = roomimageurl;
    }
}
