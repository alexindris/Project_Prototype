#!/usr/bin/python
import sys
import Adafruit_DHT as dht
import datetime
import time
import requests

url = 'http://192.168.0.165:8080/data'
sensor = dht.DHT22
id = "1"
sensor_type = "Humidity"
pin = 4
humidity = 0
while True:
    #humidity = dht.read_retry(sensor, pin)
    #humidity, temperature = dht.read_retry(sensor, pin)
    date = datetime.datetime.now()
    
    currentTime = date.strftime("%X")
    currentDay = date.strftime("%x")
        
    values = {"id": id,
              "time": currentTime,
              "date": currentDay,
              "sensor": sensor_type,
              "value": humidity
              }
    x = requests.post(url, json = values)
    print(x.text)
    time.sleep(1)

sys.exit(0)

