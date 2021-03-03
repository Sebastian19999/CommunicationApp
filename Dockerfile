FROM openjdk:8
EXPOSE 8080
ADD target/docker-second-project.jar docker-second-project.jar
ENTRYPOINT ["java","-jar","docker-second-project.jar"]