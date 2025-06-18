pipeline {
    agent any

    environment {
        GCP_PROJECT_ID = 'your-gcp-project-id'
        GOOGLE_APPLICATION_CREDENTIALS = credentials('gcp-service-account-key') // Add in Jenkins later
    }

    tools {
        maven 'Maven 3.8.6' // Configure Maven in Jenkins > Global Tool Config
        nodejs 'NodeJS 16'  // Configure Node.js in Jenkins
    }

    stages {
        stage('Clone Repo') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/your/repo.git', branch: 'main'
            }
        }

        stage('Build Backend') {
            dir('backend') {
                steps {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test Backend') {
            dir('backend') {
                steps {
                    sh 'mvn test'
                }
            }
        }

        stage('Build Frontend') {
            dir('frontend') {
                steps {
                    sh 'npm install'
                    sh 'npm run build -- --prod'
                }
            }
        }

        stage('Deploy Backend to GCP') {
            steps {
                sh '''
                gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS
                gcloud config set project $GCP_PROJECT_ID
                gcloud app deploy backend/target/*.jar --quiet
                '''
            }
        }

        stage('Deploy Frontend to GCP') {
            steps {
                sh '''
                gsutil -m rsync -r frontend/dist gs://your-gcp-bucket-name
                gsutil web set -m index.html -e 404.html gs://your-gcp-bucket-name
                '''
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
