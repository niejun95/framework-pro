package org.example.constants;

/**
 * @description 登录功能常量
 * @author ryan
 * @date 2023/4/25 9:35
 * @version 1.0
 */
public class AuthConstants {
    // 短信验证码发送时间间隔限制
    public static final String SMS_CODE_INTERVAL = "sms:code:interval:sms_code_interval_";


    // 短信验证码有效期（分钟）
    public static final int SMS_CODE_EXPIRE_TIME = 1;

    // 短信验证码 验证次数缓存key前缀
    public static final String SMS_CODE_CHECK_TIME_PREFIX = "sms:code:check:time:sms_code_check_time_";

    // 短信验证码最大验证次数
    public static final int SMS_CODE_CHECK_TIME = 3;

    // 短信验证码缓存key前缀
    public static final String SMS_CODE_VALUE_PREFIX = "sms:code:sms_verify_code_";

    // 记住登录有效时间
    public static final int REMEMBER_TOW_WEEKS = 1209600;


    // token 前缀
    public static final String LOGIN_TOKEN = "login:token_";

    // TOKEN 有效时间 分钟
    public static final int TOKEN_EXPIRE_TIME = 30;

    // jwt 中存储用户信息的 key
    public static final String JWT_LOGIN_INFO_KEY = "login-info";
}
