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
                    echo "Checking Java version..."
                    java -version

                    echo "Making Maven wrapper executable..."
                    chmod +x mvnw

                    echo "Building Spring Boot application..."
                    ./mvnw clean package -DskipTests
                '''
            }
        }


        stage('Build Docker Image') {
            steps {
                sh """
                    echo "Building Docker image..."
                    docker build -t ${APP_NAME}:${IMAGE_TAG} .
                """
            }
        }


        stage('Update Kubernetes Deployment') {
            steps {
                sh """
                    echo "Updating Kubernetes deployment..."

                    kubectl set image deployment/spring-kube-deployment \
                    spring-kube=${APP_NAME}:${IMAGE_TAG}
                """
            }
        }


        stage('Verify Kubernetes Deployment') {
            steps {
                sh """
                    echo "Waiting for rollout..."

                    kubectl rollout status deployment/spring-kube-deployment

                    echo "Current pods:"
                    kubectl get pods
                """
            }
        }

    }


    post {

        success {
            echo "================================="
            echo "Deployment Successful"
            echo "Image: ${APP_NAME}:${IMAGE_TAG}"
            echo "================================="
        }


        failure {
            echo "================================="
            echo "Deployment Failed"
            echo "Check console logs"
            echo "================================="
        }
    }
}