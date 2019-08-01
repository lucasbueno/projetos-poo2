module br.edu.ifsc.fstFXML {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.edu.ifsc.fstFXML to javafx.fxml;
    exports br.edu.ifsc.fstFXML;
}