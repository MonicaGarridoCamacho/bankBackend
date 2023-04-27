pipeline {
  environment {
    registry = "anishnath/mkdocs"
    registryCredential = 'docker-creds'
    dockerImage = ''
  }
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
  -Dsonar.projectKey=maven-jenkins-pipeline \
  -Dsonar.host.url=https://sonarqube-client-mgaa-devops-namespace.apps.ocpclientprod1.navan.accenture.com \
  -Dsonar.login=sqp_3d2d679a81811eab3680c76764a80f9191f12735'
      }
    }*/
    stage ('Container registry') {
      steps {
        sh 'docker pull registry-quay-clientprod.apps.ocpmgmt.navan.accenture.com/client_mgaa/bank_example'
      }
    }
    stage ('Build') {
      steps {
        sh 'mvn clean install'
      }
    }
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
