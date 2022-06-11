def buildJar(){
  echo "building jar file............... "
  sh 'mvn clean'
  sh 'mvn package'
}

def clearOldImages(){
  echo "clearing Old Images ..............."
  sh "docker images -a | grep mohab98/mohab* | awk '{print $3}' | xargs docker rmi -f "
}

def buildImage(){
  echo "building image..............."
  withCredentials([usernamePassword(credentialsId:'dockerHub-Credentials' , passwordVariable:'PASS' , usernameVariable:'USER')]){
    sh "docker build -t mohab98/mohab:MyPl$BUILD_NUMBER ."
    sh "echo $PASS | docker login -u $USER --password-stdin"
    //sh "docker push mohab98/mohab:MyPl$BUILD_NUMBER"
  }
}
return this
  
