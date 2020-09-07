# GreenCityTests
Automation tests for GreenCity project

### Required to install

* Java 8
* Google [Chrome](https://www.google.com/chrome/) Browser
* [maven](https://maven.apache.org/)

# Links
* selenoid UI http://35.198.124.146:8080/#/  - GUI to watch remote test run
* selenid driver connect http://35.198.124.146:4444
* local driver connect http://localhots:4444

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

Set test variables, for example:
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

# Options and capabilites example:
Set test variables, for example:
<details><summary>Example</summary>
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
            
com.softserve.edu.greencity.ui.tests.runner.DriverSetup.optionsArguments
DesiredCapabilities capabilities = DesiredCapabilities.chrome();
* capabilities.setBrowserName("chrome");
* capabilities.setVersion("84.0");
* capabilities.setCapability("enableVNC", true);
* capabilities.setCapability("enableVideo", false);
* capabilities.setCapability(ChromeOptions.CAPABILITY, options);

Local:
* GridHub.startLocally(4444);
* RegisterChrome.startNode(5551);
* RegisterChrome.startNode(5552);
* RegisterChrome.startNode(5553);
* RegisterChrome.startNode(5554);
* driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);

Remote:
* driver = new RemoteWebDriver(
                    URI.create("http://35.198.124.146:4444/wd/hub").toURL(),
                    capabilities);
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

#### VM instances
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
* [You are awesome](https://dl.dropboxusercontent.com/s/vponrk0qbct1r3i/shot_200908_011231.png)
#### Docker
</p>
</details>

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
* [Useful links](https://www.digitalocean.com/community/tags/docker?subtype=tutorial) 
* [Here we are again.  You are great](https://dl.dropboxusercontent.com/s/ds7886rrg0r8vgc/shot_200908_021245.png)
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

#### Selenoid
<details><summary>Steps</summary>
<p>

* `wget -O cm https://github.com/aerokube/cm/releases/download/1.7.2/cm_linux_amd64`
* $ `chmod +x cm`
* `curl -s https://aerokube.com/cm/bash | bash`
* $ `./cm selenoid start --vnc`
* $ `./cm selenoid-ui start`

</p>
</details>



# Where to find back/front-end part of the project
Here is the back-end part of our project: https://github.com/ita-social-projects/GreenCity

Here is the front-end part of our project: https://github.com/ita-social-projects/GreenCityClient