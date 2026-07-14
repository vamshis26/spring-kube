pipeline {

    agent any

    environment {
        APP_NAME = "spring-kube"
        IMAGE_TAG = "${new Date().format('yyyyMMdd')}-${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/vamshis26/spring-kube.git'
            }
        }

        stage('Build Spring Boot Application') {
            steps {
                sh '''
                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh """
                    docker build -t ${APP_NAME}:${IMAGE_TAG} .
                """
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh """
                    kubectl set image deployment/spring-kube-deployment \
                    spring-kube=${APP_NAME}:${IMAGE_TAG}
                """
            }
        }

        stage('Verify Deployment') {
            steps {
                sh """
                    kubectl rollout status deployment/spring-kube-deployment
                    kubectl get pods
                """
            }
        }
    }

    post {

        success {
            echo "Deployment successful: ${APP_NAME}:${IMAGE_TAG}"
        }

        failure {
            echo "Deployment failed"
        }
    }
}