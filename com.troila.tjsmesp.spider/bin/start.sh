#!/bin/bash
ARG0=$0
DIRNAME="`dirname $ARG0`"

export APP_HOME=${DIRNAME}/..

#[ -z "$JAVA_HOME" ] && JAVA_HOME="/usr/java/jdk1.7.0_21"
#JAVA_OPTS="-Xmx512m -Duser.timezone=Asia/Shanghai"
JAVA_OPTS="-Xms1024m -Xmx1024m -Xss512k -Duser.timezone=Asia/Shanghai"

##CLASSPATH="$APP_HOME/lib/*.jar;$APP_HOME/plugins/*.jar"

for i in $APP_HOME/lib/*.jar; do     
        CLASSPATH="${CLASSPATH}:${i}"     
done

"${JAVA_HOME:-/usr}/bin/java" -cp ${CLASSPATH} -server -Dlogging.config=${APP_HOME}/conf/logback.xml -Dspring.config.location=${APP_HOME}/conf/application.properties -Dname=com.troila.tjsmesp.spider ${JAVA_OPTS} -DAPP_HOME=${APP_HOME} com.troila.tjsmesp.spider.SpiderApplication 2>&1   &
echo $! > "${APP_HOME}/pid"
      

