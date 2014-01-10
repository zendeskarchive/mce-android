package com.getbase.mce;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.database.Cursor;
import android.database.MatrixCursor;

@RunWith(RobolectricTestRunner.class)
public class CursorToContactsTest {

  private CursorToContacts testSubject;

  @Before
  public void setUp() throws Exception {
    testSubject = new CursorToContacts();
  }

  @Test
  public void shouldConvertEmptyCursorToEmptyListOfContacts() throws Exception {
    final Cursor cursor = new TestCursor();
    assertThat(testSubject.convert(cursor)).isEmpty();
  }

  @Test
  public void shouldConvertRegularCursorToListOfContacts() throws Exception {
    final Cursor cursor = new TestCursor()
        .with("Jan", "Kowalski")
        .with("Janusz", "Nowak");

    assertThat(testSubject.convert(cursor)).containsSequence(
        new Contact("Jan", "Kowalski"),
        new Contact("Janusz", "Nowak")
    );
  }

  @Test
  public void shouldRemoveDuplicates() throws Exception {
    final Cursor cursor = new TestCursor()
        .with("Jan", "Kowalski")
        .with("Janusz", "Nowak")
        .with("Jan", "Kowalski");

    assertThat(testSubject.convert(cursor)).containsSequence(
        new Contact("Jan", "Kowalski"),
        new Contact("Janusz", "Nowak")
    );
  }

  private static class TestCursor extends MatrixCursor {

    public TestCursor() {
      super(new String[] {
          ContactsContract.FIRST_NAME,
          ContactsContract.LAST_NAME
      });
    }

    public TestCursor with(String firstName, String lastName) {
      addRow(new Object[] { firstName, lastName });
      return this;
    }
  }
}
