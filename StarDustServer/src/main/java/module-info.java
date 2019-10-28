module StarDustServer {
	exports app;

	requires StarDustJabber;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;

	requires javafx.media;
	
	opens app.controller to javafx.fxml;
}