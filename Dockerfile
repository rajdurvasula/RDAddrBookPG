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

RUN mkdir -p /root/app

COPY resources/postgresql10-10.6-1PGDG.rhel7.x86_64.rpm /root/postgresql10-10.6-1PGDG.rhel7.x86_64.rpm
COPY resources/postgresql10-contrib-10.6-1PGDG.rhel7.x86_64.rpm /root/postgresql10-contrib-10.6-1PGDG.rhel7.x86_64.rpm
COPY resources/postgresql10-libs-10.6-1PGDG.rhel7.x86_64.rpm /root/postgresql10-libs-10.6-1PGDG.rhel7.x86_64.rpm
COPY resources/postgresql-42.2.5.jar /root/postgresql-42.2.5.jar
COPY resources/TestPgsql.java /root/TestPgsql.java
COPY resources/RDAddrBook-0.0.1-SNAPSHOT.jar /root/app/RDAddrBook-0.0.1-SNAPSHOT.jar
COPY resources/start_app.sh /root/app/start_app.sh
COPY resources/setup_db_host_ip.sh /root/setup_db_host_ip.sh

RUN yum -y install libicu systemd-sysv libxslt dos2unix && \
  mkdir -p /root/.postgresql && \
  rpm -Uvh /root/postgresql10-libs-10.6-1PGDG.rhel7.x86_64.rpm && \
  rpm -Uvh /root/postgresql10-10.6-1PGDG.rhel7.x86_64.rpm && \
  rpm -Uvh /root/postgresql10-contrib-10.6-1PGDG.rhel7.x86_64.rpm

RUN dos2unix /root/setup_db_host_ip.sh && \
  dos2unix /root/app/start_app.sh && \
  chmod u+x /root/app/start_app.sh && \
  chmod u+x /root/setup_db_host_ip.sh && \
  rm -f /root/postgresql10-10.6-1PGDG.rhel7.x86_64.rpm && \
  rm -f /root/postgresql10-contrib-10.6-1PGDG.rhel7.x86_64.rpm && \
  rm -f /root/postgresql10-libs-10.6-1PGDG.rhel7.x86_64.rpm

EXPOSE $APP_PORT

ENTRYPOINT ["/root/app/start_app.sh"]
