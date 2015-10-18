package org.nmrao.soapuiext.impl
import com.eviware.soapui.model.testsuite.*
import org.apache.log4j.Logger

import static org.apache.log4j.Logger.getLogger
import static org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper.getInstance

class SuiteRunListener implements TestSuiteRunListener {

    private final static Logger log = getLogger(SuiteRunListener)

    @Override
    void beforeRun(TestSuiteRunner testRunner, TestSuiteRunContext runContext) {
        def scriptEngineHelper = getInstance('TEST_SUITE_BEFORE_RUN',runContext,testRunner,log)
        log.info('Setting testRunner, context, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void afterRun(TestSuiteRunner testRunner, TestSuiteRunContext runContext) {
        def scriptEngineHelper = getInstance('TEST_SUITE_AFTER_RUN',runContext,testRunner,log)
        log.info('Setting testRunner, context, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void beforeTestCase(TestSuiteRunner testRunner, TestSuiteRunContext runContext, TestCase testCase) {
        def scriptEngineHelper = getInstance('TEST_SUITE_BEFORE_TEST_CASE',runContext,testRunner,log)
        scriptEngineHelper.scriptEngine.setVariable('testCase',testCase)
        log.info('Setting testRunner, testCase, context, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void afterTestCase(TestSuiteRunner testRunner, TestSuiteRunContext runContext, TestCaseRunner testCaseRunner) {
        def scriptEngineHelper = getInstance('TEST_SUITE_AFTER_TEST_CASE',runContext,testRunner,log)
        scriptEngineHelper.scriptEngine.setVariable('testCaseRunner',testCaseRunner)
        scriptEngineHelper.run()
    }
}
