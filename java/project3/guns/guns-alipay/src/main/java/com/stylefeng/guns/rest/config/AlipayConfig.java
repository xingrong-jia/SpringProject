package com.stylefeng.guns.rest.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-12 21:12
 */
@Data
@Configuration
public class AlipayConfig {



    /**
     * 支付宝网关（固定）
     *
     * https://openapi.alipay.com/gateway.do
     */
    private  String URL="https://openapi.alipaydev.com/gateway.do";

    /* APP_ID
     *
     * APPID 即创建应用后生成
     *
     * 详情见创建应用并获取 APPID
     */
    private  String APPID="2016102100732230";
    /* APP_PRIVATE_KEY
     *
     * 开发者应用私钥，由开发者自己生成
     *
     * 详见配置密钥
     */
    private  String APPPRIVATEKEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKzNuuvw9u9KSfosiPacPzGoiqtooO8OVdEQHpMgVowiVhkqBo2vivxg/lg0m9iKb2H+nN018VkvCMwDtBTmjX1lg7gKVXY5s/TFZfZDiVGH5CCt+w5L+l4xQhg6RPjRBeb7fMbCbXK0hLi2XV9zAoVb4XQpfciYGQ+NU8Wvs99XJz1HV/lcvDXgEWfw1vT+jie6l5eHA4xYA0bbeZoEN4r+pHeELqTTvz3bitp+iYAlXwwCuasatlZHi77c6F4MWb8f5hKRegqaaNsv6V7IAw3ii6Nrt4eLMDzI1BYVL2h2tyGT086Xt8QlraFRXURbOigBhKxzYcLUvyfVzyaoVZAgMBAAECggEABh3Q8Hpq67DMMFy0Cd3XTLc2no88tjvXX6qIMwaJRnyHuS0E9Jnxq41G1g5bCYckFGSDoRZXclPvVcSC1/XkmN5RNQ4OOzOge8GZbMq105wotDBWey0ZZO4EzRVIAFlvBzpVFyUM8R5vKSUTb8Jc1OoyXkQI/SbyFrlgbDB2Mdy2eVJmHACN3CQf4MVfXYIADktVIGhpfNvuBKkWraS5gXODSU6qo/aOuDyfLreZSSa8OyVcP0nTY0VJYtQ3FnRoi0WM7QgVF9dozLYLwt0bGooEmAdXtLN6BTlGSLloDjgjMrTAG1kKSd2Yg83MxitprpoB9I+nz1o6PIZfhdKKoQKBgQC9ivI+RE8pAg6F0YGnr2DoBeB/FuW1yUbembgG9zW2qAqEW1lYs0Png7x9VO6fzEFodlsH7BGvtzD8RVFXskJOh7Xt0qX/VTU/QAR8GlR2aqwBeHg3aikE5g3GFnkMkvbHGZiqGpKvsXhHJkFVAqu2oqN+Od7VxCU1b1H/2A/adwKBgQC7d1f7zi/HNtod8CL4SWLPMhyim4XLsmICtFFntI1fl3hNYl/OU0vAVZhrRa9bKsbVM1cCpkk7DSqQTxymGjkljmA2nxZobzjHYaPUqZx5JA7F7ExeHOvN9yjWl27xUzzWcVOmRbHGkhF9PYzJr4vRkDMRVCIjchbWzN21X6rCrwKBgQCM3i5JbrUycg/fDqUAvgwUhQGjEp5oa43bNH2b17C47szaL5YIoTqvXq4Aq9WpN8HI5GWlTqKaaFjktJPOzHIRMTVZ6jtqt6YaOvbaUStC0ubBNQqLjL+tn1jPRrZyTOtRb8mRaqxoHUqZ/mmBx7lcxCUkU9g0IVHSE8jqj2bA4wKBgAxR7ItU4pjyBuWeyX2NvRumzHD3TZS0dXJ+DTeGix/0IWjchTVXDgxm06yBGOQh7I1IYXiAGyHxXJG9UUGtSYIpWDNewyc73/xic1TfjLP9ZacmP2KT/f88NgfcTeM4ODBTpE6yF9vX76RObjk2+zZbhq+l2tR/g/a74fowflVrAoGAU8XxHl/c72LZzn2zmjvEMzBvSTpv4GHWix4syzQFpIjQl8uIDPjBD7WdNusZ5lJowSc672IG7e/muzJ17C9DaCLu7uuJGgiV+I77qK38e7BD9q7BQxd15i/fQocBagr3buS2QweQku7IerUmdGNXDbuoSexpa/dzc8EPeDC1egk=";
    /* FORMAT
     *
     * 参数返回格式，只支持 json 格式
     *
     * json（固定）
     */
    private  String FORMAT="json";
    /* CHARSET
     *
     * 请求和签名使用的字符编码格式，支持 GBK和 UTF-8
     *
     * 开发者根据实际工程编码配置
     */
    private  String CHARSET="UTF-8";

    /* ALIPAY_PUBLIC_KEY
     *
     * 支付宝公钥，由支付宝生成
     *
     * 详见配置密钥
     */
    private  String ALIPAYPUBLICKEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0nZ3l5e7mBx8lfvN+XnDxq9XqN1b+BKCLwi9+I5SOkP6D+v3/In7pzRt3TQTdeDEdw+ZLf/QjXCPWE7LlVI6/jlNUv3zexkz/ZwY2wFd/5DGVh9CzTBWEVBe4XwdpponZpZYQbmnt9FHjGiRNSCVgmaiyqinvrD/XvVIlC8zoaZpM4OtjPmufBhvJ5hSpFcYtoJWmW0hP/I8rKp8oiAHIQ/KS0NbmXpkoxmAPm2fpVVqszl/GhK4ACI/bAKEJseKgqnxjtdh0m/Nv0chINDZC/bbTSdYs9kLbhV75EVRmYZuD2K63x+0ODi35vWjDpHvIz/PNcV6tHyPdoytBJPEAQIDAQAB";
    /* SIGN_TYPE
     *
     * 商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐商家使用 RSA2。
     *
     * RSA2
     *
     */
    private  String SIGNTYPE="RSA2";


    @Bean
    public AlipayClient alipayClient(){
        AlipayClient  alipayClient = new DefaultAlipayClient(URL, APPID, APPPRIVATEKEY, FORMAT, CHARSET, ALIPAYPUBLICKEY, SIGNTYPE);
        return alipayClient;
    }
}
