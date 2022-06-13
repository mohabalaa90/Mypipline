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
    stage('increment Version'){
      steps{
        script{
          gv.incrementVeresion()
        }
      }
    }    
    stage('build jar'){
     /* input{
            message "approve to execute or not "
            ok "yes"
            parameters{
              booleanParam(name:'execute', defaultValue:true , description:'wait for approval')
             }
            }*/
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
          gv.buildImage 'myImage'
          }
        }
      }
    /*stage('commit Update'){
      steps{
        script{
          gv.commitVersionUpdate()
          }
        }
      }*/
   } 
  post{
      success{
        script{
          gv.commitVersionUpdate()
          }
      }
    }
}
