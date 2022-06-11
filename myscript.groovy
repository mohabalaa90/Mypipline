def buildJar(){
  echo "building jar file............... "
  sh 'mvn package'
}
def buildImage(){
 echo "building image..............."
 withCredentials([usernamePassword(credentialsId:'dockerHub-Credentials' , passwordVariable:'PASS' , usernameVariable:'USER')]){
  sh "docker build -t mohab98/mohab:MyPl$BUILD_NUMBER ."
  sh "echo $PASS | docker login -u $USER --password-stdin"
  sh "docker push mohab98/mohab:MyPl$BUILD_NUMBER"
 }
}
return this
  
