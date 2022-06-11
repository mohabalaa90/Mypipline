pipeline{
  agent any 
  tools{
    maven 'maven-3.8.5'
  }
  stages{
    stage('build jar'){
      steps{
        script{
          echo "building jar file............... "
          sh 'mvn package'
        }
      }
    }
    stage('build image'){
      steps{
        script{
          echo "building image..............."
          withCredentials([usernamePassword(credentialsId:'dockerHub-Credentials' , passwordVariable:'PASS' , usernameVariable:'USER')]){
            sh "docker build -t mohab98/mohab:MyPl$BUILD_NUMBER ."
            sh "echo $PASS | docker login -u $USER --password-stdin"
            sh "docker push mohab98/mohab:MyPl$BUILD_NUMBER"
          }
        }
      }
    } 
  }
}
