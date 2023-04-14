pipeline {
  agent any
  stages {
    stage ('Code repo & code review') {
      steps {
        echo 'Code repo & code review'
      }
    }
    /*stage ('Static code analysis') {
      steps {
     	 sh 'mvn clean verify sonar:sonar \
  -Dsonar.projectKey=maven-jerkins-pipeline \
  -Dsonar.host.url=https://sonar-devops.apps.cluster-hwtmk.hwtmk.sandbox1148.opentlc.com \
  -Dsonar.login=sqp_ba61a7df04605f4c6114391e72807c9bb7a1ea28'
      }
    }*/
    stage ('Container registry') {
      steps {
        sh 'docker pull quay.io/monica_garrido/do288-hello-java'
      }
    }
    /*stage ('Build') {
      steps {
        sh 'mvn clean install'
      }
    }*/
    stage('Deploy') {
			agent { label 'master' }
			steps {
				script {
					withDockerRegistry([credentialsId: 'quay-credentials', url: 'https://quay.io']) {
	                    docker.build('$QUAY_REPO:$QUAY_REPO_TAG', '-f Dockerfile .').push('latest')
	                }
	                sh 'docker rmi -f $QUAY_REPO:$QUAY_REPO_TAG'
                }
			}
			post {
				always {
					deleteDir()
				}
			}
		}
	}
}
