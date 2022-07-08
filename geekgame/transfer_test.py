# -*- coding:utf-8 -*-
"""
author: zliu.elliot
@time: 2022-07-08 15:56
@file: transfer_test.py
"""
import random

from locust import HttpUser, TaskSet, task


class TransferTask(TaskSet):
    accounts = []

    @task(1)
    def transfer(self):
        body = {"origin": random.choice(self.accounts), "target": random.choice(self.accounts), "amount": random.randint(0, 1000000)}
        self.client.post('/transfer', json=body, name="转账")


class WebsiteUser(HttpUser):
    tasks = [TransferTask]
    min_wait = 3000  # 用户执行任务之间等待时间的下界，单位：毫秒
    max_wait = 6000  # 用户执行任务之间等待时间的上界，单位：毫秒

