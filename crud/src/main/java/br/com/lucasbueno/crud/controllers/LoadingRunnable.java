package br.com.lucasbueno.crud.controllers;

import java.io.IOException;

import br.com.lucasbueno.crud.dao.UserDAO;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class LoadingRunnable implements Runnable {
	private ProgressBar progressBar;
	private LoadingController loadingController;

	public LoadingRunnable(ProgressBar progressBar, LoadingController loadingController) {
		this.progressBar = progressBar;
		this.loadingController = loadingController;
	}

	@Override
	public void run() {
		updateProgress(0);
		while (true) {
			try {
				new UserDAO().getAll();
				Platform.runLater(() -> {
					try {
						loadingController.closeWindow();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				return;
			} catch (Exception e) {				
				if(progressBar.getProgress() > 0.5)
					updateProgress(0);
				else
					updateProgress(progressBar.getProgress() + 0.5);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
		}
	}

	private void updateProgress(double value) {
		Platform.runLater(() -> {
			progressBar.setProgress(value);
		});
	}

}
