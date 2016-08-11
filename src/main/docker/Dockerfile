FROM java:8-alpine
RUN apk add --no-cache bash && \
    apk add --no-cache curl
EXPOSE 9005
VOLUME /tmp
COPY lib /lean/java/lib
ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT sh /entrypoint.sh
ADD quartz-service-1.0-SNAPSHOT.jar /quartz-service.jar
RUN touch /quartz-service.jar