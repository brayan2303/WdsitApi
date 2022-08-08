package net.woden.wdsit.util;

import java.util.GregorianCalendar;
import org.springframework.stereotype.Service;

@Service
public class DayMonthUtility {
    public int getDays(int year,int month){
        int days=0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days=30;
                break;
            case 2:
                if(this.isBisiesto(year)){
                    days=29;
                }else{
                    days=28;
                }   break;
            default:
                break;
        }
        return days;
    }
    private boolean isBisiesto(int year){
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.isLeapYear(year);
    }
}
