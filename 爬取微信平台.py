import requests
import re,os
import json
from bs4 import BeautifulSoup as bs
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
s = requests.session()
s.headers.update({'cookie':'noticeLoginFlag=1; o_cookie=365062829; pgv_pvid=2663851350; ptui_loginuin=365062829@qq.com; ptcz=5581a3b6116890fdd50d054057d97536ebc22c07c220af7586485b3417c82999; pt2gguin=o0365062829; pgv_pvi=7061868544; pgv_si=s1374621696; data_bizuin=3094030808; data_ticket=BDD8BHrOetPFGw3sKSbYZfKuIzegzaj75Lj/2a95Znc6+ZTNAjO+aHWjzg25HY7S; noticeLoginFlag=0; slave_user=gh_f502635b2763; slave_sid=b1dyYTFzNlppTmduZkMxY1pIMTZJSmZtbWVDRENsQ1pUNFZpZE5Ka3FMQlBvRVJSdXZQTEFvMGZSVnVCc215c3pHV3JaOHJ0dnY2WVN1bWk2aUYzZmFkSWJBZmJMVGVCSlJGYVNNWElkeDdLOGJFbkViV25QUmpxSmFtVHQxRjlHMnZXeUlyamNDclhWbWlK; bizuin=3077030126'})

url = 'https://mp.weixin.qq.com/cgi-bin/masssendpage?t=mass/list&action=history&begin={}&count=10&token=1877814760&lang=zh_CN'
j = {'msg_item':[]}
for i in range(76):
    url2 = url.format(str(10*i))
    res = s.get(url2).content
    tmp = re.search('list : \((.*?)\).msg_item', res).group(1)
    tmp = json.loads(tmp)
    j['msg_item'] += tmp['msg_item']
    print len(j['msg_item'])
with open('list.txt', 'wb+') as f:
    for item in j['msg_item']:
        f.write(json.dumps(item, ensure_ascii=False) + '\n\n')
