module StarDustJavaFX {
	exports pkgMain;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;

	opens pkgMain to javafx.fxml;
}