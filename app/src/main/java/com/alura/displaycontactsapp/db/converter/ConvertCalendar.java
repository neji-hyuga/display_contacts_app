package com.alura.displaycontactsapp.db.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConvertCalendar {

    @TypeConverter
    public Long toLong(Calendar calendar) {
        if (calendar != null) {
            return calendar.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long timeInLong) {
        Calendar instance = Calendar.getInstance();
        if (timeInLong != null) {
            instance.setTimeInMillis(timeInLong);
        }
        return instance;
    }
}
