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
  -Dsonar.projectKey=maven-jerkins-pipeline \
  -Dsonar.host.url=https://sonar-devops.apps.cluster-9ddp2.9ddp2.sandbox2472.opentlc.com \
  -Dsonar.login=sqp_b7d6d6b781287d31f8e4fcf6eb8027b6d5bca170'
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
