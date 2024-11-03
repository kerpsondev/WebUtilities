package pl.kerpson.web.utilities;

import org.jetbrains.annotations.NotNull;

class UpdateResultImpl implements UpdateResult {

  private final boolean latest;
  private final String latestVersion;

  UpdateResultImpl(boolean latest, @NotNull String latestVersion) {
    this.latest = latest;
    this.latestVersion = latestVersion;
  }

  @Override
  public boolean isLatest() {
      return latest;
  }

  @Override
  public @NotNull String getLatestVersion() {
    return latestVersion;
  }
}