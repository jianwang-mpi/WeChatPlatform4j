import json
import re
import sys

import requests

reload(sys)
sys.setdefaultencoding('utf-8')
s = requests.session()
s.headers.update({'cookie':'noticeLoginFlag=1; pgv_pvi=5042961408; RK=StnaLVncMu; p_o2_uin=1923359914; noticeLoginFlag=1; pgv_pvid=4088850756; o_cookie=1923359914; ptui_loginuin=1923359914; ptcz=1caf2808419a9f6349dc53506b9fc2bbdba516eb22639ac4dade0e9f58823540; pt2gguin=o1923359914; pgv_si=s6137033728; data_bizuin=3094030808; data_ticket=RTQVhyUE/zy99Rl+/Hjczayas1fIDPkKTG+42em3WelQS5NQojF7ICnvTcI7uxzY; slave_user=gh_f502635b2763; slave_sid=ajMzTlQ5SEJnR1FMQTdEQ1NrbWRIbUZWc2hxR0FabEFKX0xoYkdtSEc3X1lwT3VwbFFzbmpMc1V1aVhSTFFCaFRpWHNtcElsdXZIZUJhSVl2Vk9RTnY1dzJWWjlvcDRJQkozdHU2SUdicTEwYmlEdW1DZ1VOVTNtVHloSVNRc1J2ZW9TNk94b1lvbzdVaTVT; bizuin=3094030808'})

url = 'https://mp.weixin.qq.com/cgi-bin/masssendpage?t=mass/list&action=history&begin={0}&count=10&token=1002898648&lang=zh_CN'
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
        f.write(json.dumps(item, ensure_ascii=False) + '\n')
