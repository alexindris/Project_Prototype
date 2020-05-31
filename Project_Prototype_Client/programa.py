#!/usr/bin/python
import sys
import Adafruit_DHT as dht
import datetime
import time
import requests


def loop(url):
    sensor = dht.DHT22
    id = "1"
    sensor_type = "Humidity"
    pin = 4
    humidity = 0
    while True:
        humidity, temperature = dht.read_retry(sensor, pin)
        date = datetime.datetime.now()

        currentTime = date.strftime("%X")
        currentDay = date.strftime("%x")

        values = {"id": id,
                  "time": currentTime,
                  "date": currentDay,
                  "sensor": sensor_type,
                  "value": humidity
                  }
        try:
            x = requests.post(url, json=values)
        except requests.exceptions.RequestException as e:  # This is the correct syntax
            raise SystemExit(e)

        print(x.text)
        time.sleep(1)


url = 'http://' + str(raw_input("Introduce the website of the api ex(www.yourapi.com)")) + ':8080/data'
loop(url)
