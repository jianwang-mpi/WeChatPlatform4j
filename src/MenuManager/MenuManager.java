package MenuManager;

import Buttons.Button;
import Buttons.ButtonMenu;
import Buttons.ViewButton;
import Utils.CommonUtils;
import Utils.MenuUtil;
import Utils.Token;
import log4j.Log4j;


/**
 * Created by Alchemist on 2016/4/25.
 */
public class MenuManager {
    private static ButtonMenu getMenu() {



        ButtonMenu menu = MenuFactory.setMenu();

        return menu;
    }

    public static void main(String args[]){
        Token token  = CommonUtils.getToken();
        boolean result=false;
        if(token!=null){
            result = MenuUtil.createMenu(getMenu(),token.getAccessToken());
        }
        Log4j log4j = new Log4j();

        if(result){
            log4j.infolog("create menu success");
        }else{
            log4j.infolog("create menu failed");
        }
    }
}
