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
                sh 'mvn -s .mvn/settings.xml deploy -DskipTests' 
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
                            -Dsonar.token=sqp_eb0d3790ccc0e5b53fe4fe2bc880b0576dfd2033"
                }
                timeout(time: 2, unit: 'MINUTES') {
                      script {
                        waitForQualityGate abortPipeline: true
                    }
                }
            }
        }            
    }    
}
