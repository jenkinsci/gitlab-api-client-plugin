/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Andreas Alanko, Emil Nilsson, Sony Mobile Communications AB.
 * All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.sonymobile.gitlab;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.sonymobile.gitlab.helpers.FileHelpers.loadJsonObjectFromFile;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link GitLabGroup}
 *
 * @author Emil Nilsson
 */
public class GitLabGroupTest {
    /** The group object to test against. */
    private GitLabGroup group;

    /**
     * Loads the group object from a JSON file.
     *
     * @throws java.io.IOException if reading of the JSON file failed
     */
    @Before
    public void setUp() throws IOException {
        group = new GitLabGroup(loadJsonObjectFromFile("api/v3/groups/byGroupId.json"));
    }

    /**
     * Tests whether the correct user ID is set.
     */
    @Test
    public void getId() {
        assertThat(2, is(group.getId()));
    }

    /**
     * Tests whether the correct group name is set.
     */
    @Test
    public void getName() {
        assertThat("Group Name", is(group.getName()));
    }

    /**
     * Tests whether the correct group path is set.
     */
    @Test
    public void getPath() {
        assertThat("groupname", is(group.getPath()));
    }

    /**
     * Tests whether toString() returns the group name.
     */
    @Test
    public void convertToString() {
        assertThat(group, hasToString("Group Name"));
    }
}