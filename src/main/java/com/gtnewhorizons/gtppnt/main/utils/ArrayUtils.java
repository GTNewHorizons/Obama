/*
 * Copyright 2020 The GTNH Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT  OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gtnewhorizons.gtppnt.main.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArrayUtils {

    /**
     * Creates a new Array without Null Elements in it
     *
     * @param input the Array
     * @param <T>   Type of the Array
     * @return a new Array without Null Elements or the original array if is size == 0 or only consists of Null Elements
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T[] copyAndRemoveNulls(T[] input, Class<T> clazz) {
        List<T> ret = Arrays.stream(input)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (ret.size() <= 0)
            return input;

        T[] retArr = (T[]) Array.newInstance(clazz, ret.size());

        for (int i = 0; i < ret.size(); i++)
            retArr[i] = ret.get(i);

        return retArr;
    }

}