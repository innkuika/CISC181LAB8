module StarDustServer {
	exports app;

	requires StarDustJabber;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;

	requires javafx.media;
	requires StarDustBLL;
	
	opens app.controller to javafx.fxml;
}