FROM openjdk:17-ea

EXPOSE 8080

ADD target/spring-kube-0.0.1-SNAPSHOT.jar spring-kube-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/spring-kube-0.0.1-SNAPSHOT.jar"]
