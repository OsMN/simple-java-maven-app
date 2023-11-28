pipeline 
{
    /*
    agent 
    {
        docker 
        {
            image 'maven:3.9.4-eclipse-temurin-17-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }*/
    agent any
    tools
    {
        maven 'Maven 2.2.1'
        jdk 'JDK 6u45'
    }
    stages
    {
        stage('Build') 
        { 
            steps 
            {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') 
        {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Sonarqube Analysis - SAST') 
        {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=Simple-java-maven \
                            -Dsonar.projectName='Simple java maven' \
                            -Dsonar.host.url=http://127.0.0.1:9000/sonar \
                            -Dsonar.token=sqp_6c0db8b5d88986285b3ac1d4417879316464a5b5"
                }
//           timeout(time: 2, unit: 'MINUTES') {
//                      script {
//                        waitForQualityGate abortPipeline: true
//                    }
//                }
            }
        }            
    }    
}
