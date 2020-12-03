package com.tml.poc.Wallet.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

@Service
public class CommonMethods {


    //Save the uploaded file to this folder
    public static String UPLOADED_FOLDER = "uploadFile/";

    public String generateOTP()
    {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
    public String generateOTP4dig()
    {
        return new DecimalFormat("0000").format(new Random().nextInt(9999));
    }

    public static Date getStringToDate(String dateStr, String formatStr)
    {
        Date   date       = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            String dateString = format.format( new Date()   );
            date = format.parse ( dateStr );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    public static String dateToString(Date date, String expected_format) {
        SimpleDateFormat actual = new SimpleDateFormat(expected_format, Locale.ENGLISH);


        return actual.format(date);

    }

    public String convertTimeFormat(String currentTimeFormat, String expectedTimeFormat, String time) {
        if (time == null) {
            return "";
        }
        String newTime = null;
        SimpleDateFormat actual = new SimpleDateFormat(currentTimeFormat, Locale.ENGLISH);
        SimpleDateFormat target = new SimpleDateFormat(expectedTimeFormat, Locale.ENGLISH);
        Date date;
        try {
            date = actual.parse(time);
            newTime = target.format(date);
            return newTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertTimeFormat1(String currentTimeFormat, String expectedTimeFormat, String time) {
        if (time == null) {
            return "";
        }
        String newTime = null;
        SimpleDateFormat actual = new SimpleDateFormat(currentTimeFormat, Locale.ENGLISH);
        SimpleDateFormat target = new SimpleDateFormat(expectedTimeFormat, Locale.ENGLISH);
        Date date;
        try {
            date = actual.parse(time);
            newTime = target.format(date);
            return newTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * This function used to get UNIX time stamp
     *
     * @return
     */
    public String getTimeStamp() {
        return ((Long) (System.currentTimeMillis() / 1000)).toString();
    }

    /**
     * This function is used to convert unix time stamp to expected date format
     *
     * @param timestamp      - unix timestamp to convert
     * @param expectedFormat - date format which we expects
     * @return -
     */
    public String convertTimestampToDate(String timestamp, String expectedFormat) {
        Date date = new Date(Long.parseLong(timestamp) * 1000L); // *1000 is to convert seconds to milliseconds
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
        SimpleDateFormat sdf = new SimpleDateFormat(expectedFormat, Locale.ENGLISH);
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    /**
     * This function is used to convert unix time stamp to expected date format
     *
     * @param expectedFormat - date format which we expects
     * @return -
     */
    public static String getDate(String expectedFormat) {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp); // *1000 is to convert seconds to milliseconds
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
        SimpleDateFormat sdf = new SimpleDateFormat(expectedFormat, Locale.ENGLISH);
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        // Log.e(TAG, timestamp + " UNIX timestamp converted = " + formattedDate);
        return formattedDate;
    }

    /**
     * This function is used to convert unix time stamp to expected date format
     *
     * @param expectedFormat - date format which we expects
     * @return -
     */
    public static String getDateBeforeDays(String expectedFormat, int days) {
        long timestamp = System.currentTimeMillis();
        Date date = yesterday(days); // *1000 is to convert seconds to milliseconds
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
        SimpleDateFormat sdf = new SimpleDateFormat(expectedFormat, Locale.ENGLISH);
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        // Log.e(TAG, timestamp + " UNIX timestamp converted = " + formattedDate);
        return formattedDate;
    }

    public static Date yesterday(int days) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }


    public String getBindViewError(BindingResult bindingResult) {
        String messageStr = "";
        if (bindingResult.getErrorCount() > 0) {

            for (Object object : bindingResult.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    messageStr = messageStr + ", " + fieldError.getField();

                }

                if (object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;
                    messageStr = messageStr + " " + objectError.getDefaultMessage();
                }
            }


        }
        return messageStr;

    }

    public static String sendOtp(String mobileNo,String Otp)
    {
         String uri = "http://bhashsms.com/api/sendmsg.php?user=Kashit&pass=kash12345&sender=KASHIT&phone="+mobileNo;
        uri = uri+"&text=OTP:"+Otp+"&priority=ndnd&stype=normal";

        System.out.println("url= "+uri);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);

        return result;
    }
}
