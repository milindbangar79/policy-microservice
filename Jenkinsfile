/* Secrets Configuration */
scmcredId = 'Bitbucket'
nexuscredId = 'Nexus'
jiracredId = 'JIRA'

/* Application Constants */
sonarQubeURL='http://34.205.155.82:8090'
bbprotocol='https'
bbURL='bitbucket.org/darwinninja/policyservice.git'
devops_repo=""
relbranch_devops="master"
relbranch_config="master"
to_emailid='experiencedigitial@gmail.com'
def organization="Pearl"
def environment="Dev"
def applicationname="PolicyServiceMicroService"
def relbranch_config="master"

node (){
   def VERSION
   def mvnHome = tool 'M3'
   def javaHome = tool 'jdk'

stage 'Checkout'
   deleteDir() 
   try {
       checkout scm
       /*checkoutscm()*/
       
       sh "git rev-parse --short HEAD > .git/commit-id"                        
       VERSION = readFile('.git/commit-id').trim()
       
       currentBuild.displayName = "#${env.BUILD_NUMBER}-ORG:${organization}-ENV:${environment}-APPNAME:${applicationname}-BRANCH:${relbranch_config}"
       
       currentBuild.result='SUCCESS' 
       
   } catch (e) {
      currentBuild.result = "FAILED"
      sendMail( 'FAILED' )
      throw e
   }

stage 'Build application and Run Unit Test'
   try {
      
      if (isUnix()){
         sh "JAVA_HOME=$javaHome ${mvnHome}/bin/mvn -Dmaven.test.failure.ignore clean package"
      } else {
         bat("${mvnHome}/bin/mvn -Dmaven.test.failure.ignore clean package")
      }
   } catch (e) {
      currentBuild.result = "FAILED"
      sendMail( 'FAILED' )
      throw e
   }
   
   stage('Results') {
      if (isUnix()){
         sh "ls -l ./target/surefire-reports"
      }
      /*junit './target/surefire-reports/TEST-*.xml'*/
      archive 'target/*.jar'
     }
   
   stage 'Run SonarQube Analysis'
   try {
     if (isUnix()) { 
            sh "JAVA_HOME=$javaHome ${mvnHome}/bin/mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test"
            
        withSonarQubeEnv('Sonar') {
            sh "JAVA_HOME=$javaHome ${mvnHome}/bin/mvn package sonar:sonar"
       }
       
     }else{
         /*bat("${mvnHome}/bin/mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test")*/
         bat("${mvnHome}/bin/mvn package sonar:sonar -Dsonar.host.url='${sonarQubeURL}' -Dsonar.login=admin -Dsonar.password=admin")
     }
   } catch (e){
      currentBuild.result="FAILED"
      sendMail( 'FAILED' )
      throw e
   }

   /*input "Does '${sonarQubeURL}'/dashboard/index/jenkins-docker-plugin look good?"*/

   /*stage 'Deploy to Nexus'
   try {
      if(isUnix){
         sh "JAVA_HOME=$javaHome ${mvnHome}/bin/mvn clean deploy"
      } else{
         bat("${mvnHome}/bin/mvn clean deploy")
      }
    } catch(e){
      currentBuild.Result="FAILED"
      sendEmail( 'FAILED' )
      throw e
   } */
   
 stage('Build and Publish Image') {
     docker.withRegistry('https://index.docker.io/v1/', 'Bitbucket') {
       def app = docker.build("experiencedevops/policyservice:${VERSION}", '.').push()
     }
  }
   
   /*sendMail ( 'SUCCESS' )*/
   
}

stage("SonarQube Quality Gate") {
    timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
    def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
    if (qg.status != 'OK') {
      error "Pipeline aborted due to quality gate failure: ${qg.status}"
    }
  }
}
    
def checkoutscm() {      
   withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "${scmcredId}", usernameVariable: 'bb_userid', passwordVariable: 'bb_password']]) {    
      dir ("${WORKSPACE}") {  	  	      
              git credentialsId: "${scmcredId}", poll: false, url: "${bbprotocol}://${env.bb_userid}:${env.bb_password}@${bbURL}", branch: "${relbranch_config}"                       
       }   
   }
}

def sendMail( Status ) {        
    if ( "${Status}" == 'SUCCESS' || "${Status}" == 'UNSTABLE' )
    {        
        /*emailbody = readFile 'builddesc.txt'*/  
        emailbody   = 'Package Build Successful'
        currentBuild.result = "${Status}"
    }  
    else if ( "${Status}" == 'FAILED' )
    {
        emailbody = 'Deployment Failed !!! Please check attached logs.'        
        currentBuild.result = 'FAILED'
    }
    emailext attachLog: true, body: "${emailbody}", compressLog: true, subject: "Build #${env.BUILD_NUMBER} - Deployment ${Status}.", to: "${to_emailid}"
}