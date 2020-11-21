FROM openjdk:11

ADD target/consumer-0.0.1-SNAPSHOT.jar ConsumerSB.jar

ENTRYPOINT ["java", "-jar", "ConsumerSB.jar"]