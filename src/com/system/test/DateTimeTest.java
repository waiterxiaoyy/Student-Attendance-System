package com.system.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeTest {
    public static void main(String[] args) throws ParseException {
        String bigsmall = "yes";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date()));
        String starttime = "2010-02-01 10:02:33";
        String endtime = "2010-02-01 10:03:33";
        String midtime = "2010-02-01 10:04:45";
        Date sdate = dateFormat.parse(starttime);
        Date edate = dateFormat.parse(endtime);
        Date mdate = new Date();;
        if(mdate.before(edate) && mdate.after(sdate)) {
            bigsmall = "yes";
        }
        else {
            bigsmall = "no";
        }
        System.out.println(bigsmall);
        Date curTime = new Date();

        System.out.println(dateFormat.format(curTime));
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);
    }

//    public boolean CompareTime(Date firstTime, Date lastTime) {
//        if(lastTime < firstTime) {
//            return true;
//        }
//    }
}