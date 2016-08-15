FROM registry.aliyuncs.com/leansw/java-base
EXPOSE 9005
VOLUME /tmp
COPY lib /lean/java/lib
ADD entrypoint.sh /entrypoint.sh
ENTRYPOINT sh /entrypoint.sh
ADD quartz-service-1.0-SNAPSHOT.jar /quartz-service.jar
RUN touch /quartz-service.jar && \
    touch /lean/java/lib/*.jar && \
    chmod +x /entrypoint.sh