def deploy_on_environment = "NoEnvironment"
def project_version = params.Version
def timestamp = new Date().format("yyyyMMddHHmmssSS")
def name_image_docker = "NoName"

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
            when (BRANCH_NAME != '00-cd-enagas') {
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
                            -Dsonar.token=sqp_abed0b140c50527b7fe01f83224e8c1742528264"
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
                sh 'rm -rf target/*.jar && mvn -s .mvn/settings.xml deploy -DskipTests' 
            }
        }       
        stage('Build image') 
        { 
            steps 
            {
                script{
                    if (Branch_name.equals("00-ci-enagas")) { name_image_docker = "ci-simple-app" }
                    else if (Branch_name.equals("00-cd-enagas")) { name_image_docker = "cd-simple-app" }
                    else if (Branch_name.equals("00-cicd-enagas")) { name_image_docker = "cicd-simple-app" }
                    sh "rm -rf build"
                    sh "mkdir build \
                            && cd build \
                            && cp ../target/my-app-*.jar app.jar \
                            && cp ../Dockerfile . \
                            && docker build . -t ${name_image_docker}"
                }
            }
        }       

       stage('Deploy') 
        { 
            steps 
            {
                script{
                    if (Entorno.equals("DES")) { deploy_on_environment = "development_env" }
                    else if (Entorno.equals("PRE")) { deploy_on_environment = "preproduction_env" }          
                    else if (Entorno.equals("CICD")) { deploy_on_environment = "cicd-full_env" }
                    echo "Pasos para desplegar version"
                    sh "docker stop ${deploy_on_environment} || true"
                    sh "docker rm ${deploy_on_environment} || true"
                    sh "docker run --name ${deploy_on_environment} -d ${name_image_docker}"
                }
            }
        }   
    }    
}
