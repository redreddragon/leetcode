package com.nov.leetcode.competition;

/**
 * @author longwenhe
 * @date 2020/7/11 23:33
 * @description
 */
public class ReformatDate5177 {
    public String reformatDate(String date) {
        String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] output = date.split(" ");
        String day = output[0];
        String dayTmp = day.charAt(1) >= '0' && day.charAt(1) <= '9' ? day.substring(0, 2) : day.substring(0, 1);
        dayTmp = "0" + dayTmp;
        int dayLen = dayTmp.length();
        String dayNum = dayTmp.substring(dayLen - 2, dayLen);
        String monthNum = "";
        for (int i = 0; i < month.length; i++) {
            String str = month[i];
            if (str.equals(output[1])) {
                String tmp = "0" + Integer.valueOf(i + 1).toString();
                int len = tmp.length();
                monthNum = tmp.substring(len - 2, len);
                break;
            }
        }
        String result = output[2] + "-" + monthNum + "-" + dayNum;
        return result;
    }
}
