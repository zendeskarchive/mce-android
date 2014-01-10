package com.getbase.mce;

import com.google.common.base.Objects;

public class Contact {

  private final String firstName;
  private final String lastName;

  public Contact(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contact contact = (Contact) o;

    return Objects.equal(contact.firstName, firstName)
        && Objects.equal(contact.lastName, lastName);
  }

  @Override
  public String toString() {
    return "Contact{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}
