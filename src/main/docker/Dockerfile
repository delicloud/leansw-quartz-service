FROM registry.aliyuncs.com/leansw/java-base
EXPOSE 9005
VOLUME /tmp
ADD *.jar /quartz-service.jar
ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT sh /entrypoint.sh
