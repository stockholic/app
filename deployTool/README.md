### 1. 소개 
* Jenkins 또는 Command 창에서 원격으로 WAR 파일을 Deploy 한다

---

### 2. 설정
* build 후 properties 를 서버에 맞게 변경한다.  
D:/deploy/server.properties  

```
debug = false    

server.host = 192.168.0.1  
server.port = 00  
server.user = xxxxxxxxxxxx  
server.password = xxxxxxxxxxxxx  
server.source.path = C:/xxx/xxx  
server.target.path = /xxx/xxx  
server.war.name = xxx.war  
```  

* 배포 서버가 여러대 일 경우 properties 를 반복해서 작성한다. 

```
debug = false    

server.host = 192.168.0.1  
server.port = 00  
server.user = xxxxxxxxxxxx  
server.password = xxxxxxxxxxxxx  
server.source.path = C:/xxx/xxx  
server.target.path = /xxx/xxx  
server.war.name = xxx.war  

server.host = 192.168.0.2 
server.port = 00  
server.user = xxxxxxxxxxxx  
server.password = xxxxxxxxxxxxx  
server.source.path = C:/xxx/xxx  
server.target.path = /xxx/xxx  
server.war.name = xxx.war  
```

---

### 3. 실행
* 키값으로 문자열을 암호한 한다.  
ex) java -jar deployTool.jar -e 문자열 -k 암호키 

* war 파일을 여러 서버에 복사한다.  
ex) java -jar deployTool.jar -w -k 암호키 

* Tomcat 을 start / stop 한다.

---

### 4. 기타
*  톰켓 실행에 권한 문제가 있어 아래와 같이 스크립트에 sudo 권한 부여해주어야  함  
$ visudo  
user  ALL=(ALL)       NOPASSWD:/etc/rc.d/init.d/tomcatd

