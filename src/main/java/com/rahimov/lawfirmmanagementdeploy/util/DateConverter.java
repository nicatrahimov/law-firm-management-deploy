package com.rahimov.lawfirmmanagementdeploy.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public static LocalDate toLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return LocalDate.parse(dateString,formatter);
    }

    public static String localDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

}
