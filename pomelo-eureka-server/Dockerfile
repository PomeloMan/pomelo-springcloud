# docker build --build-arg env=xxx
ARG env
# docker build -e env=xxx
ENV env=${env}

echo "current environment: ${env}"

FROM java:8
MAINTAINER fengc <fengchao.z@outlook.com>
# 在主机 /var/lib/docker 目录下创建了一个临时文件夹，并链接到容器的/tmp
VOLUME /tmp
# 将 jar 包添加到容器中并更名为 eureka-server.jar
ADD target/pomelo-eureka-server-0.0.1.jar eureka-server.jar 
# 运行
RUN bash -c 'touch /eureka-server.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-server.jar","--spring.profiles.active=${env:-single}"]