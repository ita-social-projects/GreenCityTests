timestamps {

node () {

	stage ('GreenCityTest - Checkout') {
 	 checkout([$class: 'GitSCM', branches: [[name: '*/newFeatures']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '970bd920-79cc-4c2a-87d1-3bce282ffcd5', url: 'https://github.com/ita-social-projects/GreenCityTests']]])
	}
	stage ('GreenCityTest - Build') {

withEnv(["JAVA_HOME=${ tool '"+JDK+"' }", "PATH=${env.JAVA_HOME}/bin"]) {
		// Maven build step
	withMaven(jdk: '', maven: 'Maven-3.6.0') {
 			if(isUnix()) {
 				sh "mvn install -Dmaven.test.skip=true "
			} else {
 				bat "mvn install -Dmaven.test.skip=true "
			}
 		}		// Shell build step
sh """
mvn test
 """
	}
}

    stage('reports') {
        steps {
        script {
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
}