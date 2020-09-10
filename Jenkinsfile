//in progress

pipeline {
  agent any
		
	tools {
	  git 'Default'
	  jdk 'jdk8'
	  maven 'Maven-3.6.0'
	  allure 'allure-2.6.0'
	}

	
	
  stages {
	  
	  	stage ('GreenCityTest - clean') {
	    steps {
 			sh "mvn clean"
        }
    }

	stage ('GreenCityTest - install') {
	    steps {
 			sh "mvn install -Dmaven.test.skip=true"
        }
    }
	  
	stage ('GreenCityTest - compile') {
	    steps {
 			sh "mvn test-compile"
        }
    }
	
	
	  
	stage ('Tests') {
	    steps {
		sh "mvn test -Dtestng.xml=testng.xml"
		}		
	    }
	  


	 
	  
    stage('Generate allure report') {
        steps {
            allure([
            includeProperties: false,
            jdk: 'jdk8',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'target/allure-results']]
                ])			     
        }
    }
	  


  }
		
}
