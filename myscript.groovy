def incrementVeresion(){
  echo "incrementing the version....."
  sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
  def matcher = readFile('pom.xml')=~ '<version>(.+)</version>'
  def version = matcher[0][1]
  env.IMAGE_NAME="$version-$BUILD_NUMBER"
}

def buildJar(){
  echo "building jar file............... "
  sh 'mvn clean'
  sh 'mvn package'
}

def clearOldImages(){
  echo "clearing Old Images ..............."
  sh '''if docker images -a  | grep "mohab98/mohab*" | awk \'{print $3}\' | xargs docker rmi -f ;
  then 
    printf "clearing succsseded"
  else 
    printf "no images to clear"
    fi'''
}

def buildImage(String imgN){
  echo "building image..............."
  withCredentials([usernamePassword(credentialsId:'dockerHub-Credentials' , passwordVariable:'PASS' , usernameVariable:'USER')]){
    sh "docker build -t mohab98/mohab:$imgN${IMAGE_NAME} ."
    sh "echo $PASS | docker login -u $USER --password-stdin"
    //sh "docker push mohab98/mohab:$imgN${IMAGE_NAME}"
  }
}

def commitVersionUpdate(){
  echo "commit the new pom file...."
  withCredentials([usernamePassword(credentialsId:'git-Credentials' , passwordVariable:'PASS' , usernameVariable:'USER')]){
    
    sh 'git config --global user.email "jenkinsServer@local.com"'
    sh 'git config --global user.name "JenkinsServer"'
    
    sh 'git status'
    sh 'git branch'
    sh 'git config --list'
	
    sh "git remote set-url origin https://github.com/mohabalaa90/Mypipline.git"
    sh 'git add .'
    sh 'git commit -m "update "'
    sh 'git push origin HEAD:master'
  }
}

return this
  
