# 基础镜像
ARG BASE_IMAGE=public.ecr.aws/docker/library/openjdk:8-jre-slim
FROM ${BASE_IMAGE}

# 作者
LABEL maintainer="kiyasu"

# 配置
ENV PARAMS=""

# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 添加应用
ADD target/s-pay-mall-mvc-app.jar /s-pay-mall-mvc-app.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /s-pay-mall-mvc-app.jar $PARAMS"]