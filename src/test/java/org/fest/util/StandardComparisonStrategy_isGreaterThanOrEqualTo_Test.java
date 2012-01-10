/*
 * Created on Sep 23, 2006
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
 * Copyright @2006-2011 the original author or authors.
 */
package org.fest.util;

import static org.junit.Assert.*;
import static org.junit.rules.ExpectedException.none;

import java.awt.Rectangle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link StandardComparisonStrategy#isGreaterThanOrEqualTo(Object, Object)}.
 * 
 * @author Joel Costigliola
 */
public class StandardComparisonStrategy_isGreaterThanOrEqualTo_Test {

  private static StandardComparisonStrategy standardComparisonStrategy = StandardComparisonStrategy.instance();

  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_pass() {
    Employee boss = new Employee(10000, 35);
    Employee young = new Employee(10000, 25);
    assertTrue(standardComparisonStrategy.isGreaterThanOrEqualTo(boss, young));
    assertTrue(standardComparisonStrategy.isGreaterThanOrEqualTo(boss, boss));
    assertFalse(standardComparisonStrategy.isGreaterThanOrEqualTo(young, boss));
  }

  @Test
  public void should_fail_if_a_parameter_is_not_comparable() {
    thrown.expect(IllegalArgumentException.class);
    Rectangle r1 = new Rectangle(10, 20);
    Rectangle r2 = new Rectangle(20, 10);
    standardComparisonStrategy.isGreaterThanOrEqualTo(r1, r2);
  }

}
