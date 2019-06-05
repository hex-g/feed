package hive.tamagotchi.util;

public final class TextValidation {
  public static boolean isValid(final String text) {
    try {
      return !text.isBlank();
    } catch (NullPointerException e) {
      return false;
    }
  }
}
