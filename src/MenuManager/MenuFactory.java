package MenuManager;

import Buttons.Button;
import Buttons.ButtonMenu;
import Buttons.ComplexButton;
import Buttons.ViewButton;

/**
 * Created by Alchemist on 2016/5/4.
 */
public class MenuFactory {
    public static ButtonMenu setMenu(){
        ButtonMenu menu = null;

        ViewButton survey = new ViewButton();
        survey.setName("调查");
        survey.setType("view");
        survey.setUrl("http://mp.weixin.qq.com/s?__biz=MzA3NzAzMDEyNg==&mid=503339989&idx=1&sn=2b5a644e4756b35363f923a3c6c7b61d&scene=18#wechat_redirect");

        ViewButton view = new ViewButton();
        view.setName("视界");
        view.setType("view");
        view.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=13&sn=78064eaee906f315fa253dfa80c7a967#wechat_redirect");

        ViewButton special = new ViewButton();
        special.setName("特稿");
        special.setType("view");
        special.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=5&sn=b086cc1ac6e7e850e30ff86479642dd7#wechat_redirect");

        ViewButton account = new ViewButton();
        account.setName("评论");
        account.setType("view");
        account.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=8&sn=97917c0e6a29d0c0cb03a5cbad7ad8f1#wechat_redirect");

        ComplexButton stone = new ComplexButton();
        stone.setName("石头");
        stone.setSub_button(new Button[]{survey,view,special,account});


        ViewButton time = new ViewButton();
        time.setName("光阴");
        time.setType("view");
        time.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=12&sn=1c3bcfa7bca70a289868bf39b0be0f1d#wechat_redirect");

        ViewButton people = new ViewButton();
        people.setName("人物");
        people.setType("view");
        people.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=7&sn=dd2a204c770a6d5578ff3f7b8f7a5770#wechat_redirect");

        ViewButton knowledge = new ViewButton();
        knowledge.setName("姿势");
        knowledge.setType("view");
        knowledge.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=11&sn=72d0d6835610a7c1cdd0e0a90929ea12#wechat_redirect");

        ViewButton mob = new ViewButton();
        mob.setName("机动");
        mob.setType("view");
        mob.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=10&sn=45d0f769886a56d9d59d542e797e1aaf#wechat_redirect");

        ViewButton rank = new ViewButton();
        rank.setName("排行榜");
        rank.setType("view");
        rank.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=14&sn=32d513114a013e0882f81df5fceca586#wechat_redirect");

        ComplexButton scissor = new ComplexButton();
        scissor.setName("剪刀");
        scissor.setSub_button(new Button[]{time,people,knowledge,mob,rank});


        ViewButton photo = new ViewButton();
        photo.setName("摄影");
        photo.setType("view");
        photo.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=2&sn=6aeadc1c0c49ae2ba51724ae24e06a2e#wechat_redirect");

        ViewButton self = new ViewButton();
        self.setName("言己");
        self.setType("view");
        self.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=3&sn=daa683ec8c33b4bcebccb637b213a9de#wechat_redirect");

        ViewButton diaolong = new ViewButton();
        diaolong.setName("雕龙");
        diaolong.setType("view");
        diaolong.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=4&sn=f638855575b79ca7265059ae5ced6262#wechat_redirect");

        ViewButton seeagain = new ViewButton();
        seeagain.setName("又见");
        seeagain.setType("view");
        seeagain.setUrl("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3NzAzMDEyNg==&hid=9&sn=123fb1c78429a0082b8f6c729882fca1#wechat_redirect");

        ComplexButton cloth = new ComplexButton();
        cloth.setName("布");
        cloth.setSub_button(new Button[]{photo,self,diaolong,seeagain});

        menu = new ButtonMenu();
        menu.setButtons(new Button[]{stone,scissor,cloth});
        return menu;
    }
}
