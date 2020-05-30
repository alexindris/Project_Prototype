#!/usr/bin/python
import sys
import Adafruit_DHT as dht
import datetime
import time
import request

url = 'https://192.168.1.133:8080/data'
sensor = dht.DHT22
id = "1"
sensor_type = "Humidity"
pin = 4

while True:
    #humidity = dht.read_retry(sensor, pin)
    humidity, temperature = dht.read_retry(sensor, pin)
    humidity = round(humidity, 2)
    
    date = datetime.datetime.now()
    
    currentTime = date.strftime("%X")
    currentDay = date.strftime("%x")
        
    values = {"id": id,
              "time": currentTime,
              "date": currentDate,
              "sensor": sensor,
              "valor": humidity
              }
    
    x = requests.post(url, data = values)
    time.sleep(1)

sys.exit(0)

