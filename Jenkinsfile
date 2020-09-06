pipeline {
  agent any

  stages {

	stage ('GreenCityTest - Build') {
	    steps {
 			sh "mvn install -Dmaven.test.skip=true "
        }
    }

	stage ('Tests') {
	    steps {
        	sh "mvn test"
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

  }
}