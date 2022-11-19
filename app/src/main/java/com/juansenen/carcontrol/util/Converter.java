package com.juansenen.carcontrol.util;

import androidx.room.TypeConverter;

import java.sql.Date;

public class Converter {
    public class Converters {
        @TypeConverter
        public Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public  Long dateToTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }
    }
}
