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

package com.sonymobile.gitlab.helpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

/**
 * Methods for loading JSON files from the test resources.
 *
 * This allows regular tests to easily access the same files used by WireMock using withBodyFile().
 *
 * @author Emil Nilsson
 */
public class FileHelpers {
    /**
     * Loads a JSON object from a file in the test resources.
     *
     * @see #getAbsoluteResourceFilePath(String, String)
     *
     * @param relativePath a path relative to the files directory
     * @return a JSON object
     * @throws IOException if the file cannot be loaded
     */
    public static JSONObject loadJsonObjectFromFile(String relativePath)
            throws IOException {
        return loadJsonObjectFromFile(relativePath, null);
    }

    /**
     * Loads a JSON object from a file in the test resources.
     *
     * @see #getAbsoluteResourceFilePath(String, String)
     *
     * @param relativePath a path relative to the files directory
     * @param variant the name of the variant suffix or null
     * @return a JSON object
     * @throws IOException if the file cannot be loaded
     */
    public static JSONObject loadJsonObjectFromFile(String relativePath, String variant)
            throws IOException {
        return new JSONObject(readFileToString(new File(getAbsoluteResourceFilePath(relativePath, variant))));
    }

    /**
     * Loads a JSON object from a JSON array from a file in the test resources.
     *
     * @see #getAbsoluteResourceFilePath(String, String)
     *
     * @param relativePath the path relative to the files directory
     * @param indexInArray the index in the array
     * @return a JSON object
     * @throws IOException if the file cannot be loaded
     */
    public static JSONObject loadJsonObjectFromFile(String relativePath, int indexInArray)
            throws IOException {
        return loadJsonArrayFromFile(relativePath).getJSONObject(indexInArray);
    }

    /**
     * Loads a JSON object from a JSON array from a file in the test resources.
     *
     * @see #getAbsoluteResourceFilePath(String, String)
     *
     * @param relativePath the path relative to the files directory
     * @param indexInArray the index in the array
     * @return a JSON object
     * @throws IOException if the file cannot be loaded
     */
    public static JSONObject loadJsonObjectFromFile(String relativePath, int indexInArray, String variant)
            throws IOException {
        return loadJsonArrayFromFile(relativePath, variant).getJSONObject(indexInArray);
    }

    /**
     * Loads a JSON array from a file in the test resources.
     *
     * @see #getAbsoluteResourceFilePath(String, String)
     *
     * @param relativePath a path relative to the files directory
     * @return a JSON array
     * @throws IOException if the file cannot be loaded
     */
    public static JSONArray loadJsonArrayFromFile(String relativePath)
            throws IOException {
        return loadJsonArrayFromFile(relativePath, null);
    }

    /**
     * Loads a JSON array from a file in the test resources.
     *
     * @see #getAbsoluteResourceFilePath(String, String)
     *
     * @param relativePath a path relative to the files directory
     * @param variant the name of the variant suffix or null
     * @return a JSON array
     * @throws IOException if the file cannot be loaded
     */
    public static JSONArray loadJsonArrayFromFile(String relativePath, String variant)
            throws IOException {
        return new JSONArray(readFileToString(new File(getAbsoluteResourceFilePath(relativePath, variant))));
    }

    /**
     * Gets the absolute file path for a test resource file.
     *
     * The variant is added as a suffix after a "_" to the path if set and extension ".json".
     * The path file must be relative to the src/test/resources/__files directory used by WireMock.
     *
     * @param relativePath the path relative to the files directory
     * @param variant the name of the variant suffix or null
     * @return the absolute file path
     * @throws IOException if the file cannot be loaded
     */
    private static String getAbsoluteResourceFilePath(String relativePath, String variant)
            throws IOException {
        if (isNotEmpty(variant)) {
            relativePath += "_" + variant;
        }
        relativePath = "/__files/" + relativePath + ".json";

        URL url = FileHelpers.class.getResource(relativePath);
        if (url == null) {
            throw new FileNotFoundException("The file " + relativePath + " doesn't exist in test resources");
        }
        return url.getFile();
    }
}
