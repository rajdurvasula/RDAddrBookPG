FROM 466323227181.dkr.ecr.eu-west-1.amazonaws.com/maven:3.5.4-jdk8
LABEL maintainer="raj.durvasula@gmail.com"

ENV APP_PORT 9080
ENV PG_HOST_IP 192.168.101.21
ENV PG_HOST pubcloud1.rdurvasula.net
ENV PG_PORT 5432
ENV PG_USER rdadmin1
ENV PG_PASSWORD passw0rd
ENV APP_DB rdaddrbookdb

ARG DEPENDENCY=target

RUN mkdir -p /root/app/src && \
  mkdir -p /root/app/scripts

COPY resources/postgresql10-10.6-1PGDG.rhel7.x86_64.rpm /root/postgresql10-10.6-1PGDG.rhel7.x86_64.rpm
COPY resources/postgresql10-contrib-10.6-1PGDG.rhel7.x86_64.rpm /root/postgresql10-contrib-10.6-1PGDG.rhel7.x86_64.rpm
COPY resources/postgresql10-libs-10.6-1PGDG.rhel7.x86_64.rpm /root/postgresql10-libs-10.6-1PGDG.rhel7.x86_64.rpm
COPY mvnw* /root/app/
COPY pom.xml /root/app/
COPY src /root/app/src/
COPY scripts /root/app/scripts/
COPY resources/start_app.sh /root/app/start_app.sh
COPY resources/setup_db_host_ip.sh /root/setup_db_host_ip.sh

RUN yum -y install libicu systemd-sysv libxslt dos2unix && \
  mkdir -p /root/.postgresql && \
  rpm -Uvh /root/postgresql10-libs-10.6-1PGDG.rhel7.x86_64.rpm && \
  rpm -Uvh /root/postgresql10-10.6-1PGDG.rhel7.x86_64.rpm && \
  rpm -Uvh /root/postgresql10-contrib-10.6-1PGDG.rhel7.x86_64.rpm && \
  yum -y install awslogs

RUN cd /root/app && \
  ls && \
  /usr/local/src/apache-maven-3.5.4/bin/mvn clean && \
  /usr/local/src/apache-maven-3.5.4/bin/mvn package -DskipTests && \
  dos2unix /root/setup_db_host_ip.sh && \
  dos2unix /root/app/start_app.sh && \
  chmod u+x /root/app/start_app.sh && \
  chmod u+x /root/setup_db_host_ip.sh && \
  rm -f /root/postgresql10-10.6-1PGDG.rhel7.x86_64.rpm && \
  rm -f /root/postgresql10-contrib-10.6-1PGDG.rhel7.x86_64.rpm && \
  rm -f /root/postgresql10-libs-10.6-1PGDG.rhel7.x86_64.rpm

EXPOSE $APP_PORT

ENTRYPOINT ["/root/app/start_app.sh"]
