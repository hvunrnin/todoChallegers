package todolist.todolistspring.service;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalenderServiceTest {

    private CalenderService calenderService;
    @Test
    void checkLeapYear() {
        System.out.println(calenderService.checkLeapYear(2023));
    }

    @Test
    void getFirstDayOfWeek() {
        LocalDate date = LocalDate.of(2023, 1, 1);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek.getValue());
    }

    @Test
    void changeYearMonth(){
        int[] month_day = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        System.out.println(month_day[1]);

        ArrayList<String> arr_calender = new ArrayList<String>();
        //달력에 1일부터 채우기
        for(int i=1; i<3; i++){ //월요일==1
            arr_calender.add("");
        }
        for(int i=1; i<=month_day[1]; i++){
            arr_calender.add(String.valueOf(i));
        }

        int remain_day = 7 - (arr_calender.size()%7);

        if(remain_day<7) {
            for(int i=0; i<remain_day; i++){
                arr_calender.add("");
            }
        }

//        System.out.println(arr_calender);
    }


}