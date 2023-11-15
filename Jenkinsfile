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
                    sh "mvn sonar:sonar -Dsonar.projectKey=00-CD-Simpleje-java \
                            -Dsonar.projectName='00 CD Simpleje java' \
                            -Dsonar.host.url=http://sonarqube:9000 \
                            -Dsonar.token=fa4469dbdb170015e678977b405e1d9fa57350a6"
                }
            }
        }
        stage("Quality Gate") 
        {
            steps {
                echo "Pasos para validar las QualityGate de Sonar."
//                script {
//                    timeout(time: 3, unit: 'MINUTES') {
//                        def qg = waitForQualityGate()
//                        if (qg.status != 'OK') {
//                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
//                        }
//                    }
//                }
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
                sh "rm -rf build"
                sh "mkdir build \
                        && cd build \
                        && cp ../target/my-app-*.jar app.jar \
                        && cp ../Dockerfile . \
                        && docker build . -t cicd-simple-app"
            }
        }       

       stage('Deploy') 
        { 
            steps 
            {
                echo "Pasos para desplegar version"
                sh "docker stop cicd-simple-app || true"
                sh "docker rm cicd-simple-app || true"
                sh "docker run --name cicd-simple-app -d cicd-simple-app"
            }
        }   
    }    
}
