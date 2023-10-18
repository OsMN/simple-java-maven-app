pipeline 
{
    agent any
    tools {
        maven "MavenLocal"
    }
    stages 
    {
        stage('Test') 
        {
            // Clean before build
            cleanWs()
            
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
                    sh "mvn sonar:sonar -Dsonar.projectKey=00-CD-Simpleje-java \
                            -Dsonar.projectName='00 CD Simpleje java' \
                            -Dsonar.host.url=http://sonarqube:9000 \
                            -Dsonar.token=sqp_abed0b140c50527b7fe01f83224e8c1742528264"
                }
            }
        }    
        stage('Release') 
        { 
            steps 
            {
                sh 'mvn -s .mvn/settings.xml deploy -DskipTests' 
            }
        }       
        stage('Build image') 
        { 
            steps 
            {
                sh "mkdir build \
                        && cd build \
                        && cp ../target/my-app-cd-*.jar app.jar \
                        && cp ../Dockerfile . \
                        && docker build -t helloworld"
            }
        }       

       stage('Deploy') 
        { 
            steps 
            {
                echo "Pasos para desplegar version"

            }
        }   
    }    
}
