package dk.sdu.mmmi.cbse.project3.common.data;

import java.io.Serializable;

public final class View implements Serializable {

	private String imageFilePath;

	public View(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

}
