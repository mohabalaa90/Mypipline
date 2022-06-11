def myGv
pipeline{
  agent any 
  tools{
    maven 'maven-3.8.5'
  }
  stages{
    stage('init'){
      steps{
        script{
          myGv = load "myscript.groovy"
        }
      }
    }
    stage('build jar'){
      steps{
        script{
          myGv.buildJar()  
        }
      }
    }
    stage('build image'){
      steps{
        script{
          myGV.buildImage()
          }
        }
      }
    } 
  }
