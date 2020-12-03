package com.softserve.edu.greencity.ui.tools.testng;

import com.softserve.edu.greencity.ui.api.google.sheets.ValueProvider;
import org.testng.*;

import java.lang.reflect.Method;
/**
 * Allows not to run a test remotely, if it's annotated with @LocalOnly
 * May be useful, for example, for tests with file uploading (if files aren't present on the remote machine)
 */
public class RemoteSkipTestAnalyzer implements IInvokedMethodListener {
    public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();

        if (method == null) {
            return;
        }
        if (method.isAnnotationPresent(LocalOnly.class) && isRemote()) {
            throw new SkipException("These Tests shouldn't be run on remote");
        }
            return;
    }
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    }
    public boolean isRemote(){
        return ValueProvider.remote();
    }

}


