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
  -Dsonar.host.url=https://sonar-devops.apps.cluster-28w9t.28w9t.sandbox1267.opentlc.com \
  -Dsonar.login=sqp_159782df0f09ed97cde39200b2f79815689a78a6'
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
