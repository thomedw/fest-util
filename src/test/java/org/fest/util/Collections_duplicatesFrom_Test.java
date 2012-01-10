/*
 * Created on Apr 29, 2007
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Collections.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections}</code>.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Collections_duplicatesFrom_Test {

  @Test
  public void should_return_existing_duplicates() {
    Collection<String> duplicates = duplicatesFrom(list("Merry", "Frodo", "Merry", "Sam", "Frodo"));
    assertEquals(2, duplicates.size());
    assertTrue(duplicates.contains("Frodo"));
    assertTrue(duplicates.contains("Merry"));
  }

  @Test
  public void should_not_return_any_duplicates() {
    Collection<String> duplicates = duplicatesFrom(list("Frodo", "Sam", "Gandalf"));
    assertTrue(duplicates.isEmpty());
  }

  @Test
  public void should_not_return_any_duplicates_if_collection_is_empty() {
    Collection<String> duplicates = duplicatesFrom(new ArrayList<String>());
    assertTrue(duplicates.isEmpty());
  }

  @Test
  public void should_not_return_any_duplicates_if_collection_is_null() {
    Collection<String> duplicates = duplicatesFrom(null);
    assertTrue(duplicates.isEmpty());
  }

  @Test
  public void should_return_existing_duplicates_according_to_given_comparator() {
    Collection<String> duplicates = duplicatesFrom(list("Merry", "Frodo", "MERrY", "Sam", "FroDo"),
        String.CASE_INSENSITIVE_ORDER);
    assertEquals(2, duplicates.size());
    // we can't use : assertTrue(duplicates.contains("Frodo")); because we don't know wether duplicates will contain
    // Frodo or FroDo, anyway the point here is to have the duplicate values according to given comparator
    // => make a collection of UPPER case duplicates and assert that it contains "FRODO" and "MERRY"  
    List<String> upperCaseDuplicates = list();
    for (String duplicate : duplicates) {
      upperCaseDuplicates.add(duplicate.toUpperCase());
    }
    assertTrue(upperCaseDuplicates.contains("FRODO"));
    assertTrue(upperCaseDuplicates.contains("MERRY"));
  }

  @Test
  public void should_not_return_any_duplicates_according_to_given_comparator() {
    Collection<String> duplicates = duplicatesFrom(list("Frodo", "Sam", "Sam "), String.CASE_INSENSITIVE_ORDER);
    assertTrue(duplicates.isEmpty());
  }

  @Test
  public void should_not_return_any_duplicates_if_collection_is_empty_whatever_comparator_is() {
    Collection<String> duplicates = duplicatesFrom(new ArrayList<String>(), String.CASE_INSENSITIVE_ORDER);
    assertTrue(duplicates.isEmpty());
  }

  @Test
  public void should_not_return_any_duplicates_if_collection_is_null_whatever_comparator_is() {
    Collection<String> duplicates = duplicatesFrom(null, String.CASE_INSENSITIVE_ORDER);
    assertTrue(duplicates.isEmpty());
  }
}
