# -*- coding:utf-8 -*-
"""
author: zliu.elliot
@time: 2022-07-08 15:01
@file: test_by_locust.py
"""
import os
import random

from locust import HttpUser, TaskSet, task


class AddUserTask(TaskSet):
    type_list = ["01", "02", "03"]
    names = ["张三", "李四", "王五", "苏轼", "曹操", "孙权", "关羽", "张飞"]

    @task(1)
    def add_user(self):
        user = {"owner": random.choice(self.names), "type": random.choice(self.type_list), "balance": random.randint(0, 100000000)}
        self.client.post('/add', json=user, name='添加账户')


class WebsiteUser(HttpUser):  # WebsiteUser()类用于设置性能测试
    tasks = [AddUserTask]  # 指向一个定义了的用户行为类
    min_wait = 3000  # 用户执行任务之间等待时间的下界，单位：毫秒
    max_wait = 6000  # 用户执行任务之间等待时间的上界，单位：毫秒
