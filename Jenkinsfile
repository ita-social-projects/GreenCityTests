pipeline {
    agent any
stages {
    stage('compile') {
        steps {
            bat 'mvn --batch-mode test-compile'
            }
        }
stage('clean') {
        steps {
            bat 'mvn --batch-mode clean'
            }
        }
stage('test') {
        steps {
            bat 'mvn --batch-mode test'
            }
        }
stage('allure') {
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
stage('sonar') {
        steps {
            bat 'mvn --batch-mode sonar:sonar -Dsonar.sources=src -Dsonar.test.inclusions=src/test/java/**'
            }
        }
    }
}
