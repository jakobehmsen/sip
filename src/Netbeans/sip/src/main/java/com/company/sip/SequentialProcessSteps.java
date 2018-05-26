/*
 * The MIT License
 *
 * Copyright 2018 jakob.
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
package com.company.sip;

import java.util.List;

/**
 *
 * @author jakob
 */
public class SequentialProcessSteps {
    public static Step<? super SequentialProcess> seq(List<Step<? super SequentialProcess>> steps) {
        return seq(steps, 0);
    }
    
    private static <T extends SequentialProcess> Step<? super SequentialProcess> seq(List<Step<? super SequentialProcess>> steps, int i) {
        if(i == steps.size()) {
            return ctx -> {
                ctx.finish();
            };
        } else {
            return ctx -> {
                Step<? super SequentialProcess> s = steps.get(i);
                s.perform(ctx);
                ctx.moveTo(seq(steps, i + 1));
            };
        }
    }
}
