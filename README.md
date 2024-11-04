## ♻️ WebUtilities

[![Release](https://img.shields.io/github/v/release/kerpsondev/WebUtilities.svg)](https://github.com/kerpsondev/WebUtilities/releases)

Simple api that was written for personal use, I provide it as a portfolio. Maybe it will be useful to someone.

The api currently has one module, project version analysis.

### ♻️ Maven & Gradle

### Maven
```xml
<repositories>
  <repository>
    <id>minecreators-repository-releases</id>
    <name>MineCreators Repository</name>
    <url>https://repository.minecreators.pl/releases</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>pl.kerpson.utilities.web</groupId>
    <artifactId>WebUtilities</artifactId>
    <version>1.0.1</version>
  </dependency>
</dependencies>
```

### Gradle
```gradle
maven {
    name = "minecreatorsRepositoryReleases"
    url = uri("https://repository.minecreators.pl/releases")
}

implementation("pl.kerpson.utilities.web:WebUtilities:1.0.1")
```
<br>

### ♻️ ProjectUpdater

```java
ProjectUpdater projectUpdater = new ProjectUpdater(GITHUB_API_PROJECT_URL, CURRENT_VERSION);
UpdateResult updateResult = projectUpdater.check();
Assertions.assertTrue(updateResult.isLatest(), "Latest version: " + updateResult.getLatestVersion());
```

You can check async too

```java
ProjectUpdater projectUpdater = new ProjectUpdater(GITHUB_API_PROJECT_URL, CURRENT_VERSION);
projectUpdater.checkAsync().thenAccept(updateResult -> {
    Assertions.assertTrue(updateResult.isLatest(), "Latest version: " + updateResult.getLatestVersion());
});
```
<br>

### ♻️ Project status
![Alt](https://repobeats.axiom.co/api/embed/bddbf6daea608f4eb65e582dd7c11d7630e57281.svg "Repobeats analytics image")
