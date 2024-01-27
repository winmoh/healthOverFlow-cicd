pipeline{
    agent any
    stages{
        stage('starting postgres databse container'){
            steps{
                sh  "docker start bb2cb28b729a"

            }
        }
        stage('cleaning the app'){
            steps{
                echo 'cleaning the app and generating the jar file';
                sh 'mvn  clean '
            }

        }
        stage('testing the app -unit tests-'){
                    steps{
                        sh 'mvn test'
                    }
        }
        stage('building the app'){
                    steps{
                        echo 'building the app and generating the jar file';
                        sh 'mvn  package '
                    }

         }



        stage('code analysis using sonarQube code coverage'){

            steps{
                withSonarQubeEnv('sonarQube') {
                   sh "mvn clean  verify sonar:sonar -Dsonar.projectKey=cicd-Health-OverFlow -Dsonar.projectName='cicd Health OverFlow' -Dsonar.host.url=http://192.168.0.194:9000 -Dsonar.token=sqp_4cf5b086f7ad7657d43c93d2aa98ad77bd1bccb4"
                }

            }
        }

            
    }
}