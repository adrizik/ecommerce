pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'OpenJDK'
    }
    environment{
        PATH = "/usr/local/bin:${PATH}:/usr/local/bin/docker"
    }
    stages {
        stage('Build maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/adrizik/ecommerce']])
                sh "mvn clean install"
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t adriana837/ecommerce-integration .'
                }
            }
        }
        stage('Push image to hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u adriana837 -p ${dockerhubpwd}'
                    }

                    sh 'docker push adriana837/ecommerce-integration'
                }
            }
        }
    }
}
