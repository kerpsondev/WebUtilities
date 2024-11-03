package pl.kerpson.web.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.kerpson.web.utilities.exception.UpdateCheckException;

public class ProjectUpdater {

  private final static HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

  private final String githubUrl;
  private final String currentVersion;

  public ProjectUpdater(@NotNull String githubUrl, @NotNull String currentVersion) {
    this.githubUrl = githubUrl;
    this.currentVersion = currentVersion;
  }

  public @Nullable UpdateResult check() throws UpdateCheckException {
    HttpRequest httpRequest = this.prepareRequest();
    try {
      HttpResponse<String> response = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      Gson gson = new Gson();
      JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
      String latestVersion = jsonObject.get("tag_name").getAsString();
      boolean isUpToDate = this.currentVersion.equals(latestVersion);

      return new UpdateResultImpl(isUpToDate, latestVersion);
    } catch (IOException | InterruptedException | IllegalArgumentException exception) {
      throw new UpdateCheckException(exception);
    }
  }

  public CompletableFuture<UpdateResult> checkAsync() {
    HttpRequest httpRequest = this.prepareRequest();
    return HTTP_CLIENT.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
        .handle((response, exception) -> {
          if (Objects.nonNull(exception)) {
            throw new UpdateCheckException(exception);
          }

          Gson gson = new Gson();
          JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
          String latestVersion = jsonObject.get("tag_name").getAsString();
          boolean isUpToDate = this.currentVersion.equals(latestVersion);

          return new UpdateResultImpl(isUpToDate, latestVersion);
        });
  }

  private HttpRequest prepareRequest() {
    return HttpRequest.newBuilder()
        .GET()
        .uri(URI.create(this.githubUrl))
        .header("Content-Type", "application/json")
        .build();
  }
}
