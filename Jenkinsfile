pipeline{
    agent any
    stages{
        stage('building the app'){
            steps{
                echo 'building the app and generating the jar file';
                sh 'mvn clean install'
            }

        }
        stage('testing the app -unit tests-'){
            steps{
                sh 'mvn test'
            }
        }


        stage('code analysis using sonarQube code coverage'){

            steps{
                withSonarQubeEnv('sonarQube') {
                   sh "mvn clean verify sonar:sonar -Dsonar.projectKey=cicd-Health-OverFlow -Dsonar.projectName='cicd Health OverFlow' -Dsonar.host.url=http://100.91.179.164:9000 -Dsonar.token=sqp_4cf5b086f7ad7657d43c93d2aa98ad77bd1bccb4"
                }

            }
        }

            
    }
}