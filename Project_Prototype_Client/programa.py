#!/usr/bin/python
import sys
import Adafruit_DHT as dht
import datetime
import time

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
    
    results = [id, currentTime, currentDay, sensor, humidity]
    title = ["ID", "Time: ", "Date: ", "Sensor: ", "Value: "]
    rec = 0
    
    file = open("humidityRresults", "a")
    
    for r in results:
        file.write("\n")
        file.write(title[rec])
        file.write(str(r))
        rec = rec + 1

    file.close()
    time.sleep(1)

#print('Temp={0:0.1f} *c, Hum={1:0:01f} %'.format(temperature, humidity))
sys.exit(0)
