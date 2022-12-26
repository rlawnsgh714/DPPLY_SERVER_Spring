FROM openjdk:17
COPY build/libs/dpply-0.0.1-SNAPSHOT.jar dpply.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/app.jar","-Duser.timezone=Asia/Seoul"]
