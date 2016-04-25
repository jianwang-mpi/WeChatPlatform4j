package Utils;

import Buttons.ButtonMenu;
import log4j.Log4j;
import net.sf.json.JSONObject;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class MenuUtil {
    public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public final static String menu_get_url="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    public final static String menu_delete_url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    public static boolean createMenu(ButtonMenu menu,String accessToken){
        boolean result=false;
        String url = menu_create_url.replace("ACCESS_TOKEN",accessToken);
        String jsonMenu = JSONObject.fromObject(menu).toString();
        //
        System.out.println(jsonMenu);
        System.out.println(accessToken);
        //
        JSONObject jsonObject = CommonUtils.httpsRequest(url,"POST",jsonMenu);
        if(null!=jsonObject){
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if(errorCode==0){
                result=true;
            }else{
                result=false;
                Log4j log4j = new Log4j();
                log4j.infolog("create menu failed!"+errorMsg);
            }
        }
        return result;
    }
    public static String GetMenu(String accessToken){
        String result = null;
        String requestURL = menu_get_url.replace("ACCESS_TOKEN",accessToken);
        JSONObject jsonObject = CommonUtils.httpsRequest(requestURL,"GET",null);
        if(jsonObject!=null){
            result = jsonObject.toString();
        }
        return result;
    }
    public static boolean deleteMenu(String accessToken){
        boolean result=false;
        String requestURL = menu_delete_url.replace("ACCESS_TOKEN",accessToken);
        JSONObject jsonObject = CommonUtils.httpsRequest(requestURL,"GET",null);
        if(jsonObject!=null){
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            result=true;
            if(errorCode==0){
                result=true;
            }else{
                result=false;
                Log4j log4j = new Log4j();
                log4j.infolog("delete failed!"+errorMsg);
            }
        }
        return result;
    }

}
