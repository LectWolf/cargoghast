pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/") { name = "Fabric" }
		maven("https://maven.kikugie.dev/releases")
		maven("https://maven.kikugie.dev/snapshots")
		gradlePluginPortal()
		mavenCentral()
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.9.6"
}

stonecutter {
	create(rootProject) {
		version("1.21.6", "1.21.6").buildscript = "build.121.gradle"
		version("1.21.7", "1.21.7").buildscript = "build.121.gradle"
		version("1.21.8", "1.21.8").buildscript = "build.121.gradle"
		version("1.21.9", "1.21.9").buildscript = "build.121.gradle"
		version("1.21.10", "1.21.10").buildscript = "build.121.gradle"
		version("1.21.11", "1.21.11").buildscript = "build.121.gradle"
		version("26.1", "26.1")
		version("26.2", "26.2")
		vcsVersion = "26.2"
	}
}

rootProject.name = "cargoghast"
