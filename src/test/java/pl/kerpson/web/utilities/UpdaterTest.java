package pl.kerpson.web.utilities;

import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.kerpson.web.utilities.exception.UpdateCheckException;

@TestMethodOrder(OrderAnnotation.class)
public class UpdaterTest {

  @Test
  @Order(1)
  void updaterTest() throws ExecutionException, InterruptedException {
    ProjectUpdater projectUpdater = new ProjectUpdater("https://api.github.com/repos/kerpsondev/mLicense-SDK/releases/latest", "1.0.2-BETA2");

    UpdateResult updateResult = projectUpdater.check();
    Assertions.assertTrue(updateResult.isLatest(), "Version not latest!");

    UpdateResult asyncUpdateResult = projectUpdater.checkAsync().get();
    Assertions.assertTrue(asyncUpdateResult.isLatest(), "Version not latest!");
  }

  @Test
  @Order(2)
  void updaterFakeTest() throws ExecutionException, InterruptedException, UpdateCheckException {
    ProjectUpdater projectUpdater = new ProjectUpdater("https://api.github.com/repos/kerpsondev/mLicense-SDK/releases/latest", "FAKE");

    UpdateResult updateResult = projectUpdater.check();
    Assertions.assertFalse(updateResult.isLatest(), "Version is latest!");

    UpdateResult asyncUpdateResult = projectUpdater.checkAsync().get();
    Assertions.assertFalse(asyncUpdateResult.isLatest(), "Version is latest!");
  }
}
