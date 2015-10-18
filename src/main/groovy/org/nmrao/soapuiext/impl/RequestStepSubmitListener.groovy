package org.nmrao.soapuiext.impl
import com.eviware.soapui.model.iface.Submit
import com.eviware.soapui.model.iface.SubmitContext
import com.eviware.soapui.model.iface.SubmitListener
import org.apache.log4j.Logger

import static org.apache.log4j.Logger.getLogger
import static org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper.getInstance

class RequestStepSubmitListener implements SubmitListener {

    private Logger log = getLogger(RequestStepSubmitListener.class)

    @Override
    boolean beforeSubmit(Submit submit, SubmitContext context) {
        def scriptEngineHelper = getInstance('REQUEST_STEP_BEFORE_SUBMIT', context, null, log)
        scriptEngineHelper.scriptEngine.setVariable('submit', submit)
        scriptEngineHelper.run()
        return true
    }

    @Override
    void afterSubmit(Submit submit, SubmitContext context) {
        def scriptEngineHelper = getInstance('REQUEST_STEP_AFTER_SUBMIT', context, null, log)
        scriptEngineHelper.scriptEngine.setVariable('submit', submit)
        scriptEngineHelper.run()
    }

}
