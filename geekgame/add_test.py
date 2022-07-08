# -*- coding:utf-8 -*-
"""
author: zliu.elliot
@time: 2022-07-07 23:16
@file: add_test.py
"""
import datetime
import random
from concurrent.futures import ThreadPoolExecutor, wait, ALL_COMPLETED

import requests

base_url = "http://192.168.1.105:8000/geek-account/account"
type_list = ["01", "02", "03"]
names = ["elliot", "sc", "qjp"]

total = 1000

pool = ThreadPoolExecutor(max_workers=10)


def test(account):
    start = datetime.datetime.now()
    result = requests.post(f"{base_url}/add", json=account)
    end = datetime.datetime.now()
    return result, start, end


# account = {"owner": "elliot", "type": random.choice(type_list), "balance": random.randint(0, 100000000)}
# all_task = [pool.submit(test, account) for i in range(total)]
# wait(all_task, return_when=ALL_COMPLETED)

for i in range(total):
    account = {"owner": "elliot", "type": random.choice(type_list), "balance": random.randint(0, 100000000)}
    res = test(account)
    print(res)
