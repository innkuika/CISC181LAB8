module StarDustServer {
	exports app;

	requires StarDustBLL;
	requires StarDustJabber;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.media;

	opens app.controller to javafx.fxml;
}