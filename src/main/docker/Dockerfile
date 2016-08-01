FROM registry.aliyuncs.com/leansw/quartz-service:dep

VOLUME /tmp
ADD entrypoint.sh entrypoint.sh
ADD quartz-service-1.0-SNAPSHOT.jar quartz-service.jar
RUN bash -c 'touch /quartz-service.jar'
RUN chmod +x entrypoint.sh
EXPOSE 2400
ENTRYPOINT ["./entrypoint.sh"]