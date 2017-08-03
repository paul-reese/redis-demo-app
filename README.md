# redis-demo-app

This demo app will continually post messages to redis.
```
017-08-03T12:13:22.21-0500 [APP/PROC/WEB/0] OUT 2017-08-03 17:13:22.214  INFO 14 --- [           main] i.p.RedisDemo.RedisDemoApplication       : Sending message...
2017-08-03T12:13:23.21-0500 [APP/PROC/WEB/0] OUT 2017-08-03 17:13:23.216  INFO 14 --- [           main] i.p.RedisDemo.RedisDemoApplication       : Sending message...
2017-08-03T12:13:23.21-0500 [APP/PROC/WEB/0] OUT 2017-08-03 17:13:23.218  INFO 14 --- [  container-195] io.pivotal.RedisDemo.Receiver            : Received <Hello from Redis, d0048d2a-027c-4ebb-bc70-ef7c4fabd1fc!>
```
