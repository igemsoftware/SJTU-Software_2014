EasyBbk
====================
##iGEM-2014: SJTU-Software
This is the project of igem 2014 by team SJTU-Software.

##Introduction
"Easy BBK" is a trial to provide a standardized, visualized and user friendly access to bio-brick system by simplify and interassociate the searching, comparing, designing and uploading of bio-bricks. These four main functions constitute the "Easy BBK" client-side which is realized by pure java and supported by a remote server through the internet. We hope that you will have better experiences when exploring the biobrick world using "Easy BBK". 

For tutorial and more details, please visit our [wiki page](http://2014.igem.org/Team:SJTU-Software). 
	
##Supported OS
* Windows XP of higher
* Linux
* Mac OS

##Client Installation
The runnable file "EasyBBK.jar" under "/Executable/", which is the client of our sofrware, can be execute under all of the OS mentioned above with jre6 or higher. You may install the [java enviroment](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) to execute it. 

If you prefer not to install java environment, other ways to run the client is also provided: 

* For Windows users, download the file "/Executable/EasyBBK-Windows.zip", extract into any directory and double click the file "EasyBBK.bat" in the root directory of the decompressed file folder. 

* For Linux users, download the file "/Executable/EasyBBK-Linux.tar.gz"， extract into any directory. Then open a terminal, cd to the root directory of the decompressed file folder, and execute "bash EasyBBK". 

* For Mac OS users, download the file "/Executable/EasyBBK-MacOS.tar.gz", extract into any directory, the rest will be the same as that under linux. 

##Further Development
Having new ideas? We also provide the source code and the document of the project. For further development, these might help: 
####Floder Structure
#####/Executable/:
The runnable .jar file which can be executed by double click under java environment and the compressed files which can be runned without java environment under different operating systems. 

#####/src/:
The source code of the client and the server. The folder /src/data_collection/ contains the perl source code for database data collecting. The folder /src/data_center/ contains the source code that provides database and server connection and backstage data storing. The folder /src/EasyBbk_Swing/gui/ contains the source code for GUI.  (Note that the server detail like username and password is not provided for information safety). 

#####/lib/:
The linked .jar file essential to the client. You may also link these resources into the software when further modifying. 
	
#####/Documentation/:
The documentation for data collection, API documentation and test documentation for the backstage unit "data_center". 


####API Documents and Architecture Introductions:
In /Documentation/. Provides the API and the architecture documentation in corresponding folders. The documentation for database data collection and data_center unit test is also provided. The same documentation can also be downloaded from our [wiki download page](http://2014.igem.org/Team:SJTU-Software/Project/Download). 

####Compiling
The source code is compiled under [JavaSE-1.6 (the link of JavaSE-1.8 is provided here)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Eclipse 4.4](http://www.eclipse.org/downloads/packages/eclipse-standard-44/lunar) by SJTU-Software team,  you may also compile under the same environment or higher. 

##Contact Us
For any questions or ideas about EasyBbk, please contact:
iGemdry2014@163.com
