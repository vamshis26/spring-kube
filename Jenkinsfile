pipeline {

    agent any

    environment {
        APP_NAME = "spring-kube"
        IMAGE_TAG = "${new Date().format('yyyyMMdd')}-${BUILD_NUMBER}"
        PATH = "/opt/homebrew/bin:/usr/local/bin:$PATH"
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


        stage('Check Docker') {
            steps {
                sh '''
                    echo "Checking Docker..."
                    which docker
                    docker --version
                '''
            }
        }


        stage('Build Docker Image') {
            steps {
                sh """
                    echo "Building Docker image..."

                    docker build \
                    -t ${APP_NAME}:${IMAGE_TAG} .

                    echo "Docker image created:"
                    docker images | grep ${APP_NAME}
                """
            }
        }


        stage('Update Kubernetes Deployment') {
            steps {
                sh """
                    echo "Updating Kubernetes image..."

                    kubectl set image deployment/spring-kube-deployment \
                    spring-kube=${APP_NAME}:${IMAGE_TAG}
                """
            }
        }


        stage('Verify Kubernetes Deployment') {
            steps {
                sh """
                    echo "Checking rollout..."

                    kubectl rollout status deployment/spring-kube-deployment

                    echo "Current pods:"
                    kubectl get pods
                """
            }
        }

    }


    post {

        success {
            echo """
            =====================================
            Deployment Successful
            Image: ${APP_NAME}:${IMAGE_TAG}
            =====================================
            """
        }


        failure {
            echo """
            =====================================
            Deployment Failed
            Check Console Output
            =====================================
            """
        }
    }
}