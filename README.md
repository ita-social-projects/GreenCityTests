# GreenCityTests
Automation tests for GreenCity project

### Required to install

* Java 8
* Google [Chrome](https://www.google.com/chrome/) Browser
* [maven](https://maven.apache.org/)

# Links
* [selenoid GUI](http://35.198.124.146:8080/#/)
* [selenoid driver connect](http://35.198.124.146:4444)
* [local driver connect](http://localhots:4444)
* [selenoid logs](http://35.198.124.146:4444/logs)
* [Jenkins](http://35.198.90.20:8080/)


# Environment                   
### Build
#### First run:
* `mvn install -Dmaven.test.skip=true` - install
* `mvn test-compile` - compile

#### Running UI tests
* `mvn test` - run tests, by default using pom maven setup
* `mvn test -Dtestng.xml=*.xml` to execute the automation UI tests via [maven](https://maven.apache.org/).

For example
* `mvn test -Dtestng.xml=testng.xml` to running all  UI tests 

#### Run localy:
* set in google sheet `remote FALSE`
#### Run remotely:
* set in google sheet `remote TRUE`

Value of `remote` credential  transferred to com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner, boolean remote = ValueProvider.remote();

# Credentials 
### Existing file
[Credentials](https://docs.google.com/spreadsheets/d/165DiBh-2TKxIHPtfBTDJ_GBq8kgal4Ac5vRlbaUC6O4/edit#gid=182144688)
,do what you wana do.

### New personal file
Set test variables, for example:
<details><summary>Steps</summary>
<p>

All user credentials storing at google sheets.
If you wana create personal file with credentials, you should :
* Create  [google sheet](https://docs.google.com/spreadsheets)
* [Share with people and groups](https://dl.dropboxusercontent.com/s/oj3hbfyh9vqvwbf/shot_200908_003404.png)
* [Allow API](https://developers.google.com/sheets/api/quickstart/java) :
- [click](https://dl.dropboxusercontent.com/s/kc5hros4whxu33u/shot_200907_235946.png)
- [give name](https://dl.dropboxusercontent.com/s/fpb6g25hzvsx3cu/shot_200907_235645.png)
- [chose desktop app](https://dl.dropboxusercontent.com/s/usr5u1ikz9lc0u1/shot_200907_235712.png)
- [download configuration file](https://dl.dropboxusercontent.com/s/4rnaxhhej94oap1/shot_200907_235731.png)
- [file loocks like this](https://dl.dropboxusercontent.com/s/j833vocuvpodzbm/shot_200907_235901.png)
- replace information in `resources/sheetsApi.json`
* Add key to `com.softserve.edu.greencity.ui.tools.api.google.sheets.GoogleSheet`,
([Key looks like this](https://dl.dropboxusercontent.com/s/j3hzw7u8frlhpf8/shot_200908_000657.png))
*  `SPREADSHEET_ID = YOUR_KEY_FROM_GOOGLE_SHEET`

</p>
</details>

<details><summary>Example</summary>
<p>

```
Name of value	value
invalidPassDigit	12345678-
defaultName	Pavel
emailForRegistration	greencitypavel@gmail.com
defaultPass	1234qwerTY-
temporaryPass	Temp#001
invalidName	21CharString21CharSt
invalidPassLowercase	qwertyasdfg-
nameForRegistration	greencitypavel
invalidPass	as2f
invalidPassSpecChar	bRDYBhAs3 z48Y5H-
invalidPassSpace	                                         
invalidPassLength	aA-
comfTemporaryPass	1234qwerTY-
googleEmail	greencitypavel@gmail.com
googlePass	1234qwerTY-
invalidPassUppercase	QWERTYASDFG-
validIncorrectPassword	As3z48Y5H-bRDYBh
temporaryLoginName	xdknxusqvjeovowpfk@awdrt.com
defaultEmail	greencitypavel@gmail.com
invalidEmail	\ asd 
passwordForRegistration	1234qwerTY-
validUnregisterEmail	greencitypavel@gmail.com
remote	TRUE
```
</p>
</details>

# Options and capabilities example:

<details><summary>Options</summary>
<p>

```
com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner.setUpBeforeClass
ChromeOptions options = new ChromeOptions();
* options.addArguments("--disable-gpu");
* options.addArguments("--disable-popup-blocking");
* options.addArguments("--allow-failed-policy-fetch-for-test");
* options.addArguments("--disable-browser-side-navigation");
* options.addArguments("--incognito");
* options.addArguments("--disable-notifications");
* options.addArguments("--window-size=1920,1080", "--no-sandbox", "'--disable-dev-shm-usage");
```

</p>
</details>

<details><summary>Capabilities</summary>
<p>

```            
<=================================Common=================================>
com.softserve.edu.greencity.ui.tests.runner.DriverSetup.optionsArguments
DesiredCapabilities capabilities = DesiredCapabilities.chrome();
* capabilities.setBrowserName("chrome");
* capabilities.setVersion("84.0");
* capabilities.setCapability("enableVNC", true);
* capabilities.setCapability("enableVideo", false);
* capabilities.setCapability(ChromeOptions.CAPABILITY, options);
<=================================Common=================================>

<=================================Local=================================>
* GridHub.startLocally(4444);
* RegisterChrome.startNode(5551);
* RegisterChrome.startNode(5552);
* RegisterChrome.startNode(5553);
* RegisterChrome.startNode(5554);
* driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
<=================================Local=================================>

<=================================Remote=================================>
* driver = new RemoteWebDriver(
                    URI.create("http://35.198.124.146:4444/wd/hub").toURL(),
                    capabilities);
<=================================Remote=================================>
```

</p>
</details>

# Create your personal remote environment
### Google console
<details><summary>Steps</summary>
<p>
  
* Requirement: google account
* Go to [Google console](https://console.cloud.google.com/)
* Click [Activate](https://dl.dropboxusercontent.com/s/51d90wwa3rtaxpu/shot_200908_005037.png)
* Follow steps recommended in page to activate trial:  $300 credit to explore Google Cloud products, 3 month for now, check actual information when you start.

</p>
</details>

### Selenoid
3 Steps: 
- Create VM instances (virtual machine instance)
- Setup Docker
- Setup Selenoid

Lets go step by step.

#### 1 step VM instances
<details><summary>Steps</summary>
<p>

* [open menu](https://dl.dropboxusercontent.com/s/bhkp05a6yv3ol2a/shot_200908_005832.png)
* [go to vm instance page](https://dl.dropboxusercontent.com/s/bce5oi4jdymofo2/shot_200908_005907.png)
* [click create instance](https://dl.dropboxusercontent.com/s/u3e6hptkh890ws9/shot_200908_005933.png) (if you doesnt have any instances, you will see only one button in the middle of the page)
* [give name](https://dl.dropboxusercontent.com/s/au0bfcsmtwwykuy/shot_200908_010004.png) only lowercase
* [choose server](https://dl.dropboxusercontent.com/s/p46ntpy1fr62x6k/shot_200908_010040.png)
* [chose engine parameters](https://dl.dropboxusercontent.com/s/yjb72xi5myypldv/shot_200908_010058.png)
* [choose OS](https://dl.dropboxusercontent.com/s/lr0kt8x4xg2awuh/shot_200908_010123.png) we will work with ubuntu, but be free to experiment
* [set OS parameters](https://dl.dropboxusercontent.com/s/2m7eqn5w9vlrwk6/shot_200908_010213.png)
* [allow api, http and https](https://dl.dropboxusercontent.com/s/hy6mtmf2gyghszw/shot_200908_010333.png) actually you need only http, so up to you
* [open google sh console](https://dl.dropboxusercontent.com/s/xu2j9ulmx9889uo/shot_200908_031331.png)
* add firewall rules to allow use ports 8080, 4444 etc, !!!  be careful 20,22 etc used by google, so dont tuch it !!!
```$sh
gcloud compute firewall-rules create rule-allow-tcp-8080 --source-ranges 0.0.0.0/0 --target-tags allow-tcp-8080 --allow tcp:8080
gcloud compute firewall-rules create rule-allow-tcp-4444 --source-ranges 0.0.0.0/0 --target-tags allow-tcp-4444 --allow tcp:4444
```
* [open your VM](https://dl.dropboxusercontent.com/s/teonyajd3pu4lca/shot_200908_031546.png)
* [click Edit](https://dl.dropboxusercontent.com/s/eel40tef0k25d1k/shot_200908_031640.png)
* [Add firewall rules to your VM](https://dl.dropboxusercontent.com/s/zuva5goxygur5a0/shot_200908_031924.png)


</p>
</details>

#### 2 step Docker
<details><summary>Long version</summary>
<p>

* [docker official site](https://www.docker.com/)
* [intro](https://www.digitalocean.com/community/tutorials/the-docker-ecosystem-an-introduction-to-common-components) optional to read
* [go to vm instance page](https://dl.dropboxusercontent.com/s/bce5oi4jdymofo2/shot_200908_005907.png)
* [open console](https://dl.dropboxusercontent.com/s/zx6r3duwrm64gq7/shot_200908_011659.png)
* [you should see next awesome interface](https://dl.dropboxusercontent.com/s/ahp9osnisis2sin/shot_200908_012412.png)
* <b>follow steps bellow</b>
* <b>Install:</b>
* `sudo apt update`
* `sudo apt install apt-transport-https ca-certificates curl software-properties-common`
* `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`
* `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"`
* `sudo apt update`
* `apt-cache policy docker-ce`
* you should see:
```
docker-ce:
  Installed: (none)
  Candidate: 5:19.03.9~3-0~ubuntu-focal
  Version table:
     5:19.03.9~3-0~ubuntu-focal 500
        500 https://download.docker.com/linux/ubuntu focal/stable amd64 Packages
``` 
* `sudo apt install docker-ce`
* `sudo systemctl status docker`
* you should see:
```
● docker.service - Docker Application Container Engine
     Loaded: loaded (/lib/systemd/system/docker.service; enabled; vendor preset: enabled)
     Active: active (running) since Tue 2020-05-19 17:00:41 UTC; 17s ago
TriggeredBy: ● docker.socket
       Docs: https://docs.docker.com
   Main PID: 24321 (dockerd)
      Tasks: 8
     Memory: 46.4M
     CGroup: /system.slice/docker.service
             └─24321 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock
```
* press `CTRL+C`
* <b>Setup to avoid use sudo, optional:</b>
* `sudo usermod -aG docker ${USER}`
* `su - ${USER}`
* `id -nG`
* `sammy sudo docker`
* `sudo usermod -aG docker username`
* P.S. I skip this step, use sudo and doesn't have any problem with it, but all up to you.
* <b>How to use docker:</b>
* if you skip step before, use `sudo` before command
* `docker [option] [command] [arguments]`
* type `docker` you will see all options:
```
  attach      Attach local standard input, output, and error streams to a running container
  build       Build an image from a Dockerfile
  commit      Create a new image from a container's changes
  cp          Copy files/folders between a container and the local filesystem
  create      Create a new container
  diff        Inspect changes to files or directories on a container's filesystem
  events      Get real time events from the server
  exec        Run a command in a running container
  export      Export a container's filesystem as a tar archive
  history     Show the history of an image
  images      List images
  import      Import the contents from a tarball to create a filesystem image
  info        Display system-wide information
  inspect     Return low-level information on Docker objects
  kill        Kill one or more running containers
  load        Load an image from a tar archive or STDIN
  login       Log in to a Docker registry
  logout      Log out from a Docker registry
  logs        Fetch the logs of a container
  pause       Pause all processes within one or more containers
  port        List port mappings or a specific mapping for the container
  ps          List containers
  pull        Pull an image or a repository from a registry
  push        Push an image or a repository to a registry
  rename      Rename a container
  restart     Restart one or more containers
  rm          Remove one or more containers
  rmi         Remove one or more images
  run         Run a command in a new container
  save        Save one or more images to a tar archive (streamed to STDOUT by default)
  search      Search the Docker Hub for images
  start       Start one or more stopped containers
  stats       Display a live stream of container(s) resource usage statistics
  stop        Stop one or more running containers
  tag         Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
  top         Display the running processes of a container
  unpause     Unpause all processes within one or more containers
  update      Update configuration of one or more containers
  version     Show the Docker version information
  wait        Block until one or more containers stop, then print their exit codes
```
* `docker docker-subcommand --help`
* `docker info`
* `docker run hello-world`
* You will see:
```
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
0e03bdcc26d7: Pull complete
Digest: sha256:6a65f928fb91fcfbc963f7aa6d57c8eeb426ad9a20c7ee045538ef34847f44f1
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.
```
* `docker search ubuntu` you should see OFFICIAL OK
```
Output
NAME                                                      DESCRIPTION                                     STARS               OFFICIAL            AUTOMATED
ubuntu                                                    Ubuntu is a Debian-based Linux operating sys…   10908               [OK]
dorowu/ubuntu-desktop-lxde-vnc                            Docker image to provide HTML5 VNC interface …   428                                     [OK]
rastasheep/ubuntu-sshd                                    Dockerized SSH service, built on top of offi…   244                                     [OK]
consol/ubuntu-xfce-vnc                                    Ubuntu container with "headless" VNC session…   218                                     [OK]
ubuntu-upstart                                            Upstart is an event-based replacement for th…   108                 [OK]
ansible/ubuntu14.04-ansible                               Ubuntu 14.04 LTS with
```
* `docker pull ubuntu` you should see:
```
Using default tag: latest
latest: Pulling from library/ubuntu
d51af753c3d3: Pull complete
fc878cd0a91c: Pull complete
6154df8ff988: Pull complete
fee5db0ff82f: Pull complete
Digest: sha256:747d2dbbaaee995098c9792d99bd333c6783ce56150d1b11e333bbceed5c54d7
Status: Downloaded newer image for ubuntu:latest
docker.io/library/ubuntu:latest
```
* `docker images` display your images, you should see smth like thisL
```
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ubuntu              latest              1d622ef86b13        3 weeks ago         73.9MB
hello-world         latest              bf756fb1ae65        4 months ago        13.3kB
```
* `docker run -it ubuntu` run container
* you will see that your username changed, smth lie `root@d9b100f2f636:/#`. In container you should use commands without `sudo`
* you will need identifier `d9b100f2f636` after to run/stop etc container, just keep it in mind.
* `apt update`
* `apt install nodejs`
* `node -v`
Output
```
v10.19.0
```
* <b>Manege docker:</b>
* `docker ps` Output:
```
CONTAINER ID        IMAGE               COMMAND             CREATED             
```
* `docker ps -a`
```
greencitypavel@selenoid:~$ docker ps -a
CONTAINER ID        IMAGE                         COMMAND                  CREATED             STATUS                  PORTS                    NAMES
252134e6c31e        aerokube/selenoid-ui:1.10.0   "/selenoid-ui --sele…"   3 days ago          Up 3 days (healthy)     0.0.0.0:8080->8080/tcp   selenoid-ui
c0ff6120579d        aerokube/selenoid:1.10.0      "/usr/bin/selenoid -…"   3 days ago          Up 3 days               0.0.0.0:4444->4444/tcp   selenoid
8aade18cb61f        ubuntu                        "/bin/bash"              3 days ago          Up 3 days                                        gallant_bose
0e00502eb333        hello-world                   "/hello"                 3 days ago          Exited (0) 3 days ago                            affectionate_me
itner
```
* docker ps -l
```
greencitypavel@selenoid:~$ docker ps -l
CONTAINER ID        IMAGE                         COMMAND                  CREATED             STATUS                PORTS                    NAMES
252134e6c31e        aerokube/selenoid-ui:1.10.0   "/selenoid-ui --sele…"   3 days ago          Up 3 days (healthy)   0.0.0.0:8080->8080/tcp   selenoid-ui
```
* remember we talk that you should keep in mind
* `docker start d9b100f2f636`
* 'docker ps' - status
* `docker stop selenoid-ui` stop by NAME, check table above
* `docker stop 252134e6c31e` stop by ID, check table above

</p>
</details>

<details><summary>Short version</summary>
<p>

```
if smth went wrong just repeat without sudo
```
```sh
sudo apt update
sudo apt install apt-transport-https ca-certificates curl software-properties-common
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"
sudo apt update
sudo apt-cache policy docker-ce
sudo apt install docker-ce
sudo systemctl status docker
docker: Cannot connect to the Docker daemon. Is the docker daemon running on this host?. - its ok
docker [option] [command] [arguments] - base syntax
sudo docker
sudo docker docker-subcommand --help
sudo docker info
sudo docker run hello-world
sudo docker search ubuntu
sudo docker pull ubuntu
sudo docker images
sudo docker run -it ubuntu
root@d9b100f2f636:/#
apt update
apt install nodejs
node -v
docker ps
CTRL+C
docker ps -a
docker start 1c08a7a0d0e4
docker stop quizzical_mcnulty
docker rm youthful_curie
docker commit -m "What you did to the image" -a "Author Name" container_id repository/new_image_name
docker commit -m "added Node.js" -a "sammy" d9b100f2f636 sammy/ubuntu-nodejs
docker images
docker login -u docker-registry-username
docker tag sammy/ubuntu-nodejs docker-registry-username/ubuntu-nodejs
docker push docker-registry-username/docker-image-name
docker push sammy/ubuntu-nodejs
```


</p>
</details>

#### 3 step Selenoid
<details><summary>Steps</summary>
<p>

* `wget -O cm https://github.com/aerokube/cm/releases/download/1.7.2/cm_linux_amd64`
* $ `chmod +x cm`
* `curl -s https://aerokube.com/cm/bash | bash`
* $ `./cm selenoid start --vnc`
* $ `./cm selenoid-ui start`



</p>
</details>

### Jenkins

<details><summary>Install</summary>
<p>

* create VM as you do it before, don't forget allow ports
* open vm console
* install java 8:
```sh
sudo apt update
sudo apt search openjdk
sudo apt install openjdk-8-jdk
sudo apt install openjdk-8-jdk
java -version

readlink -f $(which java)
echo $JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
```
* install maven 
```sh
sudo apt update
sudo apt install maven
```
* install allure
```sh
curl -o allure-2.6.0.tgz -Ls https://dl.bintray.com/qameta/generic/io/qameta/allure/allure/2.
6.0/allure-2.6.0.tgz
sudo tar -zxvf allure-2.6.0.tgz -C /opt/
sudo ln -s /opt/allure-2.6.0/bin/allure /usr/bin/allure
allure --version
``` 
* install jenkins
```sh
wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb https://pkg.jenkins.io/debian binary/ > \
    /etc/apt/sources.list.d/jenkins.list'
sudo apt-get update
sudo apt-get install jenkins
```
* `sudo service jenkins status` - status, you should see smth like this:
```
● jenkins.service - LSB: Start Jenkins at boot time
   Loaded: loaded (/etc/init.d/jenkins; generated)
   Active: active (exited) since Sun 2020-09-06 01:35:43 UTC; 1 day 22h ag
     Docs: man:systemd-sysv-generator(8)
    Tasks: 0 (limit: 4671)
   CGroup: /system.slice/jenkins.service
Sep 06 01:35:38 jenkins systemd[1]: Starting LSB: Start Jenkins at boot ti
Sep 06 01:35:41 jenkins jenkins[1096]: Correct java version found
Sep 06 01:35:42 jenkins jenkins[1096]:  * Starting Jenkins Automation Serv
Sep 06 01:35:43 jenkins jenkins[1096]:    ...done.
Sep 06 01:35:43 jenkins systemd[1]: Started LSB: Start Jenkins at boot tim
```
* go to your [ip](https://dl.dropboxusercontent.com/s/zdxt7z3303y4125/shot_200908_033309.png):8080 
* from console copy initial password `sudo cat /var/lib/jenkins/secrets/initialAdminPassword` 
* finish registration

</p>
</details>

<details><summary>Jenkins usage</summary>
<p>

GUI

* Base setup yourIP:8080/configureTools/
* [JDK](https://dl.dropboxusercontent.com/s/y7l3u6zgg6jc4y8/shot_200908_034512.png)
* [MAVEN](https://dl.dropboxusercontent.com/s/tprzgiqj0czi998/shot_200908_034530.png)
* [Allure](https://dl.dropboxusercontent.com/s/qe36i2ny73fy6oo/shot_200908_034544.png)
* [Create job](https://dl.dropboxusercontent.com/s/6wy9avvqqar8aoj/shot_200908_033529.png) 1
* [Create job](https://dl.dropboxusercontent.com/s/ui2e8268zres63v/shot_200908_033612.png) 2
* [Git setup](https://dl.dropboxusercontent.com/s/82i9xrss7nc7vdp/shot_200908_033943.png)
* [Build](https://dl.dropboxusercontent.com/s/jmu1w8gk4xkdwsx/shot_200908_034238.png)
* [Allure report](https://dl.dropboxusercontent.com/s/4604au7qqilvgxa/shot_200908_034353.png)


Base pipeline
 
```
pipeline {
  agent any
  
  stages {

	stage ('GreenCityTest - Build') {
	    steps {
 			sh "mvn install -Dmaven.test.skip=true "
        }
    }

	stage ('Tests') {
	    steps {
        	sh "mvn test"
        }
    }
    
    stage('Generate allure report') {
        steps {
            allure([
            includeProperties: false,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'target/allure-results']]
                ])
        }
    }


    }
}

```

</p>
</details>

<details><summary>My plugins</summary>
<p>

There is alot useless, that i used just to check what i can do. So choose what you need. Git, mvn, allure, pipeline, sh plugin recommended to install.
```
Name  ↓    Version           Enabled   
ace-editor	1.1             	true
allure-jenkins-plugin	2.28.1	true
ansible	1.0                 	true
ant	1.11	                    true
antisamy-markup-formatter	2.1	true
apache-httpcomponents-client-4-api	4.5.10-2.0	true
artifactory	3.8.1	            true
authentication-tokens	1.4	true
blueocean	1.23.2	true
blueocean-autofavorite	1.2.4	true
blueocean-bitbucket-pipeline	1.23.2	true
blueocean-commons	1.23.2	true
blueocean-config	1.23.2	true
blueocean-core-js	1.23.2	true
blueocean-dashboard	1.23.2	true
blueocean-display-url	2.4.0	true
blueocean-events	1.23.2	true
blueocean-executor-info	1.23.2	true
blueocean-git-pipeline	1.23.2	true
blueocean-github-pipeline	1.23.2	true
blueocean-i18n	1.23.2	true
blueocean-jira	1.23.2	true
blueocean-jwt	1.23.2	true
blueocean-personalization	1.23.2	true
blueocean-pipeline-api-impl	1.23.2	true
blueocean-pipeline-editor	1.23.2	true
blueocean-pipeline-scm-api	1.23.2	true
blueocean-rest	1.23.2	true
blueocean-rest-impl	1.23.2	true
blueocean-web	1.23.2	true
bouncycastle-api	2.18	true
branch-api	2.6.0	true
build-name-setter	2.1.0	true
build-timeout	1.20	true
cloudbees-bitbucket-branch-source	2.9.2	true
cloudbees-credentials	3.3	true
cloudbees-folder	6.14	true
command-launcher	1.4	true
conditional-buildstep	1.3.6	true
config-file-provider	3.6.3	true
convert-to-pipeline	1.0	true
credentials	2.3.13	true
credentials-binding	1.23	true
dark-theme	0.0.7	true
dashboard-view	2.13	true
delivery-pipeline-plugin	1.4.2	true
display-url-api	2.3.3	true
docker-commons	1.17	true
docker-workflow	1.24	true
durable-task	1.35	true
ec2-deployment-dashboard	1.0.10	true
echarts-api	4.8.0-2	true
email-ext	2.75	true
environment-dashboard	1.1.7	true
external-monitor-job	1.7	true
extra-columns	1.22	true
favorite	2.3.2	true
git	4.4.1	true
git-client	3.4.2	true
git-parameter	0.9.13	true
git-server	1.9	true
github	1.31.0	true
github-api	1.116	true
github-branch-source	2.9.0	true
google-cloud-backup	0.6	true
google-cloudbuild	0.2.2 (2018-10-17T19:01:41Z)	true
google-compute-engine	4.3.2	true
google-deployment-manager	0.1	true
google-metadata-plugin	0.3.1	true
google-oauth-plugin	1.0.2	true
google-source-plugin	0.4	true
google-storage-plugin	1.5.2	true
gradle	1.36	true
greenballs	1.15	true
h2-api	1.4.199	true
handlebars	1.1.1	true
handy-uri-templates-2-api	2.1.8-1.0	true
htmlpublisher	1.23	true
ivy	2.1	true
jackson2-api	2.11.2	true
javadoc	1.6	true
jdk-tool	1.4	true
jenkins-design-language	1.23.2	true
jira	3.1.1	true
jquery	1.12.4-1	true
jquery-detached	1.2.1	true
jquery3-api	3.5.1-1	true
jsch	0.1.55.2	true
junit	1.34	true
keyboard-shortcuts-plugin	1.4	true
ldap	1.24	true
locale	1.4	true
lockable-resources	2.8	true
mailer	1.32	true
matrix-auth	2.6.2	true
matrix-project	1.17	true
maven-plugin	3.7	true
mercurial	2.10	true
momentjs	1.1.1	true
oauth-credentials	0.4	true
okhttp-api	3.14.9	true
pam-auth	1.6	true
parameterized-trigger	2.37	true
pipeline-aggregator-view	1.11	true
pipeline-build-step	2.13	true
pipeline-github	2.7	true
pipeline-github-lib	1.0	true
pipeline-graph-analysis	1.10	true
pipeline-input-step	2.12	true
pipeline-maven	3.9.2	true
pipeline-milestone-step	1.3.1	true
pipeline-model-api	1.7.2	true
pipeline-model-definition	1.7.2	true
pipeline-model-extensions	1.7.2	true
pipeline-multibranch-defaults	2.1	true
pipeline-npm	0.9.2	true
pipeline-rest-api	2.14	true
pipeline-stage-step	2.5	true
pipeline-stage-tags-metadata	1.7.2	true
pipeline-stage-view	2.14	true
pipeline-utility-steps	2.6.1	true
plain-credentials	1.7	true
plugin-util-api	1.2.5	true
project-build-times	1.2.1	true
project-stats-plugin	0.4	true
pubsub-light	1.13	true
resource-disposer	0.14	true
run-condition	1.3	true
scm-api	2.6.3	true
script-security	1.74	true
snakeyaml-api	1.26.4	true
sse-gateway	1.23	true
ssh	2.6.1	true
ssh-agent	1.20	true
ssh-credentials	1.18.1	true
ssh-slaves	1.31.2	true
ssh-steps	2.0.0	true
structs	1.20	true
theme-manager	0.5	true
timestamper	1.11.5	true
token-macro	2.12	true
trilead-api	1.0.10	true
variant	1.3	true
windows-slaves	1.6	true
workflow-aggregator	2.6	true
workflow-api	2.40	true
workflow-basic-steps	2.20	true
workflow-cps	2.83	true
workflow-cps-global-lib	2.17	true
workflow-durable-task-step	2.36	true
workflow-job	2.40	true
workflow-multibranch	2.22	true
workflow-remote-loader	1.5	true
workflow-scm-step	2.11	true
workflow-step-api	2.22	true
workflow-support	3.5	true
ws-cleanup	0.38	true
```
</p>
</details>

<details><summary>Useful links</summary>
<p>

* [Google Cloud](https://www.youtube.com/watch?v=5wEbNSrs098&list=PLg5SS_4L6LYs5IZZSY0viHRQFPa2P-R8H)
* [Jenkins](https://www.youtube.com/watch?v=cyb10iplv7U&list=PLg5SS_4L6LYvQbMrSuOjTL1HOiDhUE_5a)
* [Docker](https://www.digitalocean.com/community/tags/docker?subtype=tutorial)
* [Selenoid documentation](https://aerokube.com/selenoid/latest)
* [Java Quickstart google sheets](https://developers.google.com/sheets/api/quickstart/java)
* [Google console](http://cloud.google.com)
* [Allure-framework](https://habr.com/ru/company/sberbank/blog/358836)
* [webdrivermanager](https://github.com/bonigarcia/webdrivermanager)
* [TestNG - Quick Guide](https://www.tutorialspoint.com/testng/testng_quick_guide.htm)
* [functional interface](https://metanit.com/java/tutorial/9.3.php)
* [lambda java](https://www.youtube.com/watch?v=DNC6Lknn2AE&t=3s)
* [Project Lombok](https://projectlombok.org)

</p>
</details>

# Where to find back/front-end part of the project
Here is the back-end part of our project: https://github.com/ita-social-projects/GreenCity

Here is the front-end part of our project: https://github.com/ita-social-projects/GreenCityClient
