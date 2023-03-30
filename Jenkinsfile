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
        sh 'mvn clean verify sonar:sonar \
  -Dsonar.projectKey=devops-pipeline-jenkins \
  -Dsonar.host.url=https://sonar-devops.apps.cluster-28w9t.28w9t.sandbox1267.opentlc.com \
  -Dsonar.login=sqp_8712c9f5876bc62b4044894fba5fcdf5b58f409as'
      }
    }
    stage ('Build') {
      steps {
        sh 'mvn clean install'
      }
    }
    /*stage('Push image') {
      steps {
        docker.withRegistry('https://quay.io', 'quay-credentials') {
          app.push("${env.BUILD_NUMBER}")
        }
      }
    }*/
    stage('Deploy') {
			agent { label 'master' }
			steps {
				script {
					withDockerRegistry([credentialsId: 'quay-io-bot', url: 'https://quay.io']) {
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
