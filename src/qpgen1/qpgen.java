package qpgen1;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class qpgen {
	public void fixQno()
	{
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.log(Level.INFO,"QPGen:FixQno");
	}
}
