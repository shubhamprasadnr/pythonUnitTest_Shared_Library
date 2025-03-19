def call(String jobName, String buildNumber, String buildUrl) {

    def SLACK_CHANNEL = '#jenkinsnotify'
    def EMAIL_RECIPIENTS = 'rohit.chopra@mygurukulam.co'

    try {
        echo "Hello from Jenkins Library!"

        echo "✅ SUCCESS: Job '${jobName} [${buildNumber}]' completed successfully! ${buildUrl}"
        
        // Slack Success Notification
        slackSend (
            channel: SLACK_CHANNEL,
            color: 'good',
            message: "✅ SUCCESS: Job '${jobName} [${buildNumber}]' completed successfully! <${buildUrl}|Details>"
        )
        
        // Email Success Notification
        mail (
            to: EMAIL_RECIPIENTS,
            subject: "SUCCESS: Jenkins Job '${jobName} [${buildNumber}]'",
            body: "Good news! The Jenkins job '${jobName} [${buildNumber}]' completed successfully.\nCheck details here: ${buildUrl}"
        )

    } catch (err) {
        echo "❌ FAILURE: Job '${jobName} [${buildNumber}]' failed! ${buildUrl}"

        // Slack Failure Notification
        slackSend (
            channel: SLACK_CHANNEL,
            color: 'danger',
            message: "❌ FAILURE: Job '${jobName} [${buildNumber}]' failed! <${buildUrl}|Check logs>"
        )

        // Email Failure Notification
        mail (
            to: EMAIL_RECIPIENTS,
            subject: "FAILURE: Jenkins Job '${jobName} [${buildNumber}]'",
            body: "Alert! The Jenkins job '${jobName} [${buildNumber}]' has FAILED.\nCheck logs here: ${buildUrl}"
        )

        // Fail the build explicitly
        error "Build failed due to error: ${err}"
    }
}
