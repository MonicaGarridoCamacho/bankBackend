pipeline{
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            label: jenkins-build
        spec:
          serviceAccountName: nonrootbuilder-sa
          containers:
          - name: podman-builder
            image: podman-builder:latest
            command:
              - cat
            tty: true
            env:
              - name: HOME
                value: /home/jenkins/agent'''
    }
  }
  environment {
    IMAGE_NAME              = 'podman-build'
    ARTIFACTORY_ROOT        = "artifactory.domain.com/builds"
    BUILD_IMAGE             = "podman-build/${IMAGE_NAME}"
    BUILD_TAG_MINOR         = "b${BUILD_NUMBER}-${GIT_COMMIT_SHORT}"
    BUILD_IMAGE_FULL        = "${ARTIFACTORY_ROOT}/${BUILD_IMAGE}:${BUILD_TAG_MINOR}"
    GIT_COMMIT_SHORT        = """${ sh( returnStdout: true, script:'git log --format="%h" -n 1').trim() }"""
    SOURCE_CODE_ROOT        = "podman-build"
  }
  stages{
    stage('Build Docker Image') {
      steps {
        container('podman-builder'){
          dir("${SOURCE_CODE_ROOT}"){
            sh 'podman info'
            sh 'podman build -f Dockerfile -t ${BUILD_IMAGE_FULL} --log-level=debug --format=docker .'
            sh 'podman images'
          }
        }
      }
    }
  } // stages
} // pipeline