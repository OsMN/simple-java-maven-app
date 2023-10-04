pipeline 
{
    agent 
    {
        docker 
        {
            image 'maven:3.9.4-eclipse-temurin-17-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
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
                    sh "mvn -Dsonar.projectKey=Simple-java-maven \
                            -Dsonar.projectName='Simple java maven' \
                            -Dsonar.host.url=http://localhost:9000 \
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
