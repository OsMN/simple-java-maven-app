pipeline {
    agent {
        docker {
            image 'maven:3.9.4-eclipse-temurin-17-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Sonarqube Analisys - SAST') {
            steps {
                withSonarQubeEnv('sonarqube-server'){
                    sh '
                    mvn sonar:sonar \
                    -Dsonar.projectKey=00-simple-java-maven \
                    -Dsonar.host.url=http://localhost:9000
                    '
                }
            }
        }        
    }    
}