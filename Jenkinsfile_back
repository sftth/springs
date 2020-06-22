pipeline {
	agent any
	
	stages {
		stage('Init') {
			steps {
				echo "===================Testing..."
			}
		}
		
		stage('Build') {
			steps {
				echo '===================Building...'
				
				sh 'mvn clean package'
			}
			post {
				success {
					echo '===================Now Archiving...'
					archiveArtifacts artifacts: '**/target/*.war'
				}
			}
		}
		stage('Deploy to Staging') {
			steps {
				echo '===================Code deployed.'
				build job: 'deploy-to-tomcat'
			}
		}
		stage('Deploy to Production') {
			steps {
				timeout(time:5, unit:'DAYS') {
					input message:'Approve PRDUCTION Deployment?'
				}
				
				build job: 'deploy-to-prod'
			}
			post {
				success {
					echo '===================Code deployed to Production.'
				}
				
				failure {
					echo '===================Deployment failed.'
				}
			}
		}
	}
}