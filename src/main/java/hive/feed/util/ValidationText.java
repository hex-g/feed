package hive.feed.util;

public final class ValidationText {
  public static boolean isValid(final String text) {
    try {
      return !text.isBlank();
    } catch (NullPointerException e) {
      return false;
    }
  }
}
