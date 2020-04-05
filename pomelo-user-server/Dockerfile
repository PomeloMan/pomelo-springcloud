FROM java:8
MAINTAINER fengc <fengchao.z@outlook.com>
# 在主机 /var/lib/docker 目录下创建了一个临时文件夹，并链接到容器的/tmp
VOLUME /tmp
# 将 jar 包添加到容器中并更名为 eureka-server.jar
ADD target/pomelo-user-server-0.0.1.jar user-server.jar 
# 运行
RUN bash -c 'touch /user-server.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user-server.jar","--spring.profiles.active=test"]