FROM sftth/mytomcat8:1.2
ADD ./target/mainWebApp.war /usr/local/tomcat/webapps
EXPOSE 8080
