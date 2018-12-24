FROM sftth/mytomcat8:1.2
ADD mainWebApp.war /usr/local/tomcat/webapps
EXPOSE 8080
