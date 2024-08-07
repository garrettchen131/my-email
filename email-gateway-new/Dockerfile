FROM openjdk:22



VOLUME /tmp/tomcat

EXPOSE 25
EXPOSE 8080

ADD target/my-email-gateway-new-jar-with-dependencies.jar my-email-gateway-new.jar

ENTRYPOINT ["java", "-jar", "/my-email-gateway-new.jar"]