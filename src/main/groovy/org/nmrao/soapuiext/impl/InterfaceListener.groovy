package org.nmrao.soapuiext.impl
import com.eviware.soapui.model.iface.Request
import com.eviware.soapui.model.support.InterfaceListenerAdapter
import org.apache.log4j.Logger

import static org.apache.log4j.Logger.getLogger
import static org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper.getInstance

class InterfaceListener extends InterfaceListenerAdapter {

    private Logger log = getLogger(InterfaceListener.class)

    @Override
    public void requestAdded( Request request ) {
        def scriptEngineHelper = getInstance('REQUEST_ADDED',null,null,log)
        scriptEngineHelper.scriptEngine.setVariable('request', request)
        log.info('Setting request and log variables')
        scriptEngineHelper.run()
    }

}
