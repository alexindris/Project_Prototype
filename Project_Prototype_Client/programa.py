#!/usr/bin/python
import sys
import Adafruit_DHT as dht
import datetime
import time
import requests

url = 'https://tcm-prototype-apirest.herokuapp.com/data'
sensor = dht.DHT22
id = "1"
sensor_type = "Humidity"
pin = 4
humidity = 0
def valmap(value, istart, istop, ostart, ostop):
  return ostart + (ostop - ostart) * ((value - istart) / (istop - istart))

while True:
    
    humidity, temperature = dht.read_retry(sensor, pin)
    date = datetime.datetime.now()

    currentTime = date.strftime("%X")
    currentDay = date.strftime("%d/%m/%Y")

    values = {"id": id,
              "time": currentTime,
              "date": currentDay,
              "sensor": sensor_type,
              "value": valmap(humidity,0,1023,0,100)
              }
    try:
        x = requests.post(url, json=values)
    except requests.exceptions.RequestException as e:  # This is the correct syntax
        print("ConnectionError:"+str(e))
        exit(0)

    print(x.text)
    time.sleep(3600)



