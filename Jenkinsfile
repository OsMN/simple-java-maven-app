pipeline 
{
    agent any
    tools {
        maven "MavenLocal"
    }
    stages 
    {
        stage('Build') 
        { 
            steps 
            {
//                sh 'mvn -B -DskipTests clean package' 
                sh 'mvn deploy -DskipTests' 
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
                            -Dsonar.host.url=http://sonarqube:9000 \
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
