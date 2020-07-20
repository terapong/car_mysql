package toto.car.jsf.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.CloseEvent;

@Named("menubean")
@ApplicationScoped
public class MenuBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MenuBean.class);
	
	@Inject
	private IndexBean indexbean;

	@PostConstruct
	private void init() {
		logger.debug("init");
	}
	
	@PreDestroy
	private void destroy() {
		logger.debug("destroy");
	}
	
	public String addCarClick() {
		logger.debug("addCarClick");
		indexbean.setContentCenter("car.xhtml");
		return null;
	}
	
	public String addColorClick() {
		logger.debug("addColorClick");
		indexbean.setContentCenter("color.xhtml");
		return "main?facesRedirect=true";
	}
	
	public String addBlandClick() {
		logger.debug("addBlandClick");
		indexbean.setContentCenter("bland.xhtml");
		return null;
	}
	
	public String addModelClick() {
		logger.debug("addModelClick");
		indexbean.setContentCenter("model.xhtml");
		return null;
	}
	
	public String reportClick() {
		logger.debug("reportClick");
		return null;
	}
	
	public String handleClose(CloseEvent event) {
		logger.debug("handleClose");
		indexbean.setContentCenter("customer.xhtml");
		return "main?facesRedirect=true";
	}
}
