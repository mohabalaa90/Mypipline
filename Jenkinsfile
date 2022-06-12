def gv
pipeline{
  agent any 
  tools{
    maven 'maven-3.8.5'
  }
  stages{
    stage('init'){
      steps{
        script{
          gv = load "myscript.groovy"
        }
      }
    }
    stage('build jar'){
      steps{
        script{
          gv.buildJar()  
        }
      }
    }
    stage('clear images'){
      steps{
        script{
          gv.clearOldImages()  
        }
      }
    }
    stage('build image'){
      steps{
        script{
          gv.buildImage "testparam"
          }
        }
      }
    } 
  }
