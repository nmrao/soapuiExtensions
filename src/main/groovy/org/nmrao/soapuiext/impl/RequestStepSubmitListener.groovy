package org.nmrao.soapuiext.impl
import com.eviware.soapui.model.iface.Submit
import com.eviware.soapui.model.iface.SubmitContext
import com.eviware.soapui.model.iface.SubmitListener
import org.apache.log4j.Logger
import org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper

import static org.apache.log4j.Logger.getLogger
import static org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelperUtil.getInstance

class RequestStepSubmitListener implements SubmitListener {

    private Logger log = getLogger(RequestStepSubmitListener.class)

    @Override
    boolean beforeSubmit(Submit submit, SubmitContext context) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('REQUEST_STEP_BEFORE_SUBMIT', context, null, log)
        scriptEngineHelper.scriptEngine.setVariable('submit', submit)
        scriptEngineHelper.run()
        return true
    }

    @Override
    void afterSubmit(Submit submit, SubmitContext context) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('REQUEST_STEP_AFTER_SUBMIT', context, null, log)
        scriptEngineHelper.scriptEngine.setVariable('submit', submit)
        scriptEngineHelper.run()
    }

}
