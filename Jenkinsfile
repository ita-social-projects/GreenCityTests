pipeline {
  agent any
   options {
        copyArtifactPermission('GCLocalPipeLine');
    }

  stages {

    stage('Compile') {
        steps {
            sh 'mvn --batch-mode test-compile'
        }
    }

    stage('Clean before install') {
        steps {
            sh 'mvn --batch-mode clean'
        }
    }

    stage('Install') {
        steps {
            sh 'mvn --batch-mode install -Dmaven.test.skip=true'
        }
    }

    stage('Clean before tests') {
        steps {
            sh 'mvn --batch-mode clean'
        }
    }

    stage('Run tests') {
        steps {
            sh 'mvn --batch-mode test -Dtestng.xml=testng.xml'
        }
    }

    stage('Generate allure report') {
        steps {
            allure([
            includeProperties: false,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'target/allure-results']]
                ])
        }
    }

    stage('HTML report') {
        steps {
            publishHTML (target : [allowMissing: false,
             alwaysLinkToLastBuild: true,
             keepAll: true,
             reportDir: 'target/surefire-reports/test-output',
             reportFiles: 'emailable-report.html',
             reportName: 'short report',
             reportTitles: 'Report'])
        }
    }

  }

      post {
        always {
            archiveArtifacts artifacts: 'TestLogs.log', onlyIfSuccessful: true

            echo 'I will always say Hello again!'

            emailext attachLog: true, attachmentsPattern: 'generatedFile.txt',
                body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                recipientProviders: [developers(), requestor()],
                subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"

        }
    }
}