pipeline {
    agent any
    stages {
        stage('pre'){
            steps{
                script {
                    env.MY_GIT_TAG = sh(returnStdout: true, script: 'git tag -l --points-at HEAD').trim()
                }
            }
        }
        stage('down'){
            when {
                expression {
                    return env.MYENV == 'TEST'||(env.MYENV == 'PROD'&&env.MY_GIT_TAG.startsWith("v"))
                    }
                }
            steps {
                sh 'docker-compose down -v'
            }
        }
        stage('build') {
            when {
                expression {
                    return env.MYENV == 'TEST'||(env.MYENV == 'PROD'&&env.MY_GIT_TAG.startsWith("v"))
                    }
                }
            agent {
                    docker {
                        image 'gradle:jdk14'
                        args """
-v /root/.gradle:/home/gradle/.gradle
-v /var/run/docker.sock:/var/run/docker.sock
-v /usr/bin/docker:/usr/bin/docker
-e MYENV="${env.MYENV}"
""" + ((env.MYPROXY_HOST == null)? "" : """
-e HTTP_PROXY="http://${env.MYPROXY_HOST}:${env.MYPROXY_PORT}"
-e HTTPS_PROXY="http://${env.MYPROXY_HOST}:${env.MYPROXY_PORT}"
-e NO_PROXY="121.36.41.244"
""")
                    }
                }
            steps {
				sh """
gradle \
""" + ((env.MYPROXY_HOST == null)? "" : """ \
 -PsystemProp.http.proxyHost=${env.MYPROXY_HOST} \
 -PsystemProp.http.proxyPort=${env.MYPROXY_PORT} \
 -PsystemProp.http.nonProxyHosts=121.36.41.244 \
 -PsystemProp.https.proxyHost=${env.MYPROXY_HOST} \
 -PsystemProp.https.proxyPort=${env.MYPROXY_PORT} \
 -PsystemProp.https.nonProxyHosts=121.36.41.244 \
""") + """ \
 docker
"""
				sh 'gradle dockerTagLatest'
            }
        }
        stage('compose'){
            when {
                expression {
                    return env.MYENV == 'TEST'||(env.MYENV == 'PROD'&&env.MY_GIT_TAG.startsWith("v"))
                    }
                }
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}