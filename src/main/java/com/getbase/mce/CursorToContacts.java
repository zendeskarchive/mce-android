package com.getbase.mce;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import android.database.Cursor;

import java.util.List;
import java.util.Set;

public class CursorToContacts {

  public List<Contact> convert(Cursor cursor) {
    final Set<Contact> contactsSet = Sets.newHashSet();
    final List<Contact> contacts = Lists.newArrayListWithCapacity(cursor.getCount());

    if (cursor.moveToFirst()) {
      while (cursor.moveToNext()) {
        final String firstName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.FIRST_NAME));
        final String lastName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.LAST_NAME));
        final Contact contact = new Contact(firstName, lastName);

        if (!contactsSet.contains(contact)) {
          contacts.add(contact);
          contactsSet.add(contact);
        }
      }
    }
    return contacts;
  }
}
