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
                   sh 'mvn clean package sonar:sonar'
                }

            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }
            
    }
}