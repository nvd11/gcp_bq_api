# 使用基础镜像，包含 Oracle JDK 8
FROM openjdk:17

# 设置工作目录
WORKDIR /app

# 将构建好的 JAR 文件复制到容器中
COPY target/*.jar app.jar

# 暴露 8080 端口
EXPOSE 8080

# 定义环境变量，默认为 dev
ENV APP_ENVIRONMENT=dev

# 设置启动命令
CMD java -jar -Dserver.port=8080 -Dspring.config.name=application-${APP_ENVIRONMENT} app.jar