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

package com.sonymobile.gitlab.model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.sonymobile.gitlab.helpers.FileHelpers.loadJsonObjectFromFile;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.NOVEMBER;
import static org.apache.commons.lang.time.DateUtils.UTC_TIME_ZONE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests getting attributes from a {@link GitLabGroupMemberInfo} object.
 *
 * @author Emil Nilsson
 */
public class GroupMemberTest {
    /** The group member. */
    private GitLabGroupMemberInfo groupMember;

    /**
     * Loads the user object from a JSON file.
     *
     * @throws IOException if reading of the JSON file failed
     */
    @Before
    public void setUp() throws IOException {
        // load the first member (index 0) of the group
        groupMember = new GitLabGroupMemberInfo(loadJsonObjectFromFile("api/v3/groups/id/members.json", 0),
                "groupname");
    }

    @Test
    public void getId() {
        assertThat(1, is(groupMember.getId()));
    }

    @Test
    public void getUsername() {
        assertThat("root", is(groupMember.getUsername()));
    }

    @Test
    public void getEmail() {
        assertThat("root@example.com", is(groupMember.getEmail()));
    }

    @Test
    public void getName() {
        assertThat("Administrator", is(groupMember.getName()));
    }

    @Test
    public void getAccessLevel() {
        assertThat(GitLabAccessLevel.OWNER, is(groupMember.getAccessLevel()));
    }

    @Test
    public void getCreatedAtDate() {
        // create the date 2010-11-12 13:14:15
        Calendar calendar = new GregorianCalendar(UTC_TIME_ZONE);
        calendar.set(2010, NOVEMBER, 12, 13, 14, 15);
        calendar.clear(MILLISECOND);
        Date expectedDate = calendar.getTime();

        assertThat(expectedDate, is(groupMember.getCreatedAtDate()));
    }

    @Test
    public void isActive() {
        assertThat(groupMember.isActive(), is(true));
    }

    @Test
    public void getGroupName() {
        assertThat("groupname", is(groupMember.getGroupName()));
    }
}