module StarDustJavaFX {

	exports app;
	exports pkgBetEngine;
	requires StarDustBLL;
	requires StarDustJabber;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.media;

	requires jackson.databind;
	requires jackson.core;
	requires jackson.dataformat.xml;
	requires jackson.annotations;
	
	requires java.sql;
	
	opens app.controller to javafx.fxml;
	
}