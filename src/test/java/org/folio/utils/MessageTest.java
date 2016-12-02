package org.folio.utils;

import static org.junit.Assert.*;

import org.folio.rest.tools.messages.MessageEnum;
import org.folio.rest.tools.messages.Messages;
import org.junit.Test;

public class MessageTest {
  private static String __(String language, MessageEnum message, Object... messageArguments) {
    return Messages.getInstance().getMessage(language, message, messageArguments);
  }

  @Test
  public void getMessage() {
    MessageEnum m = CircMessageConsts.LoanPeriodError;
    assertEquals("Loan period must be at least 1, period entered: -5",     __("en", m, -5));
    assertEquals("Leihfrist muss mindestens 1 sein, angegebene Frist: -5", __("de", m, -5));
  }

  @Test
  public void umlaut() {
    assertEquals("Zahlung nicht möglich, Zahlbetrag ist größer als offener Betrag",
        __("de", CircMessageConsts.FinePaidTooMuch));
  }
}
