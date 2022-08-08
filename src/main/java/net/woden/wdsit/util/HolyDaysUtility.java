package net.woden.wdsit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.springframework.stereotype.Service;

@Service
public class HolyDaysUtility {
    ArrayList<String> feriados = new ArrayList();
    
    private void getDiasPascua(String pascua) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = format.parse(pascua);
            //Jueves santo
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -3);
            feriados.add(format.format(calendar.getTime()));
            //Viernes santo
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -2);
            feriados.add(format.format(calendar.getTime()));
            //Ascencion de Jesus
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, +43);
            feriados.add(format.format(calendar.getTime()));
            //Corpus Christi
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, +64);
            feriados.add(format.format(calendar.getTime()));
            //Sagrado Corazon de Jesus
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, +71);
            feriados.add(format.format(calendar.getTime()));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String getPascua(String year) {
        String diaPascua = "";
        ArrayList<String> pascua = new ArrayList();
        pascua.add("2020-04-12");
        pascua.add("2021-04-04");
        pascua.add("2022-04-17");
        pascua.add("2023-04-09");
        pascua.add("2024-03-31");
        pascua.add("2025-04-20");
        pascua.add("2026-04-05");
        pascua.add("2027-03-28");
        pascua.add("2028-04-16");
        pascua.add("2029-04-01");
        pascua.add("2030-04-21");
        pascua.add("2031-04-13");
        pascua.add("2032-03-28");
        pascua.add("2033-04-17");
        pascua.add("2034-04-09");
        pascua.add("2035-03-25");
        pascua.add("2036-04-13");
        pascua.add("2037-04-05");
        pascua.add("2038-04-25");
        pascua.add("2039-04-10");
        pascua.add("2040-04-01");
        pascua.add("2041-04-21");
        pascua.add("2042-04-06");
        pascua.add("2043-03-29");
        pascua.add("2044-04-17");
        pascua.add("2045-04-09");
        pascua.add("2046-03-25");
        pascua.add("2047-04-14");
        pascua.add("2048-04-05");
        pascua.add("2049-04-18");
        pascua.add("2050-04-10");
        pascua.add("2051-04-02");
        pascua.add("2052-04-21");
        pascua.add("2053-04-06");
        pascua.add("2054-03-29");
        pascua.add("2055-04-18");
        pascua.add("2056-04-02");
        pascua.add("2057-04-22");
        pascua.add("2058-04-14");
        pascua.add("2059-03-30");
        pascua.add("2060-04-18");
        pascua.add("2061-04-10");
        pascua.add("2062-03-26");
        pascua.add("2063-04-15");
        pascua.add("2064-04-06");
        pascua.add("2065-03-29");
        pascua.add("2066-04-11");
        pascua.add("2067-04-03");
        pascua.add("2068-04-22");
        pascua.add("2069-04-14");
        pascua.add("2070-03-30");
        pascua.add("2071-04-19");
        pascua.add("2072-04-10");
        pascua.add("2073-03-26");
        pascua.add("2074-04-15");
        pascua.add("2075-04-07");
        pascua.add("2076-04-19");
        pascua.add("2077-04-11");
        pascua.add("2078-04-03");
        pascua.add("2079-04-23");
        pascua.add("2080-04-07");
        pascua.add("2081-03-30");
        pascua.add("2082-04-19");
        pascua.add("2083-04-04");
        pascua.add("2084-03-26");
        pascua.add("2085-04-15");
        pascua.add("2086-03-31");
        pascua.add("2087-04-20");
        pascua.add("2088-04-11");
        pascua.add("2089-04-03");
        pascua.add("2090-04-16");
        pascua.add("2091-04-08");
        pascua.add("2092-03-30");
        pascua.add("2093-04-12");
        pascua.add("2094-04-04");
        pascua.add("2095-04-24");
        pascua.add("2096-04-15");
        pascua.add("2097-03-31");
        pascua.add("2098-04-20");
        pascua.add("2099-04-12");
        pascua.add("2100-03-28");

        for (String s : pascua) {
            if (year.equals(s.split("-", 3)[0])) {
                diaPascua = s;
                break;
            }
        }
        return diaPascua;
    }

    private void getFestivosFijos(String year) {
        feriados.add(year + "-01-01");
        feriados.add(year + "-05-01");
        feriados.add(year + "-07-20");
        feriados.add(year + "-08-07");
        feriados.add(year + "-12-08");
        feriados.add(year + "-12-25");
    }

    private void getFestivosTrasladables(String year) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Date> trasladables = new ArrayList();
        try {
            trasladables.add(format.parse(year + "-01-06"));
            trasladables.add(format.parse(year + "-03-19"));
            trasladables.add(format.parse(year + "-06-29"));
            trasladables.add(format.parse(year + "-08-15"));
            trasladables.add(format.parse(year + "-10-12"));
            trasladables.add(format.parse(year + "-11-01"));
            trasladables.add(format.parse(year + "-11-11"));
        } catch (ParseException ex) {
            
        }
        for (Date d : trasladables) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            if (c.get(Calendar.DAY_OF_WEEK) == 2) {
                feriados.add(format.format(c.getTime()));
            } else {
                boolean status = true;
                while (status) {
                    c.add(Calendar.DAY_OF_MONTH, +1);
                    if (c.get(Calendar.DAY_OF_WEEK) == 2) {
                        status = false;
                        feriados.add(format.format(c.getTime()));
                    }
                }
            }
        }
    }

    public int getBusinessDays(String date1, String date2) {
        int businessDays = 0;
        boolean status = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateYear = null;
        Date d1 = null;
        Date d2 = null;
        Calendar c1 = Calendar.getInstance();
        try {
            dateYear = format.parse(date2);
            d1 = format.parse(date1);
            d2 = format.parse(date2);
        } catch (ParseException ex) {

        }
        Calendar year = Calendar.getInstance();
        year.setTime(dateYear);
        getDiasPascua(getPascua(String.valueOf(year.get(Calendar.YEAR))));
        getFestivosFijos(String.valueOf(year.get(Calendar.YEAR)));
        getFestivosTrasladables(String.valueOf(year.get(Calendar.YEAR)));
        c1.setTime(d1);
        while (status) {
            int count = 0;
            if (c1.getTime().getTime() < d2.getTime()) {
                Iterator<String> i=this.feriados.iterator();
                while(i.hasNext()){
                    String s=i.next();
                    if (format.format(c1.getTime()).equals(s) || c1.get(Calendar.DAY_OF_WEEK) == 1 || c1.get(Calendar.DAY_OF_WEEK) == 7) {
                        count++;
                    }
                }
                c1.add(Calendar.DAY_OF_MONTH, +1);
            } else {
                status = false;
            }
            if (count == 0) {
                businessDays++;
            }
        }
        return businessDays;
    }
}
