package pl.kerpson.web.utilities;

import org.jetbrains.annotations.NotNull;

public interface UpdateResult {

  boolean isLatest();

  @NotNull String getLatestVersion();
}
