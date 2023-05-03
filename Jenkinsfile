pipeline {
  agent {
         node {
           label 'maven'
         }
  }
  stages {
    stage ('Code repo & code review') {
      steps {
        echo 'Code repo & code review'
      }
    }
    stage ('Static code analysis') {
      steps {
     	 sh 'mvn sonar:sonar \
  -Dsonar.projectKey=maven-jerkins-pipeline \
  -Dsonar.host.url=https://sonarqube-bank-infra.apps.cluster-d7qxw.d7qxw.sandbox2290.opentlc.com \
  -Dsonar.login=271450aef7793b127994c55634ec10a4b7186c68'
      }
    }
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
