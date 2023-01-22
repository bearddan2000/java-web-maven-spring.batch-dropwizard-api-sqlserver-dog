FROM maven:3-openjdk-17

WORKDIR /usr/src/mymaven

COPY bin .

RUN mvn package

RUN chmod +x /usr/src/mymaven/target/*.jar

# Add docker-compose-wait tool -------------------
ENV WAIT_VERSION 2.7.2

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait

RUN chmod +x /wait

CMD ["java", "-jar", "/usr/src/mymaven/target/DropWizardHibernateExample-FINAL.jar", "server", "dev.yml"]
