pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './build.sh'
            }
        }
    }
    post {
        always {
            sh 'echo "pipeline complete"'
        }
    }
}