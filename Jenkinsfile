@Library('pytest_lib@main') _

pipeline {
    agent any

    environment {
        PYTEST_HTML_REPORT = 'report.html'
        branch_name = 'main'
        repo_url = 'https://github.com/snaatak-Zero-Downtime-Crew/attendance-api.git'
        email_recipients = "rohit.chopra@mygurukulam.co"
        workspace = "/var/lib/jenkins/workspace/pyhtondeclareunit"
        SLACK_CHANNEL = 'jenkinsnotify'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWorkspace()
            }
        }

        stage('Clone Repository') {
            steps {
                script {
                    cloneRepository(repo_url, branch_name, 'gitR')
                }
            }
        }

        stage('Setup') {
            steps {
                setupEnvironment()
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    runTests(PYTEST_HTML_REPORT)
                }
            }
        }

       stage('Notify') {
            steps {
                script {
                    notify(env.JOB_NAME, currentBuild.number.toString(), env.BUILD_URL)
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
            archiveArtifacts artifacts: "${PYTEST_HTML_REPORT}", allowEmptyArchive: true
        }

 success {
            echo "✅ Build was successful!"
        }

        failure {
            echo "❌ Build failed!"
        }
}
    } 
