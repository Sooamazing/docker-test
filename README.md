#test

##Docker, AWS 배포
### Dockerfile
- openjdk:21 이미지 파일을 베이스로
- jar 파일 (변수 초기화)
- 해당 jar 파일을 ./app.jar로 COPY => 여기서 ./app.jar는 어디에 있는 곳일까?
- Asia/Seoul 지부..? => 이건 왜 설정하지?
- ENTRYPOINT -> 명령어! => 이건 이미 정해진 규약을 갖다 쓰기? 내 마음대로 명령어 짓기 가능?
```agsl
FROM openjdk:21
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ./app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "./app.jar"]
```

- jar 생성
  - 인텔리제이 터미널 내(일반 터미널이면 해당 프로젝트 진입해서)에서 ./gradlew clean build
- dockerfile 작성
  - build/libs에 생성된 jar 파일 경로 이용 (plain X)
- docker image 생성 및 작동
  - docker build -t imagename .
  - docker run -it -p 8081:8080 --name test imagename
- docker hub에 올리기
- ec2 서버 내에 docker 설치 후 hub에서 image pull 받기
- 실행!
- 인스턴스 켤 때마다 실행해야 함!
- 내용물 변경 시 jar 생성하고, 이미지 push, 서버에서 pull, run!

### 결과물
- hi, 야호야호!

## application.properties gitignore
### 이미 올라갔던 파일 gitignore에 추가해 이후에 안 올라가게 하기.
=> ignore는 추적을 아예 하지 않게 해서 해당 파일이 변경돼도 commit 대상조차 되지 않음!

- ignore 파일에 추가할 내용 추가하고,
- **git rm --cached {path(삭제할 파일의 경로)}**
- git commit -a -m "gitignore에 properties 추가 및 캐시 제거”
- git log → 확인!!!
- git push origin <branch>

- cached를 안 해서였는지, ignore에 *.properties 추가해도 그냥 올라가서 난감했음..

=> 올라갔을 때, reset하는 법, 올라갔을 때 커밋 기록에서는 이전에 잘못 올라간 내용이 보이는데, 이것까지 없애는 법? 결국 reset하고 ignore로 하는 수밖에는 없나?
