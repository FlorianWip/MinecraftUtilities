
# Welcome to MinecraftUtilities
What is **MinecraftUtilities**?
I my time I wrote and collected a lot of Utils. I wrote libraries for private use. Now I want to collect all of these classes to a library, which I want to share to you. So be active, I will add my Utils and will update it often. This library works for spigot aswell for bungeecord, some features are for one of these platforms alone, but I mark that in the wiki.

Download the plugin [from my maven repository](https://repository.flammenfuchs.de/public/de/flammenfuchs/utilities/MinecraftUtilities-final/1.0.0/MinecraftUtilities-final-1.0.0.jar)


# Features
**The usage of all features will be explained in the wiki**

## Command-System (Bungee|Spigot)

You can simply create Commands for only players (*PlayerCommand*) or for console and players (*ConsoleCommand*). It supports sub-commands and it includes a *ArgsUtil*, which makes it easier to work with the arguments. It provides a *CommandRegistery* which allows to set defaultPermissionMessages, is able to load listener and commands automatically, so your Plugin-Main stayes clean. It provides a *HelpPattern*, so create the help message for Commands easy.

## Reflection-Util (Bungee|Spigot)

With this ReflectionUtil, you can easily get all classes in a the package of an object and check if a class has a empty (*NoArgsConstructor*) constructor.

## Message-Util (Bungee|Spigot)
This Util contains some special characters for messages and you can easily generate header and footer lines

## Language System (Bungee|Spigot)
The Library provides a custom Language-System, which is dynamic and the language can set for each player

## ItemBuilder (Spigot)
Annoyed from creating Items with ItemStack and ItemMeta class, this is over with the ItemBuilder

## JSON-Configuration System (Bungee|Spigot)
Annoyed from creating yml configurations. Create a full JSON-Configuration with default values, which is much faster then a yml config.

## Custom Main Class for Plugin (Spigot)
Create a clean reloadable main class. It can load/save JSON-Configurations, which are created with the custom JSON Config system automatically, it configurates the CommandRegistery with the Bootstrap.

# How to use?

## How to make it runnable?

Simply drop the plugin jar ([download](https://repository.flammenfuchs.de/public/de/flammenfuchs/utilities/MinecraftUtilities-final/1.0.0/MinecraftUtilities-final-1.0.0.jar)) is in the plugins Folder of your BungeeCord-/Spigot-Server.

## How to develope with it? 

**Maven**

1. Add my repository to your maven project (pom.xml).

> 
	<repositories>
		<repository>
			<id>flammenfuchs</id>
			<url>https://repository.flammenfuchs.de/public</url>
		</repository>
	</repositories>

2. Add the dependency to your maven-project (pom.xml)

Bukkit:

> 
	<dependencies>
		<dependency>
			<groupId>de.flammenfuchs.utilities</groupId>
			<artefactId>Bukkit</artifactId>
			<version>1.0.0</version>
		</dependency>
	</dependencies>

BungeeCord:

> 
	<dependencies>
		<dependency>
			<groupId>de.flammenfuchs.utilities</groupId>
			<artefactId>BungeeCord</artifactId>
			<version>1.0.0</version>
		</dependency>
	</dependencies>

**Gradle**
1. Add my repository to your gradle-project

> 
	repositories {
	    maven {
	        url "https://repository.flammenfuchs.de/public"
	    }
	}

 2. Add the dependency to your gradle project

Bukkit:

	dependencies {
	    Bukkit 'de.flammenfuchs.utilities:Bukkit:1.0.0'
	}
Bungeecord:

    dependencies {
	    BungeeCord 'de.flammenfuchs.utilities:BungeeCord:1.0.0'
	}

# Compatibility
## Spigot
|Minecraft-Version |Getestete Kompatiblität        |Inkompatibel (geprüft)       |
|------------------|-------------------------------|-----------------------------|
|1.8               |ab v1.0.0                      |-                            |
|1.9               |ungetestet                     |-                            |
|1.10              |ungetestet                     |-                            |
|1.11              |ungetestet                     |-                            |
|1.12              |ungetestet                     |-                            |
|1.13              |ungetestet                     |-                            |
|1.14              |ungetestet                     |-                            |
|1.15              |ungetestet                     |-                            |
|1.16              |ungetestet                     |-                            |
|1.17              |ungetestet                     |-                            |

## BungeeCord
Workes fine with last version.
# More information
in the wiki of this project
