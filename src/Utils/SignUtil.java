package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 请求校验工具类
 * Created by Alchemist on 2016/4/13.
 */
public class SignUtil {
    private static String token  = "weixin";
    /*
    * signature:加密签名
    * timestamp：时间戳
    * nonce：随机数
    * 这个函数实现了获得微信服务器提供的相关参数，进行加密之后验证的功能，具体参见微信开发平台的文档
    * 注意：调用sha-1算法要用NoSuchAlgorithmException
    */
    public static boolean checkSignature(String signature, String timestamp, String nonce){
        String[] arr = new String[]{token, timestamp, nonce};
        // 将 token, timestamp, nonce 三个参数进行字典排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行 shal 加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()): false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param digest
     * @return
     */
    private static String byteToStr(byte[] digest) {
        // TODO Auto-generated method stub
        String strDigest = "";
        for(int i = 0; i < digest.length; i++){
            strDigest += byteToHexStr(digest[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     * @param b
     * @return
     */
    private static String byteToHexStr(byte b) {
        // TODO Auto-generated method stub
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(b >>> 4) & 0X0F];
        tempArr[1] = Digit[b & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}
