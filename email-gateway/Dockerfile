FROM openjdk:22

VOLUME /tmp/tomcat

EXPOSE 25

ADD target/my-email-gateway-jar-with-dependencies.jar my-email-gateway.jar

ENTRYPOINT ["java", "-jar", "/my-email-gateway.jar"]