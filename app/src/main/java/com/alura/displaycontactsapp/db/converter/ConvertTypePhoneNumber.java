package com.alura.displaycontactsapp.db.converter;

import androidx.room.TypeConverter;

import com.alura.displaycontactsapp.model.TypePhoneNumber;

public class ConvertTypePhoneNumber {

    @TypeConverter
    public String convertToString(TypePhoneNumber typePhoneNumber){
        return typePhoneNumber.name();
    }

    @TypeConverter
    public TypePhoneNumber toTypePhoneNumber(String value){
        if(value != null){
            return TypePhoneNumber.valueOf(value);
        }
        return TypePhoneNumber.HOME;
    }
}
