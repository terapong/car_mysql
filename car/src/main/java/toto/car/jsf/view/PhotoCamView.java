package toto.car.jsf.view;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.event.CaptureEvent;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class PhotoCamView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BlandBean.class);
	
	private String filename;

	private String getRandomImageName() {
		int i = (int) (Math.random() * 10000000);

		return String.valueOf(i);
	}

	public void oncapture(CaptureEvent captureEvent) {
		filename = getRandomImageName();
		byte[] data = captureEvent.getData();

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
				+ File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".jpeg";
		
		logger.debug("newFileName: " + newFileName);

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(newFileName));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}