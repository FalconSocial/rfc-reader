FROM docker.flcn.io/base/java:latest
#
# Application
#
ENV JAVA_OPTS="-server \
            -Xms512m -Xmx512m \
            -XX:-OmitStackTraceInFastThrow" \
    APP_OPTS="" \
    APP_HOME="/app"

ADD target/flock-rfc-reader-0.0.1-SNAPSHOT.jar /app/rfc-reader-dist.jar

ENTRYPOINT [ "sh", "-c", "cd $APP_HOME && $JAVA_HOME/bin/java $JAVA_OPTS $APP_OPTS -jar rfc-reader-dist.jar" ]
EXPOSE 8080

