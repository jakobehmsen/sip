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

/**
 *
 * @author jakob
 */
public class TransientSequentialProcess implements SequentialProcess {
    private Step<? super SequentialProcess> currentStep;
    private boolean finished;
    private Object obj;
    
    public TransientSequentialProcess(Step initialStep) {
        currentStep = initialStep;
    }

    @Override
    public void moveTo(Step<? super SequentialProcess> step) {
        currentStep = step;
    }

    @Override
    public void set(Object obj) {
        this.obj = obj;
    }

    @Override
    public <T> T get() {
        return (T) obj;
    }

    @Override
    public void finish() {
        finished = true;
    }
    
    public void proceed() {
        currentStep.perform(this);
    }
    
    public boolean isFinished() {
        return finished;
    }
}
