FROM docker.flcn.io/base/java:latest
#
# Application
#
ENV JAVA_OPTS="-server \
            -Xms2g -Xmx2g \
            -XX:-OmitStackTraceInFastThrow" \
    APP_OPTS="" \
    APP_HOME="/app"

ADD target/rfc-reader-1.0.0.jar /app/rfc-reader.jar

ENTRYPOINT [ "sh", "-c", "cd $APP_HOME && $JAVA_HOME/bin/java $JAVA_OPTS $APP_OPTS -jar rfc-reader.jar" ]
EXPOSE 8088
