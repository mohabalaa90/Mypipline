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
  }

}
