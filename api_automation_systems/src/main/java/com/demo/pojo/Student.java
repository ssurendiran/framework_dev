/*
 *
 *
 *  *
 *  * MIT License
 *  * Copyright (c) 2023 Surendiran Selvam
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *  *  ** Mar 05, 2023
 *  *  * @author Surendiran Selvam
 *  *  * @version 1.0
 *  *  * @since 1.0
 *  *
 *
 *
 */

package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter // This is required for Serialization and DeSerialization
@AllArgsConstructor
@ToString
@Builder
public class Student {

  private int id;
  private String firstname;
  private String lastname;
  private String email;

  // Static Inner class
  public static class StudentBuilderInnerClass {
    private int id;
    private String firstname;
    private String lastname;
    private String email;

    public static StudentBuilderInnerClass builder() {
      return new StudentBuilderInnerClass();
    }

    public StudentBuilderInnerClass setId(int id) {
      this.id = id;
      return this;
    }

    public StudentBuilderInnerClass setFirstname(String firstname) {
      this.firstname = firstname;
      return this;
    }

    public StudentBuilderInnerClass setLastname(String lastname) {
      this.lastname = lastname;
      return this;
    }

    public StudentBuilderInnerClass setEmail(String email) {
      this.email = email;
      return this;
    }

    public Student build() {
      return new Student(this.id, this.firstname, this.lastname, this.email);
    }
  }
}
