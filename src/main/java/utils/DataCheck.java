package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCheck {

    // 手机号码格式校验
    public static Boolean phoneCheck(String checkStr){
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
        return doPattrenCheck(regex, checkStr);
    }

    // 短信验证码格式校验
    public static Boolean smsCodeCheck(String checkStr){
        String regex = "^\\d{6}$";
        return doPattrenCheck(regex, checkStr);
    }

    // 密码合法性校验
    public static Boolean passwordCheck(String checkStr){
        // 6~18位，必须包含字母和数字
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        return doPattrenCheck(regex, checkStr);
    }

    // 昵称合法性校验
    public static Boolean neaknameCheck(String checkStr){
        // 昵称格式：长度小于16,不能包含特殊符号
        String regex = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";
        return doPattrenCheck(regex, checkStr) && checkStr.length() <= 16;
    }

    private static Boolean doPattrenCheck(String regex, String checkStr){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(checkStr);
        return m.matches();
    }


    // 校验输入是否为数字
    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
