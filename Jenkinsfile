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
    }    
}
