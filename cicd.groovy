node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/wharfport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/wharfport.git'), string(name: 'PORT_DESCRIPTION', value: 'A porting tool for Go packages on z/OS' ), string(name: 'NODE_LABEL', value: "v3r1")]
  }
}
