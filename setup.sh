#!/bin/bash

# Install sublime text
sudo add-apt-repository ppa:webupd8team/sublime-text-2
sudo apt-get update
sudo apt-get install sublime-text


# Install minecraft server
wget http://devoxx4kids-wajug.s3.amazonaws.com/bukkit-devoxx.tar.gz
tar -xzf bukkit-devoxx.tar.gz
mv bukkit-devoxx bukkit
rm bukkit/plugins/spigotscalaplugin_2.10-0.1.jar

# install jars in m2
# mmhhh does not work...
#chmod +x bukkit/apache-maven-3.2.3/bin/mvn
#cd bukkit
#apache-maven-3.2.3/bin/mvn install:install-file -Dfile=craftbukkit-1.8.jar -DgroupId=org.bukkit \
#   -DartifactId=craftbukkit -Dversion=1.8 -Dpackaging=R0.1-SNAPSHOT
#cd ..
wget  http://devoxx4kids-wajug.s3.amazonaws.com/m2.tar.gz
tar -xzf m2.tar.gz

mkdir bin
cd bin

# install sbt
wget https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/0.13.7/sbt-launch.jar
wget http://devoxx4kids-wajug.s3.amazonaws.com/sbt
chmod +x sbt

# install minecraft client
wget https://s3.amazonaws.com/Minecraft.Download/launcher/Minecraft.jar
wget http://devoxx4kids-wajug.s3.amazonaws.com/minecraft
chmod +x minecraft

#install 
mkdir projects
cd projects
git clone https://github.com/xtordoir/devoxx4kids-minecraft.git

export PATH=$HOME/bin:$PATH


