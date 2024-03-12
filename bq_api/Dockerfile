# 使用基础镜像，包含 Oracle JDK 8
FROM openjdk:11

# 设置工作目录
WORKDIR /app

# 将构建好的 JAR 文件复制到容器中
COPY target/*.jar app.jar

# 暴露 8080 端口
EXPOSE 8080

# 启动 Spring Boot 应用，并指定容器内部的端口为 8080
CMD ["java", "-jar", "-Dserver.port=8080", "app.jar"]