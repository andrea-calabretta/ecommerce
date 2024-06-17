import time
from locust import HttpUser, task, between

class WebsiteUser(HttpUser):
    wait_time = between(1, 5)

    @task
    def payment_ping(self):
        self.client.post(url="/payment/ping")
