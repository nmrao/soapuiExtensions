package org.nmrao.soapuiext.impl

import com.eviware.soapui.model.testsuite.*
import org.apache.log4j.Logger
import org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper

import static org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelperUtil.getInstance
import static org.apache.log4j.Logger.getLogger

class SuiteRunListener implements TestSuiteRunListener {

    private Logger log = getLogger(SuiteRunListener.class)

    @Override
    void beforeRun(TestSuiteRunner testRunner, TestSuiteRunContext runContext) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_SUITE_BEFORE_RUN',runContext,testRunner,log)
        log.info('Setting testRunner, context, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void afterRun(TestSuiteRunner testRunner, TestSuiteRunContext runContext) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_SUITE_AFTER_RUN',runContext,testRunner,log)
        log.info('Setting testRunner, context, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void beforeTestCase(TestSuiteRunner testRunner, TestSuiteRunContext runContext, TestCase testCase) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_SUITE_BEFORE_TEST_CASE',runContext,testRunner,log)
        scriptEngineHelper.scriptEngine.setVariable('testCase',testCase)
        log.info('Setting testRunner, testCase, context, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void afterTestCase(TestSuiteRunner testRunner, TestSuiteRunContext runContext, TestCaseRunner testCaseRunner) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_SUITE_AFTER_TEST_CASE',runContext,testRunner,log)
        scriptEngineHelper.scriptEngine.setVariable('testCaseRunner',testCaseRunner)
        scriptEngineHelper.run()
    }
}
